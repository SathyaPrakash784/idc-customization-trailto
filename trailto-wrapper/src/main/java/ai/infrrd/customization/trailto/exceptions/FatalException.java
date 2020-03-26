package ai.infrrd.customization.trailto.exceptions;

public class FatalException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public FatalException()
    {
        super();
    }


    public FatalException( String message )
    {
        super( message );
    }


    public FatalException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
