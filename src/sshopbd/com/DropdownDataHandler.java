package sshopbd.com ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DropdownDataHandler {
	private MainActivity maObj ;
	private SearchDonorActivity sdaObj ;
	private UserRegistrationActivity uraObj ;
	private Context cObj ;
	private String className ;
	
	public DropdownDataHandler() {
		this.className = "DropdownDataHandler" ;
		this.resetAll() ;
	}
	
	public void setRefernece( MainActivity maObjParam ) {
		this.maObj = maObjParam ;
		this.cObj = this.maObj.getBaseContext() ;
	}
	
	public void setRefernece( SearchDonorActivity sdaObjParam ) {
		this.sdaObj = sdaObjParam ;
		this.cObj = this.sdaObj.getBaseContext() ;
	}
	
	public void setRefernece( UserRegistrationActivity uraObjParam ) {
		this.uraObj = uraObjParam ;
		this.cObj = this.uraObj.getBaseContext() ;
	}
	
	private void resetAll() {
		this.maObj = null ;
		this.sdaObj = null ;
		this.uraObj = null ;
	}
	
	private void setSarObjectActivity( SendApiRequest sarObjParam ) {
		if( this.maObj != null ) {
			sarObjParam.setActivity( this.maObj ) ;
		}
		else if( this.sdaObj != null ) {
			sarObjParam.setActivity( this.sdaObj ) ; 
		}
		else if( this.uraObj != null ) {
			sarObjParam.setActivity( this.uraObj ) ; 
		}
	}
	
	public void showDialog( String titleParam , String bodyParam ) {
		/*
		if( this.maObj != null ) {
			AlertDialogHandler.showDialog( this.maObj , titleParam , bodyParam ) ;
		}
		else if( this.sdaObj != null ) {
			AlertDialogHandler.showDialog( this.sdaObj , titleParam , bodyParam ) ;
		}
		else if( this.uraObj != null ) {
			AlertDialogHandler.showDialog( this.uraObj , titleParam , bodyParam ) ;
		}
		*/
		AlertDialogHandler.showDialog( this.cObj , titleParam , bodyParam ) ;
	}
	
	public void showToast( String msgParam ) {
		Toast.makeText( this.cObj , msgParam , Toast.LENGTH_LONG ).show() ;
	}
	
	private ArrayAdapter setArrayAdapter( List adj ) {
		ArrayAdapter aaObj ;
		aaObj = null ;
		if( this.maObj != null ) {
			aaObj = new ArrayAdapter( this.maObj , android.R.layout.simple_spinner_item , adj ) ;
		}
		else if( this.sdaObj != null ) {
			aaObj = new ArrayAdapter( this.sdaObj , android.R.layout.simple_spinner_item , adj ) ;
		}
		else if( this.uraObj != null ) {
			aaObj = new ArrayAdapter( this.uraObj , android.R.layout.simple_spinner_item , adj ) ;
		}
		return aaObj ;
	}
	
	private String[] getDataAsArray( String dataParam , boolean fl ) {
		int i , len , cn ;
		String[] res ;
		String temp ;
		res = new String[ 100 ] ;
		len = dataParam.length() ;
		temp = new String( "" ) ;
		cn = 0 ;
		for( i = 0 ; i < len ; i++ ) {
			if( dataParam.charAt( i ) == ',' ) {
				if( temp.compareTo( "" ) != 0 ) {
					res[ cn++ ] = temp ;
				}
				temp = "" ;
			}
			else {
				temp += dataParam.charAt( i ) ;
			}
		}
		if( fl == true ) {
			Arrays.sort( res , 0 , cn ) ;
		}
		return res ;
	}
	
	private String[] getAllDistrictData() {
		String response ;
		String[] res ;
		SendApiRequest sarObj ;
		sarObj = new SendApiRequest() ;
		this.setSarObjectActivity( sarObj ) ;
		response = sarObj.sendRequest( this.cObj , "" , "districtDataHandler.php" , "Error occurred in " + this.className + " class." , "District List Module!" ) ;
		res = this.getDataAsArray( response , true ) ;
		return res ;
	}
	
	private void getObject( Object oParam ) {
		oParam = null ;
	}
	
	private String[] getAllBloodGroup() {
		String response ;
		String[] res ;
		SendApiRequest sarObj ;
		sarObj = new SendApiRequest() ;
		this.setSarObjectActivity( sarObj ) ;
		response = sarObj.sendRequest( this.cObj , "" , "bloodGroupDataHandler.php" , "Error occurred in " + this.className + " class." , "Blood Group Module!" ) ;
		res = this.getDataAsArray( response , true ) ;
		return res ;
	}
	
	public void setSpinnerDistrictData( Spinner sObjParam ) {
		List adj = new ArrayList() ;
		String[] arr = this.getAllDistrictData() ;
		int i , sz ;
		sz = arr.length ;
		for( i = 0 ; i < sz ; i++ ) {
			if( arr[ i ] != null ) {
				adj.add( arr[ i ] ) ;
			}
		}
		sObjParam.setAdapter( this.setArrayAdapter( adj ) ) ;
		this.resetAll() ;
	}
	
	public void setSpinnerBloodGroupData( Spinner sObjParam ) {
		List adj = new ArrayList() ;
		String[] arr = this.getAllBloodGroup() ;
		int i , sz ;
		sz = arr.length ;
		for( i = 0 ; i < sz ; i++ ) {
			if( arr[ i ] != null ) {
				adj.add( arr[ i ] ) ;
			}
		}
		sObjParam.setAdapter( this.setArrayAdapter( adj ) ) ;
		this.resetAll() ;
	}
}
