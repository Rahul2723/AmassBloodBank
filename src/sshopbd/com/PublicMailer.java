package sshopbd.com ;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;


/**
 * Sends mail to designated email address
 * with the message. Supports only 1 email 
 * address at moment.
 * 
 * Sample Usage:
 * PublicMailer PM = new PublicMailer() ; 
 * PM.sendEmail( "test@gmail.com" , "Hi there!" , "This is test message!" ) ;
 * 
 * Description:
 * File Name: PublicMailer.java
 * Package Name: com.peincognito.peinframework.controller.mail
 * Project Name: PEIN Framework
 * Time: 12:47:34 PM
 * Date: Sep 21, 2013
 * Year: 2013
 * 
 * @author S. M. Ijaz-ul-Amin Chowdhury
 */
public class PublicMailer {
	private String error_msg ;
	
	/**
	 * Default constructor. 
	 * 
	 * @return void
	 */
	public PublicMailer() {
		this.error_msg = "" ;
	}
		
	/**
	 * Sends the email to the designated email 
	 * address.
	 * 
	 * @return boolean returns whether the email send 
	 * was successful in boolean
	 */
	public boolean sendEmail( String to , String subject , String text ) {
		boolean fl ;
		fl = true ;
		final String username = "drupodibd@gmail.com" ;//test@gmail.com
		final String password = "drupodi536." ;//mypassword
		final String from = "drupodibd@gmail.com" ;//test@gmail.com
		if( to.compareTo( "" ) == 0 ) {
			to = from ;
		}
		final String company_name = "Amass Bangladesh" ;
 		Properties props = new Properties() ;
		props.put( "mail.smtp.auth" , "true" ) ;
		props.put( "mail.smtp.starttls.enable" , "true" ) ;
		props.put( "mail.smtp.host" , "smtp.gmail.com" ) ;
		props.put( "mail.smtp.port" , "587" ) ;		
		Session session = Session.getInstance( props ,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication( username , password ) ;
				}
			}
		) ;
	 	try {
			Message message = new MimeMessage( session ) ;
			message.setFrom( new InternetAddress( from.toString() ) ) ;
			message.setContent( "" , "text/html" ) ;
			message.setSubject( subject.toString() ) ;
			message.setText( text.toString() ) ;
			message.setHeader( "Content-Type" , "text/html; charset=UTF-8" ) ;
			message.setRecipients( Message.RecipientType.TO , InternetAddress.parse( to.toString() ) ) ;
			Address address = null ;
			try {
				address = new InternetAddress( from , company_name ) ;
			}
			catch( UnsupportedEncodingException e ) {
				this.error_msg += e.toString() ;
				return false ;
			}
			message.setSentDate( new Date() ) ;
            message.setFrom( address ) ;
            message.saveChanges() ;
			Transport.send( message ) ;
		}
	 	catch( MessagingException e ) {
	 		this.error_msg += "Error in sending email : " + e.toString() ;
	 		fl = false ;
		}
	 	return fl ;
	}
	
	/*
	protected void sendEmailAndroid( String to , String subject , String body ) {
		String[] recipients ;
		String username , password , from ;
		from = "drupodibd@gmail.com" ;
		password = "drupodi536." ;
		recipients = new String[ 10 ] ;
		recipients[ 0 ] = to ;
		if( to.compareTo( "" ) == 0 ) {
			to = from ;
		}
		Intent email = new Intent( Intent.ACTION_SEND , Uri.parse( "mailto:" ) ) ;
		email.setType( "message/rfc822" ) ;
		email.putExtra( Intent.EXTRA_EMAIL , recipients ) ;
		email.putExtra( Intent.EXTRA_SUBJECT , subject.toString() ) ;
		email.putExtra( Intent.EXTRA_TEXT , body.toString() ) ;
		try {
			startActivity( Intent.createChooser( email , "Choose an email client from..." ) ) ; 
		} 
		catch( android.content.ActivityNotFoundException ex ) {
			Toast.makeText( MainActivity.this , "No email client installed." ,
			Toast.LENGTH_LONG).show() ;
		}
	}
	*/

	/**
	 * Returns the error message found in the 
	 * exception.
	 * 
	 * @return String
	 */
	public String getErrorMessage() {
		return this.error_msg ;
	}
}