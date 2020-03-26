package ai.infrrd.customization.trailto.entities;

import java.io.Serializable;
import java.util.List;


public class BaseScanRequestAdditionalInfo implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    protected List<String> additionalFields;
    protected Boolean enableMerchantEnrichment;
    protected Boolean enableMerchantLookup;
    protected Boolean getLines;
    protected Boolean text;
    protected Boolean enableLogoDetection;
    protected boolean getCoordinates;
    protected boolean absConfidence;
    protected boolean orchestrator;


    public List<String> getAdditionalFields()
    {
        return additionalFields;
    }


    public void setAdditionalFields( List<String> additionalFields )
    {
        this.additionalFields = additionalFields;
    }


    public Boolean getEnableMerchantEnrichment()
    {
        return enableMerchantEnrichment;
    }


    public void setEnableMerchantEnrichment( Boolean enableMerchantEnrichment )
    {
        this.enableMerchantEnrichment = enableMerchantEnrichment;
    }


    public Boolean getEnableMerchantLookup()
    {
        return enableMerchantLookup;
    }


    public void setEnableMerchantLookup( Boolean enableMerchantLookup )
    {
        this.enableMerchantLookup = enableMerchantLookup;
    }


    public Boolean getGetLines()
    {
        return getLines;
    }


    public void setGetLines( Boolean getLines )
    {
        this.getLines = getLines;
    }


    public Boolean getText()
    {
        return text;
    }


    public void setText( Boolean text )
    {
        this.text = text;
    }


    public Boolean getEnableLogoDetection()
    {
        return enableLogoDetection;
    }


    public void setEnableLogoDetection( Boolean enableLogoDetection )
    {
        this.enableLogoDetection = enableLogoDetection;
    }


    public boolean isGetCoordinates()
    {
        return getCoordinates;
    }


    public void setGetCoordinates( boolean getCoOrdinates )
    {
        this.getCoordinates = getCoOrdinates;
    }


    public boolean getAbsConfidence()
    {
        return absConfidence;
    }


    public void setAbsConfidence( boolean absConfidence )
    {
        this.absConfidence = absConfidence;
    }


    public boolean getOrchestrator()
    {
        return orchestrator;
    }


    public void setOrchestrator( boolean orchestrator )
    {
        this.orchestrator = orchestrator;
    }


    @Override
    public String toString()
    {
        return "BaseScanRequestAdditionalInfo {additionalFields=" + additionalFields + ", enableMerchantEnrichment="
            + enableMerchantEnrichment + ", enableMerchantLookup=" + enableMerchantLookup + ", getLines=" + getLines + ", text="
            + text + ", enableLogoDetection=" + enableLogoDetection + ", getCoordinates=" + getCoordinates + ", absConfidence="
            + absConfidence + ", orchestrator=" + orchestrator + "}";
    }
}
