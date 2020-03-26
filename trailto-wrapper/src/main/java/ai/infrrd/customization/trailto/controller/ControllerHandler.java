package ai.infrrd.customization.trailto.controller;

import ai.infrrd.customization.trailto.entities.ErrorResponse;
import ai.infrrd.customization.trailto.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerHandler
{
    private static final Logger LOG = LoggerFactory.getLogger( ControllerHandler.class );
    private static final String UNEXPECTED_ERROR = "UNEXPECTED_ERROR";
    private static final String BAD_REQUEST = "BAD_REQUEST";
    private static final String NOT_FOUND = "NOT_FOUND";

    private static final String REQUEST_TIMEOUT = "REQUEST_TIMEOUT";
    private static final String INITIALIZATION_EXCEPTION = "INITIALIZATION_FAILED";


    @ExceptionHandler ( ScanIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> invalidScanId( ScanIdNotFoundException e )
    {
        LOG.error( "ScanId Not Found Exception handled {}", e );
        return new ResponseEntity<>( new ErrorResponse( NOT_FOUND, e.getMessage() ), HttpStatus.NOT_FOUND );
    }


    @ExceptionHandler ( InvalidInputException.class)
    public ResponseEntity<ErrorResponse> handleWrongRequest( InvalidInputException e )
    {
        LOG.error( "Invalid Input Exception handled {}", e );
        return new ResponseEntity<>( new ErrorResponse( BAD_REQUEST, e.getMessage() ), HttpStatus.BAD_REQUEST );
    }


    @ExceptionHandler ( ServiceUnreachableException.class)
    public ResponseEntity<ErrorResponse> serviceUnreachableHandler( FatalException unreachableException )
    {
        LOG.error( "ServiceUnreachableException handled {}", unreachableException );
        return new ResponseEntity<>(
            new ErrorResponse( UNEXPECTED_ERROR, "Could not connect to underlying service. Please try after some time." ),
            HttpStatus.INTERNAL_SERVER_ERROR );
    }


    @ExceptionHandler ( GimletRequestTimeoutException.class)
    public ResponseEntity<ErrorResponse> gimletRequestTimeoutHandler( GimletRequestTimeoutException e )
    {
        LOG.error( "Request timeout! {}", e );
        return new ResponseEntity<>( new ErrorResponse( REQUEST_TIMEOUT, "Please try again!" ), HttpStatus.REQUEST_TIMEOUT );
    }


    @ExceptionHandler ( GimletException.class)
    public ResponseEntity<ErrorResponse> gimletExceptionHandler( GimletException e )
    {
        LOG.error( "Unexpected response from gimlet! {}", e );
        return new ResponseEntity<>( new ErrorResponse( UNEXPECTED_ERROR, "Internal service failure!" ),
            HttpStatus.INTERNAL_SERVER_ERROR );
    }


    @ExceptionHandler ( InitializationException.class)
    public ResponseEntity<ErrorResponse> initializationExceptionHandler( InitializationException e )
    {
        LOG.error( "Failed to initialize varaibles! {}", e );
        return new ResponseEntity<>(
            new ErrorResponse( INITIALIZATION_EXCEPTION, "Failed to initialize variables (maybe static)" ),
            HttpStatus.INTERNAL_SERVER_ERROR );
    }


    @ExceptionHandler ( FatalException.class)
    public ResponseEntity<ErrorResponse> fatalExceptionHandler( FatalException e )
    {
        LOG.error( "Fatal exception handled {}", e );
        return new ResponseEntity<>( new ErrorResponse( UNEXPECTED_ERROR, "An unexpected error occured! Please try again." ),
            HttpStatus.INTERNAL_SERVER_ERROR );
    }


    @ExceptionHandler ( Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler( Exception e )
    {
        LOG.error( "Unknown Generic exception handled {}", e );
        return new ResponseEntity<>( new ErrorResponse( UNEXPECTED_ERROR, "An unexpected error occured! Please try again." ),
            HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
