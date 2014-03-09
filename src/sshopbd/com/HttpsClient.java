package sshopbd.com ;

import java.io.BufferedReader ;
import java.io.DataOutputStream ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.net.URL ;
import java.net.HttpURLConnection ;

import org.apache.commons.codec.binary.Base64 ;

import android.content.Context ;
import android.widget.Toast ;

 
public class HttpsClient {
	private Context parentContext ;
	private String exceptionMsg ;
	
	private HttpsClient() {
	}
	
	public HttpsClient( MainActivity cParam ) {
		this.parentContext = cParam ;
		this.exceptionMsg = "" ;
	}
	
	public HttpsClient( SearchDonorActivity cParam ) {
		this.parentContext = cParam ;
		this.exceptionMsg = "" ;
	}
	
	public HttpsClient( UserRegistrationActivity cParam ) {
		this.parentContext = cParam ;
		this.exceptionMsg = "" ;
	}
	
	public HttpsClient( UserProfileActivity cParam ) {
		this.parentContext = cParam ;
		this.exceptionMsg = "" ;
	}
	
	public String getExceptionMessage() {
		return this.exceptionMsg ;
	}
    
    public void printLine( String msg ) {
        System.out.println( msg.toString() ) ;
    }
    
    public String excutePost( String targetURL , String urlParameters ) {
    	URL url ;
    	HttpURLConnection connection = null ;  
        StringBuffer response ;
        response = new StringBuffer() ;
        try {            
            //Create connection
        	url = new URL( targetURL ) ;
        	connection = ( HttpURLConnection ) url.openConnection() ; // open connection 
            connection.setRequestMethod( "POST" ) ;
            connection.setRequestProperty( "Content-Type" , "application/x-www-form-urlencoded; charset=UTF-8" ) ;
            connection.setRequestProperty( "Content-Length" , "" + Integer.toString( urlParameters.getBytes().length ) ) ;
            connection.setRequestProperty( "Content-Language" , "en-US" ) ;
            String username = "amass!bb" ;
            String password = "@mass@bloodbnk" ;
            String userpassword = username + ":" + password ;
            byte[] encodedBytes = Base64.encodeBase64( userpassword.getBytes() ) ;
            String encodedAuthorization = new String( encodedBytes ) ;
            connection.setRequestProperty( "Authorization" , "Basic " + encodedAuthorization ) ;
            connection.setUseCaches ( false ) ;
            connection.setDoInput( true ) ;
            connection.setDoOutput( true ) ;
            //Send request
            DataOutputStream wr = new DataOutputStream( connection.getOutputStream() ) ;
            wr.writeBytes( urlParameters ) ;
            wr.flush() ;
            wr.close() ;
            //Get Response
            InputStream is = connection.getInputStream() ;
            BufferedReader rd = new BufferedReader( new InputStreamReader( is ) ) ;
            String line ;
            response = new StringBuffer() ;
            while( ( line = rd.readLine() ) != null ) {
                response.append( line ) ;
                response.append( '\r' ) ;
            }
            rd.close() ;
            return response.toString() ;
        } 
        catch( Exception ex ) {
        	this.exceptionMsg = "Please connect to the internet!" + ex.toString() ;
        }    
        finally {
            if( connection != null ) {
                connection.disconnect() ;
            }
        }
        return null ;
    }
}