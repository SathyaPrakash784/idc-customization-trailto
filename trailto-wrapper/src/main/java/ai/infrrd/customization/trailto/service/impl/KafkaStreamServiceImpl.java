package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.commons.FieldName;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.entities.KafkaDataEntity;
import ai.infrrd.customization.trailto.entities.KafkaMetaData;
import ai.infrrd.customization.trailto.exceptions.FatalException;
import ai.infrrd.customization.trailto.service.KafkaStreamService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;


@Service public class KafkaStreamServiceImpl implements KafkaStreamService
{
    public static final Logger LOGGER = LoggerFactory.getLogger( KafkaStreamServiceImpl.class );
    private KafkaTemplate<String, String> kafkaTemplate;
    @Value ("${spring.kafka.topicName}") private String TOPIC_NAME;


    @Autowired public void setKafkaTemplate( KafkaTemplate<String, String> kafkaTemplate )
    {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override public void sendMessage( String message )
    {
        LOGGER.debug( "Sending data to kafka" );
        ListenableFuture<SendResult<String, String>> response = kafkaTemplate.send( TOPIC_NAME, message );
        try {
            SendResult<String, String> sendResult = response.get( 5L, TimeUnit.SECONDS );
            LOGGER.debug( "Response from kafka call {}", sendResult );
        } catch ( InterruptedException e ) {
            LOGGER.error( "Failed to send data to Kafka : InterruptedException ", e );
        } catch ( ExecutionException e ) {
            LOGGER.error( "Execution Exception while sending data to kafka ", e );
        } catch ( TimeoutException e ) {
            LOGGER.error( "Timeout happened while sending data to kafka ", e );
        }
    }


    @Override public void streamUpdatedResponse( String xcci, GimletResponseV2 gimletResponseV2, String profile )
    {
        //Sending data to the kafka queue
        String message = null;

        String scanId = gimletResponseV2.getData().getScanId();

        LOGGER.info( "Streaming updated data to kafka for scanId: {}", scanId );
        try {
            Map<String, Object> wrapperFields = new HashMap<>( gimletResponseV2.getData().getWrapperFields() );
            List<Object> wrapperLineItems = gimletResponseV2.getData().getWrapperLineItems();

            Map<String, Object> kafkaReadyWrapperFields = updateNullValuesToStringNull( wrapperFields );

            Map<String, Object> kafkaReadyLineItemsHolder = new HashMap<>();

            //This is how line items should be streamed to kafka.
            List<Object> kafkaReadyLineItems = prepareLineItemsForKafkaMessage( wrapperLineItems );
            kafkaReadyLineItemsHolder.put( "wrapperLineitems", kafkaReadyLineItems );

            KafkaDataEntity kafakaDataEntity = new KafkaDataEntity( xcci, scanId, kafkaReadyWrapperFields,
                kafkaReadyLineItemsHolder, gimletResponseV2.getUploadDateTime(), System.currentTimeMillis(), profile,
                gimletResponseV2.getData().getImageUrl() );

            KafkaMetaData kafkaMetaData = new KafkaMetaData( kafakaDataEntity );

            message = new ObjectMapper().writeValueAsString( kafkaMetaData );
        } catch ( JsonProcessingException e ) {
            LOGGER.error( "Exception while creating json string fields for kafka for scanId {}",
                gimletResponseV2.getData().getScanId() );
        }
        LOGGER.debug( "STREAMING MESSAGE TO KAFKA... \n***** {}", message );
        this.sendMessage( message );
    }


    /**
     * Convert null values to String "null" values. Because, Elastic search won't store null values and resultAnalyzer won't handle it while saving it ES.
     * </br> This case is handled in get call by apiGateway.
     * @param lineItems
     * @return
     */
    private List<Object> prepareLineItemsForKafkaMessage( List<Object> lineItems )
    {
        LOGGER.info( "prepareLineItemsForKafkaMessage: converting null columns to \"null\" " );
        return lineItems.stream().filter( Objects::nonNull ).map( lineItem -> {
            Map<String, Object> item = (Map<String, Object>) lineItem;
            item.entrySet().stream()
                .forEachOrdered( column -> column.setValue( ( column.getValue() == null ) ? "null" : column.getValue() ) );
            return item;
        } ).collect( Collectors.toList() );
    }


    private Map<String, Object> updateNullValuesToStringNull( Map<String, Object> wrapperFields )
    {
        LOGGER.info( "Preparing fields For Kafka Message: converting null field values to \"null\" " );
        Map<String, Object> nullFreeFields = new HashMap<>();
        for ( Entry<String, Object> field : wrapperFields.entrySet() ) {
            switch ( field.getKey() ) {
                case FieldName.STORE_DETAILS:

                    Map<String, Object> nullFreeStoreDetail;
                    try {//deep copy so that the wrapper response will still be null instead of "null"
                        String serialized = new ObjectMapper().writeValueAsString( field.getValue() );
                        nullFreeStoreDetail = new ObjectMapper().readValue( serialized, new TypeReference<Map<String, Object>>()
                        {
                        } );
                    } catch ( IOException e ) {
                        //Never happening.
                        throw new FatalException( "Failed to deep copy(serialize and deserialize)", e );
                    }

                    Map<String, String> storeDetailValues = (Map<String, String>) nullFreeStoreDetail.get( "value" );

                    for ( Entry<String, String> storeDetail : storeDetailValues.entrySet() ) {
                        String valueToStore = ( storeDetail.getValue() == null ) ? "null" : storeDetail.getValue();
                        storeDetail.setValue( valueToStore );
                    }
                    nullFreeStoreDetail.replace( "value", storeDetailValues );
                    nullFreeFields.put( field.getKey(), nullFreeStoreDetail );
                    break;
                default:
                    nullFreeFields.put( field.getKey(), field.getValue() );
                    break;
            }
        }
        return nullFreeFields;
    }
}
