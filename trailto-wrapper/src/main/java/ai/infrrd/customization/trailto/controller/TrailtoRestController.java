package ai.infrrd.customization.trailto.controller;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.service.DocumentProcessingService;
import ai.infrrd.customization.trailto.service.impl.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class TrailtoRestController
{
    private static final Logger LOG = LoggerFactory.getLogger( TrailtoRestController.class );

    @Autowired
    ValidationService validationService;

    @Autowired
    DocumentProcessingService documentService;


    @PostMapping ( value = "/trialto/receipt", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DeferredResult<ResponseEntity<?>> postResponse( @RequestHeader ( "X-Consumer-Custom-ID") String xcci,
        @RequestBody MultipartFile file, @RequestParam ( required = false, defaultValue = "false") boolean willWait )
    {
        LOG.info( "Recieved request with multipart file: {}", file );
        validationService.validate( file );
        return documentService.processDocument( file, xcci, willWait );
    }


    @GetMapping ( value = "/trialto/receipt/{scanId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<GimletResponseV2> getFileData( @PathVariable String scanId,
        @RequestHeader ( "X-Consumer-Custom-ID") String xcci )
    {
        LOG.info( "Getting results for scanId: {}", scanId );
        return new ResponseEntity<>( documentService.getByScanId( scanId, xcci ), HttpStatus.OK );
    }
}
