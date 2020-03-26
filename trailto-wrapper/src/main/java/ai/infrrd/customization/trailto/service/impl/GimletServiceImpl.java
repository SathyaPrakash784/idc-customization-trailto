package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.apis.GimletApiIntegrationBuilder;
import ai.infrrd.customization.trailto.commons.AppConstants;
import ai.infrrd.customization.trailto.commons.FieldName;
import ai.infrrd.customization.trailto.entities.ExtractionParameters;
import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import ai.infrrd.customization.trailto.exceptions.*;
import ai.infrrd.customization.trailto.service.GimletService;
import ai.infrrd.customization.trailto.utils.ByteOperations;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service public class GimletServiceImpl implements GimletService
{

    private static final Logger LOG = LoggerFactory.getLogger( GimletServiceImpl.class );
    GimletApiIntegrationBuilder gimletApi;
    private static HashMap<String, ExtractionParameters> profileParameters;


    @Autowired public void setGimletApi( GimletApiIntegrationBuilder gimletApi )
    {
        this.gimletApi = gimletApi;
    }


    static {
        profileParameters = new HashMap<>();

        ExtractionParameters receiptParameters = new ExtractionParameters();
        receiptParameters.setAbsConfidence( true );
        List<String> receiptFields = Arrays
            .asList( FieldName.STORE_DETAILS, FieldName.BILLING_DATE_ISO, FieldName.BILLING_TIME, FieldName.RECEIPT_NUMBER )
            .stream().distinct().collect( Collectors.toList() );
        receiptParameters.setAdditionalFields( receiptFields );

        receiptParameters.setGetLines( true );
        receiptParameters.setText( true );

        profileParameters.put( AppConstants.RECEIPT_PROFILE, receiptParameters );

        if ( LOG.isInfoEnabled() ) {
            //Will be executed only once.
            LOG.info( "Initialized gimlet invoice extraction parameters: \n {}",
                new GsonBuilder().setPrettyPrinting().create().toJson( receiptParameters ) );
        }
    }


    @Override public GimletResponseV2 postGimlet( String xcci, byte[] fileByteArray, String scanId, String profile )
    {
        LOG.info( "Posting file to gimlet with scanId {}", scanId );
        GimletResponseV2 responseV2 = null;

        RequestBody extractionParametersBody = RequestBody
            .create( MultipartBody.FORM, new Gson().toJson( profileParameters.get( profile ) ) );
        RequestBody uploadFile = RequestBody
            .create( MediaType.parse( ByteOperations.getMimeType( fileByteArray ) ), fileByteArray );
        MultipartBody.Part image = MultipartBody.Part.createFormData( "file", "file", uploadFile );
        Call<GimletResponseV2> gimletRequestCall = gimletApi.getGimletApiService()
            .postDocument( xcci, profile, scanId, true, true, image, extractionParametersBody );

        Response<GimletResponseV2> gimletResponse;
        try {
            gimletResponse = gimletRequestCall.execute();
            if ( gimletResponse.isSuccessful() ) {
                responseV2 = gimletResponse.body();
                long uploadTime = getUploadTime( gimletResponse );
                responseV2.setUploadDateTime( uploadTime );
                LOG.debug( "Gimlet extraction status {} for scanId {}", responseV2.getStatus(), scanId );
            } else if ( gimletResponse.code() == HttpStatus.REQUEST_TIMEOUT.value() ) {
                throw new GimletRequestTimeoutException( "Processing timeout!!",
                    gimletResponse.errorBody().source().readUtf8() );
            } else {
                String errorBody =
                    gimletResponse.errorBody() == null ? "`NULL` ERROR BODY" : gimletResponse.errorBody().source().readUtf8();
                throw new GimletException( "Gimlet post returned error response!!", errorBody );
            }
        } catch ( IOException ioe ) {
            throw new ServiceUnreachableException( "IO Exception occured while posting file to gimlet!!", ioe );
        }
        return responseV2;
    }


    @Override public String generateScanId( String profile )
    {
        LOG.info( "Getting scan id." );
        Call<String> serviceCall = gimletApi.getGimletApiService().getGimletScanId( profile );
        Response<String> response;
        String scanId;
        try {
            response = serviceCall.execute();
            if ( response.isSuccessful() ) {
                scanId = response.body();
                LOG.info( "Successfully recieved scanID: {}", scanId );
                return scanId;
            } else {
                String errorBody =
                    response.errorBody() == null ? "`NULL` ERROR BODY " : response.errorBody().source().readUtf8();
                throw new GimletException( "ScanId generation by gimlet failed!!", errorBody );
            }
        } catch ( IOException ioe ) {
            throw new ServiceUnreachableException( "IO Exception occured while calling gimlet for scanID generation", ioe );
        }
    }


    @Override public GimletResponseV2 getByScanId( String scanId, String xcci, String profile )
    {
        LOG.info( "Getting extraction results for  scanId: {}", scanId );
        Call<GimletResponseV2> serviceCall = gimletApi.getGimletApiService().getExtractedData( xcci, profile, scanId, true );
        Response<GimletResponseV2> response;
        try {
            response = serviceCall.execute();
            if ( response.isSuccessful() ) {
                GimletResponseV2 extractedData = response.body();
                LOG.info( "Recieved status: {} for scanID: {}", extractedData.getStatus(), scanId );
                return extractedData;
            } else if ( response.code() == HttpStatus.NOT_FOUND.value() ) {
                throw new ScanIdNotFoundException( "ScanId not found! Please check the scanId and try again.." );
            } else {
                String errorBody =
                    response.errorBody() == null ? "`NULL` ERROR BODY " : response.errorBody().source().readUtf8();
                throw new GimletException( "Unexpected response from gimlet for get call with scanId: " + scanId, errorBody );
            }
        } catch ( IOException ioe ) {
            throw new ServiceUnreachableException( "IO Exception occured while getting extracted data for scanId: " + scanId,
                ioe );
        }
    }


    private long getUploadTime( Response<GimletResponseV2> gimletResponse )
    {
        DateFormat dateFormat = new SimpleDateFormat( AppConstants.UPLOAD_DATE_FORMAT );
        String gimletUploadTime = gimletResponse.headers().get( AppConstants.UPLOAD_DATE_TIME );
        try {
            return dateFormat.parse( gimletUploadTime ).getTime();
        } catch ( ParseException e ) {
            throw new FatalException( "Unable to parse gimlet upload time: ( " + gimletUploadTime + " ) into format : "
                + AppConstants.UPLOAD_DATE_FORMAT, e );
        }
    }

}
