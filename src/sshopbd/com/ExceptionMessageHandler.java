package sshopbd.com;

import java.io.ByteArrayOutputStream ;
import java.io.PrintStream ;

/**
 * Handles the exception e.printStackTrace()
 * method data. Gets it and prints it 
 * onto a String object.
 * 
 * Sample Usage:
 * 
 * 
 * Description:
 * File Name: ExceptionMessageHandler.java
 * Package Name: com.peincognito.peinframework.controller.exception
 * Project Name: PEIN Framework
 * Time: 2:15:49 AM
 * Date: Sep 28, 2013
 * Year: 2013
 * 
 * @author S. M. Ijaz-ul-Amin Chowdhury
 */
public final class ExceptionMessageHandler {

	/**
	 * Default constructor
	 * 
	 */
	private ExceptionMessageHandler() {		
	}
	
	public static String getPrintStackTrace( Exception e ) {
		String result ;
		result = "" ;
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream() ;
		PrintStream PS = new PrintStream( BAOS ) ;
		e.printStackTrace( PS ) ;		
		result = BAOS.toString() ;
		return result ;
	}
}