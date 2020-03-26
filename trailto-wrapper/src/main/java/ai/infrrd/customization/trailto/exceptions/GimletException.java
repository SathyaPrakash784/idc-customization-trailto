package ai.infrrd.customization.trailto.exceptions;

public class GimletException extends FatalException

{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private final String errorBody;


    public GimletException( String message, String errorBody )
    {
        super( message );
        this.errorBody = errorBody;
    }


    public String getErrorBody()
    {
        return errorBody;
    }
}
