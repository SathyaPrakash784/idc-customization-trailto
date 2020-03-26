package ai.infrrd.customization.trailto.entities;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Arrays;


public class DocumentMetaData
{
    private String xcci;
    private boolean willWait;
    private String scanId;
    private byte[] fileBytes;
    private DeferredResult<ResponseEntity<?>> deferredResult;
    private String profile;


    public DocumentMetaData( String xcci, boolean willWait, String scanId, byte[] fileBytes, String profile )
    {
        super();
        this.xcci = xcci;
        this.willWait = willWait;
        this.scanId = scanId;
        this.fileBytes = fileBytes;
        this.profile = profile;
    }


    public String getXcci()
    {
        return xcci;
    }


    public void setXcci( String xcci )
    {
        this.xcci = xcci;
    }


    public boolean isWillWait()
    {
        return willWait;
    }


    public void setWillWait( boolean willWait )
    {
        this.willWait = willWait;
    }


    public String getScanId()
    {
        return scanId;
    }


    public void setScanId( String scanId )
    {
        this.scanId = scanId;
    }


    public byte[] getFileBytes()
    {
        return fileBytes;
    }


    public void setFileBytes( byte[] fileBytes )
    {
        this.fileBytes = fileBytes;
    }


    public DeferredResult<ResponseEntity<?>> getDeferredResult()
    {
        return deferredResult;
    }


    public void setDeferredResult( DeferredResult<ResponseEntity<?>> deferredResult )
    {
        this.deferredResult = deferredResult;
    }


    public String getProfile()
    {
        return profile;
    }


    public void setProfile( String profile )
    {
        this.profile = profile;
    }


    @Override
    public String toString()
    {
        return "DocumentMetaData [xcci=" + xcci + ", willWait=" + willWait + ", scanId=" + scanId + ", fileBytes="
            + Arrays.toString( fileBytes ) + ", deferredResult=" + deferredResult + ", profile=" + profile + "]";
    }

}

