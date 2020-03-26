package ai.infrrd.customization.trailto.vo;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class ScanDataResponseVO extends GimletResponseV2 implements Serializable
{

    @JsonIgnore
    private List<Map<String, Object>> histories;
    @JsonIgnore
    private long lastModifyDateTime;

    public ScanDataResponseVO()
    {
        super();
    }

    public List<Map<String, Object>> getHistories()
    {
        return histories;
    }


    public void setHistories( List<Map<String, Object>> histories )
    {
        this.histories = histories;
    }


    public long getLastModifyDateTime()
    {
        return lastModifyDateTime;
    }


    public void setLastModifyDateTime( long lastModifyDateTime )
    {
        this.lastModifyDateTime = lastModifyDateTime;
    }
}
