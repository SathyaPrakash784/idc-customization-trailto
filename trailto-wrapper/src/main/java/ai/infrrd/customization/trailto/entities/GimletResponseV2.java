package ai.infrrd.customization.trailto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class GimletResponseV2 implements Serializable
{
    /**
     * 
     */
    @JsonIgnore
    private String profile;
    private static final long serialVersionUID = 1L;
    private String status;
    private ScanRequestVOV2 data;
    @JsonIgnore
    private Long uploadDateTime;
    @JsonProperty ( access = Access.WRITE_ONLY)
    private int historySize;


    protected GimletResponseV2()
    {}


    //for view object
    public GimletResponseV2( String status, String scanId, String imageUrl, String extractionConfidence,
        Map<String, Object> fields, List<Object> lineItems, int licenseConsumed ,String rawText)
    {
        this.status = status;

        ScanRequestVOV2 viewV2 = new ScanRequestVOV2();
        this.data = viewV2;
        viewV2.setScanId( scanId );
        viewV2.setImageUrl( imageUrl );
        viewV2.setExtractionConfidence( extractionConfidence );
        viewV2.setFields( fields );
        viewV2.setLineItems( lineItems );
        viewV2.setLicenseConsumed( licenseConsumed );
        viewV2.setRawText( ( rawText == null || rawText.isEmpty() ) ? null : rawText );
    }


    public GimletResponseV2( ScanRequest scanRequest )
    {
        this.status = scanRequest.getStatus();
        this.data = new ScanRequestVOV2();
        this.data.setScanId( scanRequest.getScanRequestId() );
        this.data.setImageUrl( scanRequest.getS3ImagePath() );
    }


    public GimletResponseV2( String profile, String status, ScanRequestVOV2 response )
    {
        this.profile = profile;
        this.status = status;
        this.setData( response );
    }


    public String getStatus()
    {
        return status;
    }


    public void setStatus( String status )
    {
        this.status = status;
    }


    public ScanRequestVOV2 getData()
    {
        return data;
    }


    public void setData( ScanRequestVOV2 data )
    {
        this.data = data;
    }


    public Long getUploadDateTime()
    {
        return uploadDateTime;
    }


    public void setUploadDateTime( Long uploadDateTime )
    {
        this.uploadDateTime = uploadDateTime;
    }


    public String getProfile()
    {
        return profile;
    }


    public void setProfile( String profile )
    {
        this.profile = profile;
    }


    public int getHistorySize()
    {
        return historySize;
    }


    public void setHistorySize( int historySize )
    {
        this.historySize = historySize;
    }


    @Override
    public String toString()
    {
        return "GimletResponseV2 [status=" + status + ", data=" + data + ", uploadDateTime=" + uploadDateTime + "]";
    }


}
