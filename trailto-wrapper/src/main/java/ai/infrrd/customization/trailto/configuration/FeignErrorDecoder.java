package ai.infrrd.customization.trailto.configuration;

import ai.infrrd.customization.trailto.exceptions.ApiGatewayException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


public class FeignErrorDecoder implements ErrorDecoder
{
    private static final Logger LOG = LoggerFactory.getLogger( FeignErrorDecoder.class );


    @Override
    public Exception decode( String methodKey, Response response )
    {
        LOG.warn( "Got response code {} from method key: {}", response.status(), methodKey );
        if ( response.status() == HttpStatus.SERVICE_UNAVAILABLE.value() ) {
            return new ApiGatewayException( "Api gateway is unavailable. response code: " + response.status() );
        } else if ( response.status() == HttpStatus.BAD_REQUEST.value() ) {
            throw new ApiGatewayException( "Api gateway returned BAD_REQUEST..!! " + response.status() );
        } else {
            LOG.warn( "Api gateway returned unknown response code: {}", response.status() );
            throw new ApiGatewayException(
                "Unknown response from api gateway. Api gateway returned code: " + response.status() );
        }
    }
}