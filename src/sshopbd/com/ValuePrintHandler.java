package sshopbd.com;

import android.content.Context ;

public class ValuePrintHandler {
	
	private ValuePrintHandler() {
	}
	
	public static void printArray( Context cObjParam , String[] arr ) {
		int i , len ;
		String res ;
		res = new String( "" ) ;
		len = arr.length ;
		for( i = 0 ; i < len ; i++ ) {
			if( arr[ i ] == null ) {
				break ;
			}
			res += arr[ i ] + "...." ;
		}
		AlertDialogHandler.showDialog( cObjParam , "Array Values" , res ) ;
	}
	
	public static String getPrintArray( String[] arr ) {
		int i , len ;
		String res ;
		res = new String( "" ) ;
		len = arr.length ;
		for( i = 0 ; i < len ; i++ ) {
			if( arr[ i ] == null ) {
				break ;
			}
			res += arr[ i ] + "...." ;
		}
		return res ;
	}
	
	public static void printData( Context cObjParam , String arr ) {
		AlertDialogHandler.showDialog( cObjParam , "Array Values" , arr ) ;
	}
	
	public static void printResponse( Context cObjParam , String arr ) {
		AlertDialogHandler.showDialog( cObjParam , "Response Data" , arr ) ;
	}
}
