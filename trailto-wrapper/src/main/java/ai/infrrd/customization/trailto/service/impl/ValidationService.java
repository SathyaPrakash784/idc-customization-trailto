package ai.infrrd.customization.trailto.service.impl;


import ai.infrrd.customization.trailto.exceptions.InvalidInputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import static ai.infrrd.customization.trailto.utils.ByteOperations.getByteArray;
import static ai.infrrd.customization.trailto.utils.ByteOperations.getMimeType;


@Component
public class ValidationService
{

    private static final Long MAX_FILE_SIZE = 10000000L;
    private static final Long OneMBInBytes = 1000000L;

    private static final Logger LOG = LoggerFactory.getLogger( ValidationService.class );


    public boolean validate( MultipartFile file )
    {
        LOG.info( "Validating multipart file: {}", file );
        if ( file == null ) {
            LOG.warn( "Recieved null multipart file!!" );
            throw new InvalidInputException( "Unable to find the document! Please attach a file." );
        }
        String fileType = getMimeType( getByteArray( file ) );

        if ( file.getSize() > MAX_FILE_SIZE ) {
            double fileSizeInMB = (double) file.getSize() / OneMBInBytes;
            String fileSizeStr = String.format( "%.2f", fileSizeInMB ) + " MB";
            throw new InvalidInputException( "File size: " + fileSizeStr + " is too large.!!" );
        }
        LOG.info( "Filetype: {}", fileType );
        boolean isValidType = isValidInput( fileType );
        if ( !isValidType ) {
            LOG.warn( "Recieved filetype: {} is not supported!!", fileType );
            throw new InvalidInputException( "Filetype: " + fileType + " is not supported!!" );
        }


        return isValidType;
    }


    public static boolean isValidInput( String fileType )
    {
        switch ( fileType ) {
            case "image/png":
            case "image/jpg":
            case "image/jpeg":
            case "image/bmp":
                return true;
            default:
                return false;
        }
    }

}
