package sshopbd.com;

public class RandomDataHandler {
	
	private RandomDataHandler() {
	}
	
	public static String getRandomString( int len ) {
		int a , i , cn ;
		char[] arr ;
		String res ;
		res = new String( "" ) ;
		arr = new char[ 100 ] ;
		cn = 0 ;
		arr[ cn++ ] = 'a' ;
		arr[ cn++ ] = 'b' ;
		arr[ cn++ ] = 'c' ;
		arr[ cn++ ] = 'd' ;
		arr[ cn++ ] = 'f' ;
		arr[ cn++ ] = 'g' ;
		arr[ cn++ ] = 'h' ;
		arr[ cn++ ] = 'i' ;
		arr[ cn++ ] = 'j' ;
		arr[ cn++ ] = 'k' ;
		arr[ cn++ ] = 'l' ;
		arr[ cn++ ] = 'm' ;
		arr[ cn++ ] = 'n' ;
		arr[ cn++ ] = 'o' ;
		arr[ cn++ ] = 'p' ;
		arr[ cn++ ] = 'q' ;
		arr[ cn++ ] = 'r' ;
		arr[ cn++ ] = 's' ;
		arr[ cn++ ] = 't' ;
		arr[ cn++ ] = 'u' ;
		arr[ cn++ ] = 'v' ;
		arr[ cn++ ] = 'w' ;
		arr[ cn++ ] = 'x' ;
		arr[ cn++ ] = 'y' ;
		arr[ cn++ ] = 'z' ;
		arr[ cn++ ] = '0' ;
		arr[ cn++ ] = '1' ;
		arr[ cn++ ] = '2' ;
		arr[ cn++ ] = '3' ;
		arr[ cn++ ] = '4' ;
		arr[ cn++ ] = '5' ;
		arr[ cn++ ] = '6' ;
		arr[ cn++ ] = '7' ;
		arr[ cn++ ] = '8' ;
		arr[ cn++ ] = 'G' ;
		arr[ cn++ ] = 'L' ;
		for( i = 0 ; i < len ; i++ ) {
			a = ( int ) Math.floor( Math.random() * 10000 ) ;
			a = a % cn ;
			res += arr[ a ] ; 
		}
		return res ;
	}
}
