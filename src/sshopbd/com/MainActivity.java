package sshopbd.com ;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	private Button btnStartAnotherActivity ;
	private EditText et1 , et2 ;
	
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_main ) ;
		this.commonInit() ;
	}
	
	private void commonInit() {
		ThreadPolicyHandler.doSetThreadPolicy() ;
		getActionBar().setTitle( ProjectConstants.getProjectName() ) ;
	}

	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.main , menu ) ;
		return true;
	}
	
	public void doRegister( View v ) {
		Intent i = new Intent( getBaseContext() , UserRegistrationActivity.class ) ;
		startActivity( i ) ;
	}
	
	public void doCheckLogin( View v ) {
		this.et1 = ( EditText ) findViewById( R.id.editText1 ) ;
		this.et2 = ( EditText ) findViewById( R.id.editText2 ) ;
		String response ;
		response = new String( "" ) ;
		String email = this.et1.getText().toString() ;
		String password = this.et2.getText().toString() ;
		if( email.compareTo( "" ) == 0 || password.compareTo( "" ) == 0 ) {
			AlertDialogHandler.showDialog( this , "Error!" , "Please provide email and password to login!" ) ;
			return ;
		}
		SendApiRequest sarObj = new SendApiRequest() ;
		sarObj.setActivity( this ) ;
		response = sarObj.sendRequest( this , "1=" + email + "&2=" + password , "checkLogin.php" , "Error occurred in MainActivity class." , "Login Module!" ) ;
		if( response == null ) {
		}
		else if( response.charAt( 0 ) == 'o' && response.charAt( 1 ) == 'k' ) {
			Intent iObj = new Intent( getBaseContext() , SearchDonorActivity.class ) ;
			startActivity( iObj ) ;
		}
		else {
			AlertDialogHandler.showDialog( this , "Login Module!" , "Wrong email and password combination!" ) ;
		}
	}
}

