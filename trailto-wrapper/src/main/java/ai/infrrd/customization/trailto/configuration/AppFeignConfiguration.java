package ai.infrrd.customization.trailto.configuration;

import org.springframework.context.annotation.Bean;

import feign.Logger;
import feign.Request;
import feign.codec.ErrorDecoder;


public class AppFeignConfiguration
{
    @Bean
    public ErrorDecoder errorDecoder()
    {
        return new FeignErrorDecoder();
    }


    @Bean
    public Logger.Level logger()
    {
        return Logger.Level.FULL;
    }


    @Bean
    public Request.Options options()
    {
        return new Request.Options( 30000, 30000 );
    }
}