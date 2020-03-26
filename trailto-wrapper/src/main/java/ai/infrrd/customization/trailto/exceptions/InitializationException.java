package ai.infrrd.customization.trailto.exceptions;

public class InitializationException extends FatalException
{


    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public InitializationException()
    {
        super();
    }


    public InitializationException( String message )
    {
        super( message );
    }


    public InitializationException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
