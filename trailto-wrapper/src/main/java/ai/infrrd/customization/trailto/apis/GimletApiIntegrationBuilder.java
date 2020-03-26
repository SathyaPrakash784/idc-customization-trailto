package ai.infrrd.customization.trailto.apis;

import ai.infrrd.customization.trailto.configuration.CustomMapDeserializer;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Component
public class GimletApiIntegrationBuilder
{
    private static final Logger LOGGER = LoggerFactory.getLogger( GimletApiIntegrationBuilder.class );
    private static final int READ_TIMEOUT = 70;

    private GimletApiService gimletApiService;

    @Value ( "${apiurl.gimlet}")
    private String gimletUrl;


    public GimletApiService getGimletApiService()
    {
        return gimletApiService;
    }


    @PostConstruct
    public void afterPropertiesSet()
    {
        LOGGER.info( "Initializing Gimlet API rest builder with url '{}' and read timeout {} seconds", gimletUrl,
            READ_TIMEOUT );
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(new TypeToken<Map<String, Object>>(){}.getType(), new CustomMapDeserializer() );
        OkHttpClient client = new OkHttpClient.Builder().readTimeout( READ_TIMEOUT, TimeUnit.SECONDS ).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl( gimletUrl ).client( client )
            .addConverterFactory( GsonConverterFactory.create( gson.setLenient().create() ) ).build();
        gimletApiService = retrofit.create( GimletApiService.class );
    }
}
