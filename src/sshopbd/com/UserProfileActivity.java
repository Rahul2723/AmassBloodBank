package sshopbd.com ;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserProfileActivity extends Activity {
	private String className ;
	private TextView et1 , et2 , et3 ;
	private Button b1 ;

	protected void onCreate( Bundle savedInstanceState ) {
		this.className = "UserProfileActivity" ;
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_user_profile ) ;
		this.commonInit() ;
		this.init() ;
		this.doGetAllData() ;
	}
	
	private void init() {
		this.et1 = ( TextView ) findViewById( R.id.textView1 ) ;
		this.et2 = ( TextView ) findViewById( R.id.textView2 ) ;
		this.et3 = ( TextView ) findViewById( R.id.textView3 ) ;
		this.b1 = ( Button ) findViewById( R.id.button1 ) ;
	}
	
	private String[] parseData( String data ) {
		int i , len , cn ;
		String[] res ;
		String temp ;
		temp = new String( "" ) ;
		res = new String[ 100 ] ;
		data += "," ;
		len = data.length() ;
		temp = "" ;
		cn = 0 ;
		for( i = 0 ; i < len ; i++ ) {
			if( data.charAt( i ) == ',' ) {
				if( temp.compareTo( "" ) != 0 ) {
					res[ cn++ ] = temp ;
					temp = "" ;
				}
			}
			else {
				temp += data.charAt( i ) ;
			}
		}
		return res ;
	}
	
	private void doGetAllData() {
		EncryptionMethods emObj ;
		Bundle postData = getIntent().getExtras() ;
		String response ;
		response = new String( "" ) ;
		emObj = new EncryptionMethods() ;
		String district = postData.getString( "district" ) ;
		String bloodGroup = postData.getString( "blood_group" ) ;
		if( district.compareTo( "" ) == 0 || bloodGroup.compareTo( "" ) == 0 ) {
			AlertDialogHandler.showDialog( this , "Error!" , "Please provide district and blood group to search for users!" ) ;
			return ;
		}
		SendApiRequest sarObj = new SendApiRequest() ;
		sarObj.setActivity( this ) ;
		response = sarObj.sendRequest( this , "1=" + emObj.base64Encode( district ) + "&2=" + emObj.base64Encode( bloodGroup ) , "searchUsers.php" , "Error occurred in " + this.className + " class." , "User Profile Module!" ) ;
		if( response == null ) {
		}
		else if( response.compareTo( "" ) != 0 ) {
			String[] arr = this.parseData( response ) ;
			try {
				this.et2.setText( arr[ 0 ] ) ;
				this.et1.setText( arr[ 1 ] ) ;
				this.et3.setText( arr[ 3 ] ) ;
				this.b1.setText( "+880" + arr[ 2 ] ) ;
			}
			catch( Exception ex ) {
				AlertDialogHandler.showDialog( this , "exception data:" , ex.toString() ) ;
			}
			/*
			Intent iObj = new Intent( getBaseContext() , SearchDonorActivity.class ) ;
			startActivity( iObj ) ;
			*/
		}
		else {
			AlertDialogHandler.showDialog( this , "User Profile Module!" , "No user found!" ) ;
		}
	}
	
	private void commonInit() {
		ThreadPolicyHandler.doSetThreadPolicy() ;
		getActionBar().setTitle( ProjectConstants.getProjectName() ) ;
	}

	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.user_profile , menu ) ;
		return true ;
	}
	
	public void callNow( View v ) {
		Button bLocal = ( Button ) v ;
		Intent callIntent = new Intent( Intent.ACTION_CALL ) ;
		callIntent.setData( Uri.parse( "tel:" + bLocal.getText() ) ) ;
		startActivity( callIntent ) ;
	}

}
