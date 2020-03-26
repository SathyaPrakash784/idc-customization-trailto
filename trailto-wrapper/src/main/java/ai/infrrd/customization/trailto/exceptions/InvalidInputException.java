package ai.infrrd.customization.trailto.exceptions;

public class InvalidInputException extends FatalException
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public InvalidInputException()
    {
        super();
    }


    public InvalidInputException( String message )
    {
        super( message );
    }


    public InvalidInputException( String message, Throwable cause )
    {
        super( message, cause );
    }

}
