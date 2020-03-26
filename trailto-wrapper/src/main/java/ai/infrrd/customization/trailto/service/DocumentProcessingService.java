package ai.infrrd.customization.trailto.service;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;


public interface DocumentProcessingService
{

    GimletResponseV2 getByScanId( String scanId ,String xcci);


    DeferredResult<ResponseEntity<?>> processDocument( MultipartFile file, String xcci, boolean willWait );


}
