package ai.infrrd.customization.trailto.entities;

import java.util.List;


public class ExtractionParameters
{
    private List<String> additionalFields;
    private List<String> additionalLineFields;
    private List<String> additionalStoreDetails;
    private boolean absConfidence;
    private boolean text;
    private boolean orchestrator;
    private boolean getLines;
    private boolean fixedHeaders;
    private boolean getCoordinates;


    public List<String> getAdditionalLineFields()
    {
        return additionalLineFields;
    }


    public void setAdditionalLineFields( List<String> additionalLineFields )
    {
        this.additionalLineFields = additionalLineFields;
    }


    public boolean isFixedHeaders()
    {
        return fixedHeaders;
    }


    public void setFixedHeaders( boolean fixedHeaders )
    {
        this.fixedHeaders = fixedHeaders;
    }


    public boolean isGetLines()
    {
        return getLines;
    }


    public void setGetLines( boolean getLines )
    {
        this.getLines = getLines;
    }


    public boolean isOrchestrator()
    {
        return orchestrator;
    }


    public void setOrchestrator( boolean orchestrator )
    {
        this.orchestrator = orchestrator;
    }


    public boolean isAbsConfidence()
    {
        return absConfidence;
    }


    public void setAbsConfidence( boolean absConfidence )
    {
        this.absConfidence = absConfidence;
    }


    public List<String> getAdditionalFields()
    {
        return additionalFields;
    }


    public void setAdditionalFields( List<String> fields )
    {
        this.additionalFields = fields;
    }


    public boolean isText()
    {
        return text;
    }


    public void setText( boolean text )
    {
        this.text = text;
    }


    public boolean isGetCoordinates()
    {
        return getCoordinates;
    }


    public void setGetCoordinates( boolean getCoordinates )
    {
        this.getCoordinates = getCoordinates;
    }


    public List<String> getAdditionalStoreDetails()
    {
        return additionalStoreDetails;
    }


    public void setAdditionalStoreDetails( List<String> additionalStoreDetails )
    {
        this.additionalStoreDetails = additionalStoreDetails;
    }


    @Override
    public String toString()
    {
        return "ExtractionParameters [additionalFields=" + additionalFields + ", additionalLineFields=" + additionalLineFields
            + ", additionalStoreDetails=" + additionalStoreDetails + ", absConfidence=" + absConfidence + ", text=" + text
            + ", orchestrator=" + orchestrator + ", getLines=" + getLines + ", fixedHeaders=" + fixedHeaders
            + ", getCoordinates=" + getCoordinates + "]";
    }


}
