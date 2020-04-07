package ai.infrrd.customization.trailto.exceptions;

public class ApiGatewayException extends FatalException
{

    public ApiGatewayException()
    {
        super();
    }

    public ApiGatewayException( String message )
    {
        super( message );
    }

    public ApiGatewayException( String message, Throwable thrw )
    {
        super( message, thrw );
    }
}
