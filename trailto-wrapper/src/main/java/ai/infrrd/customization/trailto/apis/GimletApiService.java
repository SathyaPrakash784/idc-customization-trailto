package ai.infrrd.customization.trailto.apis;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;


public interface GimletApiService
{
    @POST ( "gimlet-web/rest/ocr/v2/{profile}/scanid")
    Call<String> getGimletScanId( @Path ( "profile") String profile );


    @Multipart
    @POST ( "gimlet-web/rest/ocr/v2/{profile}/assign/{scanId}")
    Call<GimletResponseV2> postDocument( @Header ( "X-Consumer-Custom-ID") String apikey, @Path ( "profile") String profile,
        @Path ( "scanId") String scanId, @Query ( "willWait") boolean willWait,
        @Query ( "getUploadTime") boolean isUploadTimeRequired, @Part MultipartBody.Part image,
        @Part ( "extractionParameters") RequestBody extractionParameters );


    @GET ( "gimlet-web/rest/ocr/v2/{profile}/{scanId}")
    Call<GimletResponseV2> getExtractedData( @Header ( "X-Consumer-Custom-ID") String xConsumerCustomId,
        @Path ( "profile") String profile, @Path ( "scanId") String scanId,
        @Query ( "getCorrectedStatus") boolean getCorrectedStatus );
}
