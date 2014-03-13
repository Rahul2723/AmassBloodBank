package sshopbd.com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SearchDonorActivity extends Activity {
	private Spinner sp1 ;
	private Spinner sp2 ;
	private Button bt1 ;
	
	private void commonInit() {
		ThreadPolicyHandler.doSetThreadPolicy() ;
		getActionBar().setTitle( ProjectConstants.getProjectName() ) ;
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
	
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState ) ;
		setContentView( R.layout.activity_search_donor ) ;
		this.commonInit() ;
		
		this.bt1 = ( Button ) findViewById( R.id.button3 ) ;
		this.sp1 = ( Spinner ) findViewById( R.id.spinner1 ) ;
		this.sp2 = ( Spinner ) findViewById( R.id.spinner2 ) ;
	
		DropdownDataHandler dddhObj = new DropdownDataHandler() ;
		dddhObj.setRefernece( this ) ;
		dddhObj.setSpinnerDistrictData( this.sp1 ) ;
		
		dddhObj = new DropdownDataHandler() ;
		dddhObj.setRefernece( this ) ;
		dddhObj.setSpinnerBloodGroupData( this.sp2 ) ;
		
	}

	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.search_donor, menu ) ;
		return true ;
	}
	
	public void doSearchDonor( View v ) {
		try {
		Intent iObj = new Intent( getBaseContext() , UserProfileActivity.class ) ;
		iObj.putExtra( "district", this.sp1.getSelectedItem().toString() ) ;
		iObj.putExtra( "blood_group", this.sp2.getSelectedItem().toString() ) ;
		startActivity( iObj ) ;
		}
		catch( Exception ex ) {
			AlertDialogHandler.showDialog( this , "Error!", ex.toString() ) ;
		}
	}

}
