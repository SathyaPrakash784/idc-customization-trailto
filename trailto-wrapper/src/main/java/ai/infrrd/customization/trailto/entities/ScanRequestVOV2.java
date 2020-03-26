package ai.infrrd.customization.trailto.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ScanRequestVOV2 implements Serializable
{
    private String scanId;
    private String imageUrl;
    @JsonInclude ( JsonInclude.Include.NON_NULL)
    private String extractionConfidence;

    @JsonSerialize ( using = CustomMapSerializer.class)
    private Map<String, Object> fields;

    private List<Object> lineItems;

    private String rawText;

    @JsonIgnore
    private String status;
    private Integer licenseConsumed;
    @JsonIgnore
    private String hashId;


    public ScanRequestVOV2()
    {}


    public String getStatus()
    {
        return this.status;
    }


    public void setStatus( String status )
    {
        this.status = status;
    }


    public String getScanId()
    {
        return scanId;
    }


    public void setScanId( String scanId )
    {
        this.scanId = scanId;
    }


    public String getImageUrl()
    {
        return imageUrl;
    }


    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }


    public String getExtractionConfidence()
    {
        return extractionConfidence;
    }


    public void setExtractionConfidence( String extractionConfidence )
    {
        this.extractionConfidence = extractionConfidence;
    }


    public Map<String, Object> getFields()
    {
        return fields;
    }


    public void setFields( Map<String, Object> fields )
    {
        this.fields = fields;
    }


    public List<Object> getLineItems()
    {
        return lineItems;
    }


    public void setLineItems( List<Object> lineItems )
    {
        this.lineItems = lineItems;
    }


    public String getRawText()
    {
        return rawText;
    }


    public void setRawText( String rawText )
    {
        this.rawText = rawText;
    }


    public Integer getLicenseConsumed()
    {
        return licenseConsumed;
    }


    public void setLicenseConsumed( Integer licenseConsumed )
    {
        this.licenseConsumed = licenseConsumed;
    }


    public String getHashId()
    {
        return hashId;
    }


    public void setHashId( String hashId )
    {
        this.hashId = hashId;
    }

}


class CustomMapSerializer extends JsonSerializer<Map<String, Object>>
{
    @Override
    public void serialize( Map<String, Object> value, JsonGenerator jgen, SerializerProvider provider ) throws IOException
    {
        jgen.writeStartObject();
        for ( Entry<String, Object> entry : value.entrySet() ) {
            if ( entry.getValue() instanceof Double ) {
                jgen.writeObjectField( entry.getKey(), BigDecimal.valueOf( (double) entry.getValue() ) );
            } else {
                jgen.writeObjectField( entry.getKey(), entry.getValue() );
            }
        }
        jgen.writeEndObject();
    }
}

