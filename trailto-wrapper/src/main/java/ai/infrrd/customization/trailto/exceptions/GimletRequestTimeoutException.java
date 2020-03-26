package ai.infrrd.customization.trailto.exceptions;

public class GimletRequestTimeoutException extends GimletException
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;


    public GimletRequestTimeoutException( String message, String errorBody )
    {
        super( message, errorBody );
    }

    
}
