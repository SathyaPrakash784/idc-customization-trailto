package ai.infrrd.customization.trailto.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Scan request object
 */
public class ScanRequest implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String scanRequestId;
    private String userId;
    private String fileType;
    private String documentType;
    private String locale;
    private String s3ImagePath;
    private Date uploadDateTime;
    private Date lastModifyDateTime;
    private String status;
    private String category;
    private Map<String, Object> categoryDetails;
    private String possibleLanguages;

    private Map<String, Object> receiptData;
    private Map<String, Object> tabularData;
    private Map<String, Object> localeEntity;

    private Map<String, Object> updatedStructure;
    private double ocrConfidence;

    private String profile;

    private ScanRequestAdditionalInfo scanRequestAdditionalInfo;
    private int pageCount;


    public ScanRequest()
    {}


    public String getScanRequestId()
    {
        return scanRequestId;
    }


    public void setScanRequestId( String scanRequestId )
    {
        this.scanRequestId = scanRequestId;
    }


    public String getUserId()
    {
        return userId;
    }


    public void setUserId( String userId )
    {
        this.userId = userId;
    }


    public String getFileType()
    {
        return fileType;
    }


    public void setFileType( String fileType )
    {
        this.fileType = fileType;
    }


    public String getDocumentType()
    {
        return documentType;
    }


    public void setDocumentType( String documentType )
    {
        this.documentType = documentType;
    }


    public String getLocale()
    {
        return locale;
    }


    public void setLocale( String locale )
    {
        this.locale = locale;
    }


    public String getS3ImagePath()
    {
        return s3ImagePath;
    }


    public void setS3ImagePath( String s3ImagePath )
    {
        this.s3ImagePath = s3ImagePath;
    }


    public Date getUploadDateTime()
    {
        return uploadDateTime;
    }


    public void setUploadDateTime( Date uploadDateTime )
    {
        this.uploadDateTime = uploadDateTime;
    }


    public Date getLastModifyDateTime()
    {
        return lastModifyDateTime;
    }


    public void setLastModifyDateTime( Date lastModifyDateTime )
    {
        this.lastModifyDateTime = lastModifyDateTime;
    }


    public String getStatus()
    {
        return status;
    }


    public void setStatus( String status )
    {
        this.status = status;
    }


    public String getCategory()
    {
        return category;
    }


    public void setCategory( String category )
    {
        this.category = category;
    }


    public Map<String, Object> getCategoryDetails()
    {
        return categoryDetails;
    }


    public void setCategoryDetails( Map<String, Object> categoryDetails )
    {
        this.categoryDetails = categoryDetails;
    }


    public String getPossibleLanguages()
    {
        return possibleLanguages;
    }


    public void setPossibleLanguages( String possibleLanguages )
    {
        this.possibleLanguages = possibleLanguages;
    }


    public Map<String, Object> getReceiptData()
    {
        return receiptData;
    }


    public void setReceiptData( Map<String, Object> receiptData )
    {
        this.receiptData = receiptData;
    }


    public Map<String, Object> getTabularData()
    {
        return tabularData;
    }


    public void setTabularData( Map<String, Object> tabularData )
    {
        this.tabularData = tabularData;
    }


    public Map<String, Object> getLocaleEntity()
    {
        return localeEntity;
    }


    public void setLocaleEntity( Map<String, Object> localeEntity )
    {
        this.localeEntity = localeEntity;
    }


    public Map<String, Object> getUpdatedStructure()
    {
        return updatedStructure;
    }


    public void setUpdatedStructure( Map<String, Object> updatedStructure )
    {
        this.updatedStructure = updatedStructure;
    }


    public double getOcrConfidence()
    {
        return ocrConfidence;
    }


    public void setOcrConfidence( double ocrConfidence )
    {
        this.ocrConfidence = ocrConfidence;
    }


    public String getProfile()
    {
        return profile;
    }


    public void setProfile( String profile )
    {
        this.profile = profile;
    }


    public ScanRequestAdditionalInfo getScanRequestAdditionalInfo()
    {
        return scanRequestAdditionalInfo;
    }


    public void setScanRequestAdditionalInfo( ScanRequestAdditionalInfo scanRequestAdditionalInfo )
    {
        this.scanRequestAdditionalInfo = scanRequestAdditionalInfo;
    }


    public int getPageCount()
    {
        return pageCount;
    }


    public void setPageCount( int pageCount )
    {
        this.pageCount = pageCount;
    }


    @Override
    public String toString()
    {
        return "ScanRequest{" + "scanRequestId='" + scanRequestId + '\'' + ", userId='" + userId + '\'' + ", fileType='"
            + fileType + '\'' + ", documentType='" + documentType + '\'' + ", locale='" + locale + '\'' + ", s3ImagePath='"
            + s3ImagePath + '\'' + ", uploadDateTime=" + uploadDateTime + ", lastModifyDateTime=" + lastModifyDateTime
            + ", status='" + status + '\'' + ", category='" + category + '\'' + ", categoryDetails=" + categoryDetails
            + ", possibleLanguages='" + possibleLanguages + '\'' + ", receiptData=" + receiptData + ", tabularData="
            + tabularData + ", localeEntity=" + localeEntity + ", updatedStructure=" + updatedStructure + ", ocrConfidence="
            + ocrConfidence + ", profile='" + profile + '\'' + ", scanRequestAdditionalInfo=" + scanRequestAdditionalInfo
            + ", pageCount=" + pageCount + '}';
    }
}
