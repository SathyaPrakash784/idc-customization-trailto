package ai.infrrd.customization.trailto.exceptions;

public class ScanIdNotFoundException extends InvalidInputException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public ScanIdNotFoundException()
    {
        super();
    }


    public ScanIdNotFoundException( String message )
    {
        super( message );
    }


    public ScanIdNotFoundException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
