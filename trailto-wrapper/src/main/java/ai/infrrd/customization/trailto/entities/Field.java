package ai.infrrd.customization.trailto.entities;

public class Field<T>
{

    private T value;
    private double confidence = 0.0;
    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;


    public Field( T value, double confidence )
    {
        this.value = value;
        this.confidence = confidence;
    }


    public T getValue()
    {
        return value;
    }


    public void setValue( T value )
    {
        this.value = value;
    }


    public double getConfidence()
    {
        return confidence;
    }


    public void setConfidence( double confidence )
    {
        this.confidence = confidence;
    }


    public int getStartX()
    {
        return startX;
    }


    public void setStartX( int startX )
    {
        this.startX = startX;
    }


    public int getStartY()
    {
        return startY;
    }


    public void setStartY( int startY )
    {
        this.startY = startY;
    }


    public int getEndX()
    {
        return endX;
    }


    public void setEndX( int endX )
    {
        this.endX = endX;
    }


    public int getEndY()
    {
        return endY;
    }


    public void setEndY( int endY )
    {
        this.endY = endY;
    }


    @Override
    public String toString()
    {
        return "Field [value=" + value + ", confidence=" + confidence + ", startX=" + startX + ", startY=" + startY + ", endX="
            + endX + ", endY=" + endY + "]";
    }

}
