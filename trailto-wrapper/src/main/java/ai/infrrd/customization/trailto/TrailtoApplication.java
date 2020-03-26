package ai.infrrd.customization.trailto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class TrailtoApplication
{

    public static void main( String[] args )
    {
        SpringApplication.run( TrailtoApplication.class, args );
    }

}
