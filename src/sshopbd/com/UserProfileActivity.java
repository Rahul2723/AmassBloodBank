package sshopbd.com ;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class UserProfileActivity extends Activity {
	private String className ;
	private TextView et1 , et2 , et3 ;
	private Button b1 ;
	private int dataSize , newButtonId , newTextViewId ;

	protected void onCreate( Bundle savedInstanceState ) {
		this.className = "UserProfileActivity" ;
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_user_profile ) ;
		this.commonInit() ;
		this.init() ;
		this.doGetAllData() ;
	}
	
	public boolean onOptionsItemSelected( MenuItem item ) {
	    switch( item.getItemId() ) {
	    case R.id.action_settings :
	        this.logout() ;
	        return true ;
	    default :
	        return super.onOptionsItemSelected( item ) ;
	    }
	}
	
	public void logout() {
		this.finish() ;
	}
	
	private void init() {
		this.et1 = ( TextView ) findViewById( R.id.textView1 ) ;
		this.et2 = ( TextView ) findViewById( R.id.textView2 ) ;
		this.et3 = ( TextView ) findViewById( R.id.textView3 ) ;
		this.b1 = ( Button ) findViewById( R.id.button3 ) ;
		this.dataSize = 0 ;
		this.newButtonId = 2 ;
		this.newTextViewId = 4 ;
	}
	
	private String[][] parseData( String dataParam ) {
		int i , len , cn1 , cn2 ;
		String[][] res ;
		String temp ;
		temp = new String( "" ) ;
		res = new String[ ProjectConstants.getArraySize() ][ ProjectConstants.getArraySize() ] ;
		dataParam += ",:" ;
		len = dataParam.length() ;
		temp = "" ;
		cn1 = 0 ;
		cn2 = 0 ;
		for( i = 0 ; i < len ; i++ ) {
			if( cn2 == 0 && dataParam.charAt( i ) == ':' ) {
			}
			else if( dataParam.charAt( i ) == ',' && i + 1 < len && dataParam.charAt( i + 1 ) == ':' ) {
				if( temp.compareTo( "" ) != 0 ) {
					res[ cn1 ][ cn2 ] = temp ;
					cn2++ ;
					temp = "" ;
					cn1++ ;
					cn2 = 0 ;
				}
			}
			else if( dataParam.charAt( i ) == ',' ) {
				if( temp.compareTo( "" ) != 0 ) {
					res[ cn1 ][ cn2 ] = temp ;
					cn2++ ;
					temp = "" ;
				}
			}
			else {
				temp += dataParam.charAt( i ) ;
			}
		}
		this.dataSize = cn1 ;
		return res ;
	}
	
	private void doGetAllData() {
		int i , j , cn1 , cn2 ;
		boolean fl ;
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
			String[][] arr = this.parseData( response ) ;
			cn1 = this.dataSize ;
			for( i = 0 ; i < cn1 ; i++ ) {
				if( arr[ i ] == null ) {
					break ;
				}
				fl = false ;
				cn2 = arr[ i ].length ;
				for( j = 0 ; j < 4 ; j++ ) {
					if( arr[ i ][ j ] == null ) {
						fl = true ;
						break ;
					}
				}
				if( fl == true ) {
					break ;
				}
				this.setNewLinearLayout( arr[ i ][ 0 ] , arr[ 0 ][ 1 ] , arr[ 0 ][ 3 ] , arr[ 0 ][ 2 ] ) ;
			}
		}
		else {
			AlertDialogHandler.showDialog( this , "User Profile Module!" , "No user found!" ) ;
			try {
				Thread.sleep( 2000 ) ;
			}
			catch( InterruptedException e ) {
			}
			Intent iObj = new Intent( getBaseContext() , SearchDonorActivity.class ) ;
			startActivity( iObj ) ;
		}
	}
	
	private void setNewLinearLayout( String nameParam , String locationParam , String blodGroupParam , String phoneNumberParam ) {
		LayoutParams lpObj ;
		Drawable daObj ;
		LinearLayout mainLinearLayoutObj , newRowLinearLayoutObj , anotherColumnLinearLayoutObj , newColumnLinearLayoutObj ;
		TextView tvObj ;

		newRowLinearLayoutObj = new LinearLayout( this ) ;
		daObj = getResources().getDrawable( R.drawable.button_gradient ) ;
		newRowLinearLayoutObj.setBackgroundDrawable( daObj ) ;
	    lpObj = new LayoutParams( LayoutParams.MATCH_PARENT , LayoutParams.WRAP_CONTENT ) ;
	    newRowLinearLayoutObj.setLayoutParams( lpObj ) ;
	    
	    newColumnLinearLayoutObj = new LinearLayout( this ) ;
	    newColumnLinearLayoutObj.setOrientation( LinearLayout.VERTICAL ) ;	    
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT ) ;
	    lpObj.setMargins( 10 , 5 , 0 , 5 ) ;
	    lpObj.weight = 1.5f ;
	    newColumnLinearLayoutObj.setLayoutParams( lpObj ) ;
	    
	    //first textview
	    tvObj = new TextView( this ) ;
	    tvObj.setText( nameParam ) ;
	    tvObj.setTextAppearance( this , android.R.style.TextAppearance_Medium ) ;
	    tvObj.setId( this.newTextViewId++ ) ;
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT ) ;
	    lpObj.weight = 1.5f ;
	    tvObj.setLayoutParams( lpObj ) ;
	    
	    newColumnLinearLayoutObj.addView( tvObj ) ;
	    
	    //second textview
	    tvObj = new TextView( this ) ;
	    tvObj.setText( locationParam ) ;
	    tvObj.setId( this.newTextViewId++ ) ;
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT ) ;
	    lpObj.weight = 1.5f ;
	    tvObj.setLayoutParams( lpObj ) ;
	    newColumnLinearLayoutObj.addView( tvObj ) ;
	    
	    //third textview
	    tvObj = new TextView( this ) ;
	    tvObj.setText( blodGroupParam ) ;
	    tvObj.setId( this.newTextViewId++ ) ;
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT ) ;
	    lpObj.weight = 1.5f ;
	    tvObj.setLayoutParams( lpObj ) ;
	    newColumnLinearLayoutObj.addView( tvObj ) ;
	    
	    newRowLinearLayoutObj.addView( newColumnLinearLayoutObj ) ;
	    
	    anotherColumnLinearLayoutObj = new LinearLayout( this ) ;
	    anotherColumnLinearLayoutObj.setOrientation( LinearLayout.VERTICAL ) ;	    
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.MATCH_PARENT ) ;
	    anotherColumnLinearLayoutObj.setLayoutParams( lpObj ) ;
	    
	    Button butObj = new Button( this ) ;
	    butObj.setText( phoneNumberParam ) ;
	    butObj.setId( this.newButtonId++ ) ;
	    lpObj = new LayoutParams( LayoutParams.WRAP_CONTENT , LayoutParams.WRAP_CONTENT ) ;
	    lpObj.weight = 1.5f ;
	    lpObj.topMargin = 10 ;
	    lpObj.bottomMargin = 10 ;
	    butObj.setLayoutParams( lpObj ) ;
	    daObj = getResources().getDrawable( R.drawable.button_gradient ) ;
	    butObj.setBackgroundDrawable( daObj ) ;
	    butObj.setOnClickListener( this.handleOnClick( butObj ) ) ;
	    
	    anotherColumnLinearLayoutObj.addView( butObj ) ;
	    
	    newRowLinearLayoutObj.addView( anotherColumnLinearLayoutObj ) ;
	    
	    mainLinearLayoutObj = ( LinearLayout ) findViewById( R.id.mainLayout ) ;
	    mainLinearLayoutObj.addView( newRowLinearLayoutObj ) ;
	}
	
	View.OnClickListener handleOnClick( final Button button ) {
	    return new View.OnClickListener() {
	        public void onClick( View v ) {
	        	Button bLocal = ( Button ) v ;
	    		Intent callIntent = new Intent( Intent.ACTION_CALL ) ;
	    		callIntent.setData( Uri.parse( "tel:" + bLocal.getText() ) ) ;
	    		startActivity( callIntent ) ;    
	        }
	    } ;
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
