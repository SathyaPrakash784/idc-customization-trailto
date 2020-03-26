package ai.infrrd.customization.trailto.service.impl;


import ai.infrrd.customization.trailto.entities.DocumentMetaData;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.service.GimletService;
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


    private GimletService gimletService;

    @Autowired
    public void setGimletService( GimletService gimletService )
    {
        this.gimletService = gimletService;
    }

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
        documentMetaData.getDeferredResult().setResult( new ResponseEntity<>( response, HttpStatus.OK ) );
    }

}