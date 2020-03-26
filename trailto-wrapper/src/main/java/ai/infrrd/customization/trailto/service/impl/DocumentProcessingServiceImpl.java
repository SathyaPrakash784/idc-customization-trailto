package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.commons.AppConstants;
import ai.infrrd.customization.trailto.entities.DocumentMetaData;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.exceptions.GimletRequestTimeoutException;
import ai.infrrd.customization.trailto.service.DocumentProcessingService;
import ai.infrrd.customization.trailto.service.GimletService;
import ai.infrrd.customization.trailto.utils.ByteOperations;
import ai.infrrd.customization.trailto.vo.ScanIdVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DocumentProcessingServiceImpl implements DocumentProcessingService
{

    private static final Logger LOG = LoggerFactory.getLogger( DocumentProcessingServiceImpl.class );

    private static final Long TIMEOUT = 35000L;

    private GimletService gimletService;

    private WorkerThread workerThread;


    @Autowired
    public void setGimletService( GimletService gimletService )
    {
        this.gimletService = gimletService;
    }

    @Autowired
    public void setWorkerThread( WorkerThread workerThread )
    {
        this.workerThread = workerThread;
    }

    @Override
    public GimletResponseV2 getByScanId( String scanId, String xcci )
    {
        return gimletService.getByScanId( scanId, xcci, AppConstants.RECEIPT_PROFILE );
    }


    @Override
    public DeferredResult<ResponseEntity<?>> processDocument( MultipartFile file, String xcci, boolean willWait )
    {
        LOG.info( "Processing document with willWait: {}", willWait );
        String scanId = gimletService.generateScanId( AppConstants.RECEIPT_PROFILE );

        byte[] byteArray = ByteOperations.getByteArray( file );
        DocumentMetaData documentMetaData = new DocumentMetaData( xcci, willWait, scanId, byteArray,
            AppConstants.RECEIPT_PROFILE );

        return controllerOps( documentMetaData );
    }


    private DeferredResult<ResponseEntity<?>> controllerOps( DocumentMetaData documentMetaData )
    {
        final DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>( TIMEOUT );

        deferredResult.onCompletion( () -> LOG.info( "Scan id {} with willWait:{} completed.", documentMetaData.getScanId(),
            documentMetaData.isWillWait() ) );

        deferredResult.onTimeout( () -> {
            throw new GimletRequestTimeoutException( "WRAPPER TIME OUT AFTER : " + TIMEOUT + " milliseconds",
                "WRAPPER_ASYNC_TIMEOUT" );
        } );


        //if willWait= false, respond with scanId
        if ( !documentMetaData.isWillWait() ) {
            ScanIdVO scanIdVo = new ScanIdVO( documentMetaData.getScanId() );
            deferredResult.setResult( new ResponseEntity<>( scanIdVo, HttpStatus.OK ) );
        } else {
            LOG.info( "Processing with willwait: TRUE for  scanId: {}", documentMetaData.getScanId() );
        }
        documentMetaData.setDeferredResult( deferredResult );
        workerThread.setDocumentMetaData( documentMetaData );
        workerThread.start();
        return deferredResult;
    }

}
