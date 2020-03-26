package ai.infrrd.customization.trailto.exceptions;

public class ServiceUnreachableException extends FatalException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public ServiceUnreachableException()
    {
        super();
    }


    public ServiceUnreachableException( String message )
    {
        super( message );
    }


    public ServiceUnreachableException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
