package ai.infrrd.customization.trailto.apis;

import ai.infrrd.customization.trailto.configuration.AppFeignConfiguration;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient ( value = "apigateway-service", configuration = AppFeignConfiguration.class)
public interface ApiGatewayFeignService
{
    @GetMapping ( value = "/keygateway/api-gateway/data/get")
    public Response getScannedData( @RequestHeader (name = "X-Consumer-Custom-ID") String xcci,
        @RequestHeader (name = "customer-xcci") String customerXCCI, @RequestHeader (name = "scanId") String scanId );
}
