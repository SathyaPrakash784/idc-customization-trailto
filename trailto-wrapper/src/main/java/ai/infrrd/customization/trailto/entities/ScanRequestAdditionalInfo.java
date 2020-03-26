package ai.infrrd.customization.trailto.entities;

import java.io.Serializable;
import java.util.Map;


public class ScanRequestAdditionalInfo extends BaseScanRequestAdditionalInfo implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Map<String, Boolean> fieldsToProcess;


    public Map<String, Boolean> getFieldsToProcess()
    {
        return fieldsToProcess;
    }


    public void setFieldsToProcess( Map<String, Boolean> fieldsToProcess )
    {
        this.fieldsToProcess = fieldsToProcess;
    }


    @Override
    public String toString()
    {
        return "ScanRequestAdditionalInfo {fieldsToProcess=" + fieldsToProcess + ", additionalFields=" + additionalFields
            + ", enableMerchantEnrichment=" + enableMerchantEnrichment + ", enableMerchantLookup=" + enableMerchantLookup
            + ", getLines=" + getLines + ", text=" + text + ", enableLogoDetection=" + enableLogoDetection + ", getCoordinates="
            + getCoordinates + ", absConfidence=" + absConfidence + ", orchestrator=" + orchestrator + "}";
    }
}
