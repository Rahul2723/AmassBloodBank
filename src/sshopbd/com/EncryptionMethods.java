package sshopbd.com;

import java.security.MessageDigest ;
import java.security.NoSuchAlgorithmException ;
import java.util.Random ;
import org.apache.commons.codec.binary.Base64 ;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder ;  
import java.net.URLEncoder ;

import java.security.InvalidKeyException ;
import java.security.NoSuchAlgorithmException ;
 
import javax.crypto.BadPaddingException ;
import javax.crypto.Cipher ;
import javax.crypto.IllegalBlockSizeException ;
import javax.crypto.KeyGenerator ;
import javax.crypto.NoSuchPaddingException ;
import javax.crypto.SecretKey ;

/**
 * Provides all type of encryption for
 * String object.
 * 
 * Sample Usage:
 * 
 * Description: 
 * File Name: EncryptionMethods.java
 * Package Name: com.peincognito.peinframework.controller.security
 * Project Name: PEIN Framework
 * Time: 3:42:51 PM
 * Date: Sep 16, 2013
 * Year: 2013
 * 
 * @author S. M. Ijaz-ul-Amin Chowdhury
 */
public class EncryptionMethods {
	private String error_message ;
	private String des_key ;
	private Cipher des_cipher ;
	
	/**
	 * Default constructor for 
	 * EncryptionMethods.
	 * 
	 * @param args
	 * void
	 */
	public EncryptionMethods() {
		this.error_message = "" ;
		this.des_key = "" ;
	    try {
			this.des_cipher = Cipher.getInstance( "DES/ECB/PKCS5Padding" ) ;
		} 
	    catch( Exception e) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		}
	}
	
	/**
	 * Encrypts the String with md5 hashing.
	 * 
	 * @param arr the String object to be md5 hashed
	 * @return String returns the md5 hashed data
	 */
	public String md5Encryption( String arr ) {
		MessageDigest md ;
		String res ;
		int i ;
        md = null ;
		try {
			md = MessageDigest.getInstance( "MD5" ) ;
		} 
		catch( NoSuchAlgorithmException e ) { 
			this.error_message += e.toString() ;
		}
        md.update( arr.getBytes() ) ;
        byte byteData[] = md.digest() ;
        StringBuffer sb = new StringBuffer() ;
        for( i = 0 ; i < byteData.length ; i++ ) {
        	sb.append( Integer.toString( ( byteData[ i ] & 0xff ) + 0x100 , 16 ).substring( 1 ) ) ;
        }
        res = sb.toString() ;
		return res ;
	}
	
	/**
	 * Encodes the data onto base64 encoded
	 * format.
	 * 
	 * @param data the String object to be
	 * encoded
	 * @return String
	 */
	public String base64Encode( String data ) {
		String result ;		
		byte[] encoded_bytes = Base64.encodeBase64( data.getBytes() ) ;
		result = new String( encoded_bytes ) ;		
		return result ;
	}
	
	/**
	 * Decodes the data onto base64 decoded
	 * format.
	 * 
	 * @param data the String object to be
	 * decoded
	 * @return String
	 */
	public String base64Decode( String data ) {
		String result ;		
		byte[] decoded_bytes = Base64.decodeBase64( data.getBytes() ) ;
		result = new String( decoded_bytes ) ;
		return result ;
	}
	
	/**
	 * Encodes the data onto URL encoded
	 * format.
	 * 
	 * @param data the String object to be
	 * encoded
	 * @return String
	 */
	private String urlEncode( String data ) {
		String result ;
		result = data ;
		try {
			result = URLEncoder.encode( data , "UTF-8" ) ;
		} 
		catch( UnsupportedEncodingException e ) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		} 
		return result ;
	}
	
	/**
	 * Decodes the data onto URL decoded
	 * format.
	 * 
	 * @param data the String object to be
	 * decoded
	 * @return String
	 */
	private String urlDecode( String data ) {
		String result ;
		result = data ;
		try {
			result = URLDecoder.decode( data , "UTF-8" ) ;
		} 
		catch( Exception e ) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		} 
		return result ;
	}
	
	/**
	 * Encodes the data onto JCE(Java Cryptography 
	 * Extension) encoded format via DES(Data 
	 * Encryption Standard).
	 * 
	 * @param data the String object to be
	 * encoded
	 * @return String
	 */
	private String jceEncode( String data ) {
		String result ;
		result = data ;
		try {
			//to be done
		} 
		catch( Exception e ) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		} 
		return result ;
	}
	
	/**
	 * Decodes the data onto JCE(Java Cryptography 
	 * Extension) decoded format via DES(Data 
	 * Encryption Standard).
	 * 
	 * @param data the String object to be
	 * decoded
	 * @return String
	 */
	private String jceDecode( String data ) {
		String result ;
		result = data ;
		try {
			//to be done
		} 
		catch( Exception e ) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		} 
		return result ;
	}
		
	/**
	 * Returns the encrypted String object.
	 * 
	 * @param s the String object to be
	 * encrypted
	 * @return String
	 */
	public String doGlobalEncrypt( String s ) {
		int i ;		
		s = this.base64Encode( s ) ;
		for( i = 0 ; i < 5 ; i++ ) {
			if( i % 2 == 0 ) {
				s = this.generateRandom( i + 1 ) + s ;
				s = this.base64Encode( s ) ;
			}
			else {
				s = s + this.generateRandom( i + 1 ) ;
				s = this.base64Encode( s ) ;
			}
		}
		s = this.urlEncode( s ) ;
		for( i = 0 ; i < 5 ; i++ ) {
			if( i % 2 == 0 ) {
				s = this.generateRandom( i + 1 ) + s ;
				s = this.base64Encode( s ) ;
			}
			else {
				s = s + this.generateRandom( i + 1 ) ;
				s = this.base64Encode( s ) ;
			}
		}						
		s = this.base64Encode( s ) ;
		return s ;
	}

	private String removeLastCharactersFromString( String data , int size ) {
		String result ;
		int i , len ;
		result = "" ;
		len = data.length() ;
		for( i = 0 ; i < len - size ; i++ ) {
			result += data.charAt( i ) ;
		}
		return result ;
	}	
	
	/**
	 * Returns the decrypted String object.
	 * 
	 * @param s the String object to be
	 * decrypted
	 * @return String
	 */
	public String doGlobalDecrypt( String s ) {
		int i ;
		s = this.base64Decode( s ) ;
		for( i = 4 ; i >= 0 ; i-- ) {
			if( i % 2 == 0 ) {
				s = this.base64Decode( s ) ;
				s = this.removeFirstCharactersFromString( s , i + 1 ) ;							
			}
			else {				
				s = this.base64Decode( s ) ;
				s = this.removeLastCharactersFromString( s , i + 1 ) ;
			}
		}
		s = this.urlDecode( s ) ;
		for( i = 4 ; i >= 0 ; i-- ) {
			if( i % 2 == 0 ) {
				s = this.base64Decode( s ) ;
				s = this.removeFirstCharactersFromString( s , i + 1 ) ;							
			}
			else {				
				s = this.base64Decode( s ) ;
				s = this.removeLastCharactersFromString( s , i + 1 ) ;
			}
		}						
		s = this.base64Decode( s ) ;
		return s ;
	}
	
	private String removeFirstCharactersFromString( String data , int size ) {
		String result ;
		int i , len ;
		result = "" ;
		len = data.length() ;
		for( i = size ; i < len ; i++ ) {
			result += data.charAt( i ) ;
		}
		return result ;
	}
	
	/**
	 * To get the exception messages.
	 * 
	 * @return String returns the error message found in the exception(if any)
	 */
	public String getErrorMessage() {
		return this.error_message ;
	}
	
	public String desKeyGenerator() {
		String result ;
		KeyGenerator key_generator ;
		SecretKey my_des_key ;
		my_des_key = null ;
		try {
			key_generator = KeyGenerator.getInstance( "DES" ) ;
			my_des_key = key_generator.generateKey() ;
		} 
		catch( Exception e ) {
			ExceptionMessageHandler.getPrintStackTrace( e ) ;
		}	    
		result = my_des_key.getEncoded().toString() ;
		return result ;		
	}
	/**
	 * Generates random String of given 
	 * length with following regular 
	 * expression: [a-zA-z0-9].
	 * 
	 * @param len the integer length of the 
	 * String object to be created
	 * @return String
	 */
	public String generateRandom( int len ) {
		String result ;
		String[] arr = new String[ 100 ] ;
		int i , cn ;
		result = "" ;
		cn = 0 ;
		for( i = 0 ; i < 10 ; i++ ) {
			arr[ cn++ ] = "" + i ;			
		}
		arr[ cn++ ] = "a" ;
		arr[ cn++ ] = "b" ;
		arr[ cn++ ] = "c" ;
		arr[ cn++ ] = "d" ;
		arr[ cn++ ] = "e" ;
		arr[ cn++ ] = "f" ;
		arr[ cn++ ] = "g" ;
		arr[ cn++ ] = "h" ;
		arr[ cn++ ] = "i" ;
		arr[ cn++ ] = "j" ;
		arr[ cn++ ] = "k" ;
		arr[ cn++ ] = "l" ;
		arr[ cn++ ] = "m" ;
		arr[ cn++ ] = "n" ;
		arr[ cn++ ] = "o" ;
		arr[ cn++ ] = "p" ;
		arr[ cn++ ] = "q" ;
		arr[ cn++ ] = "r" ;
		arr[ cn++ ] = "s" ;
		arr[ cn++ ] = "t" ;
		arr[ cn++ ] = "u" ;
		arr[ cn++ ] = "v" ;
		arr[ cn++ ] = "w" ;
		arr[ cn++ ] = "x" ;
		arr[ cn++ ] = "y" ;
		arr[ cn++ ] = "z" ;
		arr[ cn++ ] = "A" ;
		arr[ cn++ ] = "B" ;
		arr[ cn++ ] = "C" ;
		arr[ cn++ ] = "D" ;
		arr[ cn++ ] = "E" ;
		arr[ cn++ ] = "F" ;
		arr[ cn++ ] = "G" ;
		arr[ cn++ ] = "H" ;
		arr[ cn++ ] = "I" ;
		arr[ cn++ ] = "J" ;
		arr[ cn++ ] = "K" ;
		arr[ cn++ ] = "L" ;
		arr[ cn++ ] = "M" ;
		arr[ cn++ ] = "N" ;
		arr[ cn++ ] = "O" ;
		arr[ cn++ ] = "P" ;
		arr[ cn++ ] = "Q" ;
		arr[ cn++ ] = "R" ;
		arr[ cn++ ] = "S" ;
		arr[ cn++ ] = "T" ;
		arr[ cn++ ] = "U" ;
		arr[ cn++ ] = "V" ;
		arr[ cn++ ] = "W" ;
		arr[ cn++ ] = "X" ;
		arr[ cn++ ] = "Y" ;
		arr[ cn++ ] = "Z" ;
		for( i = 0 ; i < len ; i++ ) {
			Random RG = new Random() ;
			int random_int = RG.nextInt( cn ) ;
			result += arr[ random_int ] ;
		}
		return result ;
	}
	
	private static void main( String[] args ) {
		/*
		try{
			 
		    KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		    SecretKey myDesKey = keygenerator.generateKey();
		    SecretKey myDesKey2 = keygenerator.generateKey();
		    
		    Cipher desCipher;
 
		    // Create the cipher 
		    desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
 
		    // Initialize the cipher for encryption
		    desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
 
		    //sensitive information
		    byte[] text = "No body can see me".getBytes();
 
		    System.out.println("Text [Byte Format] : " + text);
		    System.out.println("Text : " + new String(text));
 
		    // Encrypt the text
		    byte[] textEncrypted = desCipher.doFinal(text);
 
		    System.out.println("Text Encryted : " + textEncrypted);
 
		    // Initialize the same cipher for decryption
		    desCipher.init(Cipher.DECRYPT_MODE, myDesKey2);
 
		    // Decrypt the text
		    byte[] textDecrypted = desCipher.doFinal(textEncrypted);
 
		    System.out.println("Text Decryted : " + new String(textDecrypted));
 
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(NoSuchPaddingException e){
			e.printStackTrace();
		}catch(InvalidKeyException e){
			e.printStackTrace();
		}catch(IllegalBlockSizeException e){
			e.printStackTrace();
		}catch(BadPaddingException e){
			e.printStackTrace();
		} 
		*/		
	}
}