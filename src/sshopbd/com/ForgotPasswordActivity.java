package sshopbd.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {
	private EditText et1 ;
	private String className ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_forgot_password ) ;
		this.commonInit() ;
		this.init() ;
	}
	
	private void commonInit() {
		ThreadPolicyHandler.doSetThreadPolicy() ;
		getActionBar().setTitle( ProjectConstants.getProjectName() ) ;
	}
	
	private void init() {
		this.et1 = ( EditText ) findViewById( R.id.editText1 ) ;
		this.className = "ForgotPasswordActivity" ;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.forgot_password, menu);
		return true;
	}
	
	private String parseNewPassword( String dataParam ) {
		int i , len ;
		String res ;
		res = new String( "" ) ;
		len = dataParam.length() ;
		for( i = 2 ; i < len ; i++ ) {
			res += dataParam.charAt( i ) ;
		}
		return res ;
	}
	
	public void sendEmail( View v ) {
		EncryptionMethods emObj ;
		EditText et1 ;
		String email ;
		String password , response ;
		emObj = new EncryptionMethods() ;
		et1 = ( EditText ) findViewById( R.id.editText1 ) ;
		email = et1.getText().toString() ;
		password = RandomDataHandler.getRandomString( 5 ) ;
		response = new String( "" ) ;
		SendApiRequest sarObj = new SendApiRequest() ;
		sarObj.setActivity( this ) ;
		response = sarObj.sendRequest( this , "1=" + emObj.base64Encode( email ) + "&2=" + emObj.base64Encode( password ) , "getNewPassword.php" , "Error occurred in " + this.className + " class." , "Forget Password Module!" ) ;
		if( response == null ) {
		}
		else if( response.charAt( 0 ) == 'o' && response.charAt( 1 ) == 'k' ) {
			password = this.parseNewPassword( response ) ;
			this.sendEmailThroughApi( email, "Your email address is: " + email + "\n" + "and your new password is: " + password + "\n" , "You Blood Bank Android Application Password!" ) ;
			AlertDialogHandler.showDialog( this , "Forget Password Module!" , "Sending Email!" ) ;
		}
		else {
			AlertDialogHandler.showDialog( this , "Forget Password Module!" , "No user found!" ) ;
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
			AlertDialogHandler.showDialog( this , "Forget Password Module!" , "Password reset successfully! Please check your email!" ) ;
		}
		else {
			AlertDialogHandler.showDialog( this , "Forget Password Module!" , "Problems Occured In Sending Email!" ) ;
		}
	}
	
	public void goBack( View v ) {
		Intent iObj = new Intent( getBaseContext() , MainActivity.class ) ;
		startActivity( iObj ) ;
	}

}
