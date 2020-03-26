package ai.infrrd.customization.trailto.utils;

import ai.infrrd.customization.trailto.exceptions.FatalException;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public class ByteOperations
{
    private ByteOperations()
    {}


    public static byte[] getByteArray( MultipartFile file )
    {
        try {
            return file.getBytes();
        } catch ( IOException ioe ) {
            throw new FatalException( "IO Exception while converting multipart file into byte array.", ioe );
        }
    }


    public static String getMimeType( byte[] fileByteArray )
    {
        return new Tika().detect( fileByteArray );
    }

}