package ai.infrrd.customization.trailto.service;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;


public interface GimletService
{

    String generateScanId( String profile );


    GimletResponseV2 postGimlet( String xcci, byte[] fileByteArray, String scanId, String profile );


    GimletResponseV2 getByScanId( String scanId, String xcci, String profile );

}
