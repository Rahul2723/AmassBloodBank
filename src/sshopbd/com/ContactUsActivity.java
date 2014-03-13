package sshopbd.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUsActivity extends Activity {
	private EditText et1 , et2 , et3 , et4 ;
	private String className ;
	
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_contact_us ) ;
		this.commonInit() ;
		this.init() ;
	}
	
	private void commonInit() {
		ThreadPolicyHandler.doSetThreadPolicy() ;
		getActionBar().setTitle( ProjectConstants.getProjectName() ) ;
	}
	
	private void init() {
		this.et1 = ( EditText ) findViewById( R.id.editText1 ) ;
		this.et2 = ( EditText ) findViewById( R.id.editText2 ) ;
		this.et3 = ( EditText ) findViewById( R.id.editText3 ) ;
		this.et4 = ( EditText ) findViewById( R.id.editText4 ) ;
		this.className = "ContactUsActivity" ;
	}
	
	public void buttonClicked( View v ) {
		String name , email , phone , message ;
		name = this.et1.getText().toString() ;
		email = this.et2.getText().toString() ;
		phone = this.et3.getText().toString() ;
		message = this.et4.getText().toString() ;
		if( name.compareTo( "" ) == 0 || email.compareTo( "" ) == 0 || phone.compareTo( "" ) == 0 || message.compareTo( "" ) == 0 ) {
			AlertDialogHandler.showDialog( this , "Error!" , "Please provide all the data!" ) ;
			return ;
		}
		if( DataFormatHandler.checkEmail( email ) == false ) {
			AlertDialogHandler.showDialog( this , "Error!" , "Invalid Email!" ) ;
			return ;
		}
		if( DataFormatHandler.checkPhoneNumber( phone ) == false ) {
			AlertDialogHandler.showDialog( this , "Error!" , "Invalid Phone Number!" ) ;
			return ;
		}
		try { 
			this.sendEmailThroughApi( "drupodibd@gmail.com" , "Email Address: " + email + "\n" + "From: " + name + "\n" + "Phone Number: " + phone + "\n" + "Message: " + message + "\n" , "Contact Us Message From User!" ) ;
		}
		catch( Exception ex ) {
			AlertDialogHandler.showDialog( this , "Error!" , ex.toString() ) ;
		}
	}
	
	private void sendEmailThroughApi( String toParam , String messageParam , String subjectParam ) {
		String response ;
		EncryptionMethods emObj ;
		emObj = new EncryptionMethods() ;
		response = new String( "" ) ;
		SendApiRequest sarObj = new SendApiRequest() ;
		sarObj.setActivity( this ) ;
		response = sarObj.sendRequest( this , "1=" + emObj.base64Encode( toParam ) + "&2=" + emObj.base64Encode( messageParam ) + "&3=" + emObj.base64Encode( subjectParam ) , "sendEmail.php" , "Error occurred in " + this.className + " class." , "Forget Password Module!" ) ;
		if( response == null ) {
		}
		else if( response.charAt( 0 ) == 'o' && response.charAt( 1 ) == 'k' ) {
			AlertDialogHandler.showDialog( this , "Contact Us Module!" , "Emailed successfully sent to the admin!" ) ;
		}
		else {
			AlertDialogHandler.showDialog( this , "Contact Us Module!" , "Problems Occured In Sending Email!" ) ;
		}
	}
	

	public boolean onCreateOptionsMenu( Menu menu ) {
		//getMenuInflater().inflate( R.menu.contact_us , menu ) ;
		return true ;
	}
	
	public void goBack( View v ) {
		Intent iObj = new Intent( getBaseContext() , MainActivity.class ) ;
		startActivity( iObj ) ;
	}

}

