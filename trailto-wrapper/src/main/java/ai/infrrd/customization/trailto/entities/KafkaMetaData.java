package ai.infrrd.customization.trailto.entities;

public class KafkaMetaData
{
    private KafkaDataEntity scanReponseMeta;


    public KafkaMetaData( KafkaDataEntity scanReponseMeta )
    {
        this.scanReponseMeta = scanReponseMeta;
    }


    public KafkaDataEntity getScanReponseMeta()
    {
        return scanReponseMeta;
    }


    public void setScanReponseMeta( KafkaDataEntity scanReponseMeta )
    {
        this.scanReponseMeta = scanReponseMeta;
    }
}

