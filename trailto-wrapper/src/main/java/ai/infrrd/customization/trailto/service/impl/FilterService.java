package ai.infrrd.customization.trailto.service.impl;

import ai.infrrd.customization.trailto.entities.GimletResponseV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service public class FilterService
{

    public static final Logger LOG = LoggerFactory.getLogger( FilterService.class );
    public static final List<String> INVALID_LINE_ITEM_REGEX = Arrays
        .asList( "(?i)\\bcontainer[ ]{0,4}deposit\\b", "(?i)\\bregu(i)?(1|l)a(r|t)([ ]{0,4}price)?\\b",
            "(?i)\\bregu(i)?(1|l)a(r|t)ly\\b", "(?i)\\b(tax|gst|sub[ ]{0,4}total)\\b" );


    public void filterGimletResponse( GimletResponseV2 response )
    {

        LOG.info( "filterring of trailto gimlet response started" );
        if ( response == null ) {
            LOG.warn( "empty response" );
            return;
        }

        //filter line items by removing unwanted strings
        List<Object> wrapperLineItems = response.getData().getLineItems();
        if ( response.getData() != null && wrapperLineItems != null && !wrapperLineItems.isEmpty() ) {
            wrapperLineItems.removeIf( x -> {
                if ( x == null ) {
                    return true;
                }

                Map<String, Object> item = (Map<String, Object>) x;
                if ( item.get( "rawText" ) != null ) {
                    String rawText = (String) item.get( "rawText" );
                    for ( String regex : INVALID_LINE_ITEM_REGEX ) {
                        Pattern pattern = Pattern.compile( regex );
                        Matcher matcher = pattern.matcher( rawText );
                        if ( matcher.find() ) {
                            //if the invalid string is a part of lineItem then remove only the invalid content from line item else remove entire line item.
                            if ( rawText.contains( "\n" ) ) {
                                double otherContent = Double.MIN_VALUE;
                                String[] splittedRawText = rawText.split( "\\n" );
                                for ( String line : splittedRawText ) {
                                    if ( !line.contains( matcher.group() ) ) {
                                        rawText = line;
                                    } else {
                                        line = line.replaceAll( "[^\\d^.]", "" );
                                        try {
                                            otherContent = Double.valueOf( line );
                                        } catch ( NumberFormatException e ) {
                                            LOG.warn( "error occured while converting other contents to double" );
                                        }
                                    }
                                }
                                item.put( "rawText", rawText );

                                String productName = (String) item.get( "productName" );
                                if ( productName != null && !productName.isEmpty() ) {
                                    productName = productName.replace( matcher.group(), "" ).trim();
                                    item.put( "productName", productName );
                                }

                                if ( otherContent != Double.MIN_VALUE && item.get( "finalPrice" ) != null
                                    && otherContent == Double.valueOf( (Double) item.get( "finalPrice" ) ) ) {
                                    item.put( "finalPrice", 0d );
                                }
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }

                return false;
            } );

            //fix line no of each lineItems after filterring
            if ( wrapperLineItems != null && !wrapperLineItems.isEmpty() ) {
                int count = 1;
                for ( Object item : wrapperLineItems ) {
                    ( (Map<String, Object>) item ).put( "lineNumber", ( count++ * 1.0 ) );
                }
            }
        }
        response.getData().setWrapperFields( response.getData().getFields() );
        response.getData().setWrapperLineItems( wrapperLineItems );
    }
}
