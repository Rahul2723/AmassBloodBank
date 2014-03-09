package sshopbd.com ;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;

public class AlertDialogHandler {
	
	private AlertDialogHandler() {		
	}
	
	public static void showDialog( Context cObj , String titleParam , String bodyParam ) {
		AlertDialog adObj = new AlertDialog.Builder( cObj ).create() ;
		adObj.setTitle( titleParam ) ;
		adObj.setMessage( bodyParam ) ;
		adObj.setButton( "Ok" , new DialogInterface.OnClickListener() {
			public void onClick( DialogInterface dialog , int which ) {
			}
		} ) ;
		adObj.show() ;
	}
}
