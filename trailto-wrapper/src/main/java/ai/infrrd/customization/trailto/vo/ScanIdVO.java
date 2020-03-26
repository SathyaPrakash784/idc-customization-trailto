package ai.infrrd.customization.trailto.vo;

public class ScanIdVO
{
    private String scanId;


    public ScanIdVO( String scanId )
    {
        this.scanId = scanId;
    }


    public void setScanId( String scanId )
    {
        this.scanId = scanId;
    }


    public String getScanId()
    {
        return scanId;
    }


    @Override
    public String toString()
    {
        return "ScanIdVO [scanId=" + scanId + "]";
    }
}
