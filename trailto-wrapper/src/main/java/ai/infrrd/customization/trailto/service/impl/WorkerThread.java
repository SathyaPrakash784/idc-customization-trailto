package ai.infrrd.customization.trailto.service.impl;


import ai.infrrd.customization.trailto.entities.DocumentMetaData;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.service.GimletService;
import ai.infrrd.customization.trailto.service.KafkaStreamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Scope ( value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkerThread extends Thread
{
    private static final Logger LOG = LoggerFactory.getLogger( WorkerThread.class );


    private DocumentMetaData documentMetaData;

    private FilterService filterService;

    private GimletService gimletService;

    private KafkaStreamService kafkaStreamService;

    private ViewService viewService;

    @Autowired
    public void setGimletService( GimletService gimletService )
    {
        this.gimletService = gimletService;
    }

    @Autowired
    public void setFilterService( FilterService filterService ) { this.filterService = filterService; }

    @Autowired
    public void setKafkaStreamService( KafkaStreamService kafkaStreamService )
    {
        this.kafkaStreamService = kafkaStreamService;
    }

    @Autowired
    public void setViewService( ViewService viewService ) { this.viewService = viewService; }

    public void setDocumentMetaData( DocumentMetaData documentMetaData )
    {
        this.documentMetaData = documentMetaData;
    }


    @Override
    public void run()
    {
        LOG.info( "Worker thread running for scanId: {}", documentMetaData.getScanId() );
        GimletResponseV2 response = gimletService.postGimlet( documentMetaData.getXcci(), documentMetaData.getFileBytes(),
            documentMetaData.getScanId(), documentMetaData.getProfile() );

        //filter results before returning it
        filterService.filterGimletResponse( response );

        kafkaStreamService.streamUpdatedResponse( documentMetaData.getXcci(), response, documentMetaData.getProfile() );

        // Set the view results here.
        GimletResponseV2 viewObject = viewService.toViewObject( response );
        documentMetaData.getDeferredResult().setResult( new ResponseEntity<>( viewObject, HttpStatus.OK ) );
    }
}