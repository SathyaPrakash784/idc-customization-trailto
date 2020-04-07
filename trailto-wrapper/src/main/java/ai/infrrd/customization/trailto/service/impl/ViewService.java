package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.commons.AppConstants;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.entities.ScanRequestVOV2;
import ai.infrrd.customization.trailto.exceptions.FatalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Component public class ViewService
{
    private static final Logger LOG = LoggerFactory.getLogger( ViewService.class );


    public GimletResponseV2 toViewObject( GimletResponseV2 updatedFieldsResponse )
    {
        //Perform deep copy of updatedFieldsResponse only if kakfaStreamService is Asynchronous.
        LOG.info( "Creating view object for scanId: {} ", updatedFieldsResponse.getData().getScanId() );

        ScanRequestVOV2 dataCopy = updatedFieldsResponse.getData();
        if ( updatedFieldsResponse.getStatus() != null && !updatedFieldsResponse.getStatus()
            .equalsIgnoreCase( AppConstants.STATUS_EXTRACTED ) ) {
            return new GimletResponseV2( updatedFieldsResponse.getStatus(), dataCopy.getScanId(), dataCopy.getImageUrl(), null,
                new HashMap<>(), new ArrayList<>(), 0, dataCopy.getRawText() );
        }

        Map<String, Object> viewFields = updatedFieldsResponse.getData().getWrapperFields();

        if ( viewFields == null || viewFields.isEmpty() ) {
            throw new FatalException( "WRAPPER FIELDS CAN NOT BE EMPTY. LOOKS LIKE VIEW IS CALLED BEFORE UPDATING FIELDS." );
        }

        return new GimletResponseV2( updatedFieldsResponse.getStatus(), dataCopy.getScanId(), dataCopy.getImageUrl(),
            dataCopy.getExtractionConfidence(), viewFields, dataCopy.getWrapperLineItems(), dataCopy.getLicenseConsumed(),
            dataCopy.getRawText() );
    }

}
