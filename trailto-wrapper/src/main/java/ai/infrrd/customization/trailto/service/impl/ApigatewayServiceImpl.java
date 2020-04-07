package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.apis.ApiGatewayFeignService;
import ai.infrrd.customization.trailto.entities.ErrorResponse;
import ai.infrrd.customization.trailto.exceptions.ApiGatewayException;
import ai.infrrd.customization.trailto.service.ApigatewayService;
import ai.infrrd.customization.trailto.vo.ScanDataResponseVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RetryableException;
import feign.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Optional;


@Service
public class ApigatewayServiceImpl implements ApigatewayService
{
    private static final Logger LOG = LoggerFactory.getLogger( ApigatewayServiceImpl.class );
    private ApiGatewayFeignService apiGatewayService;

    @Value ( "${wrapper.xcci}")
    private String wrapperxcci;


    @PostConstruct
    public void init()
    {
        LOG.info( "Initialized ApigatewayServiceImpl with FEIGN version.." );
    }


    @Autowired
    public void setApiGatewayService( ApiGatewayFeignService apiGatewayService )
    {
        this.apiGatewayService = apiGatewayService;
    }

    @Override
    public Optional<ScanDataResponseVO> apigatewayGetCall( String xcci, String scanId )
    {
        String body;
        ScanDataResponseVO scannedData = null;
        try {
            feign.Response response = this.apiGatewayService.getScannedData( wrapperxcci, xcci, scanId );
            body = Util.toString( response.body().asReader() );
            if ( response.status() == HttpStatus.OK.value() ) {
                scannedData = new ObjectMapper().readValue( body, ScanDataResponseVO.class );
            } else {
                ErrorResponse errorResponse = new ObjectMapper().readValue( body, ErrorResponse.class );
                LOG.error( "Got status : {} , exception while getting data from api-gateway :{}", response.status(),
                    errorResponse );
            }
        } catch ( ApiGatewayException | IOException | RetryableException e ) {
            LOG.error( "Exception while calling api-gateway", e );
            return Optional.empty();
        }
        return Optional.ofNullable( scannedData );
    }
}
