package sshopbd.com;

public class StackTraceHandler {
	
	private StackTraceHandler() {
		
	}
	
	public static String doPrintStackTrace( StackTraceElement[] arr ) {
		String res ;
		res = new String( "" ) ;
		int i , sz ;
		sz = arr.length ;
		for( i = 0 ; i < sz ; i++ ) {
			res += arr[ i ].toString() + "....." ;
		}
		return res ;
	}
}
