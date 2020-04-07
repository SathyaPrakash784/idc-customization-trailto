package ai.infrrd.customization.trailto.service;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;


public interface KafkaStreamService
{
    void sendMessage( String message );


    void streamUpdatedResponse( String xcci, GimletResponseV2 gimletResponseV2, String profile );

}
