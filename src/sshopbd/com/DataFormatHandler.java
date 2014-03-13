package sshopbd.com ;

public class DataFormatHandler {
	
	private DataFormatHandler() {
	}
	
	public static boolean checkEmail( String data ) {
		int i , len , j , cn , k ;
		boolean fl ;
		len = data.length() ;
		fl = false ;
		j = -1 ;
		k = -1 ;
		for( i = 0 ; i < len ; i++ ) {
			if( data.charAt( i ) == '@' ) {
				if( i != 0 ) {
					fl = true ;
					j = i ;
					break ;
				}
			}
		}
		if( fl == false ) {
			return fl ;
		}
		cn = 0 ;
		for( i = 0 ; i < len ; i++ ) {
			if( data.charAt( i ) == '.' ) {
				cn++ ;
				k = i ;
			}
		}
		if( ! ( data.charAt( j ) == '@' && j - 1 >= 0 && data.charAt( j - 1 ) != '.' ) ) {
			return false ;
		}
		if( ! ( data.charAt( j ) == '@' && j + 1 < len && data.charAt( j + 1 ) != '.' ) ) {
			return false ;
		}
		if( data.charAt( len - 1 ) == '.' ) {
			return false ;
		}
		if( ( data.charAt( j ) == '@' && ( j + 1 >= len || j - 1 < 0 ) ) ) {
			return false ;
		}
		if( cn == 0 ) {
			return false ;
		}
		return true ;
	}
	
	public static boolean checkPhoneNumber( String numberParam ) {
		int i , len ;
		len = numberParam.length() ;
		for( i = 0 ; i < len ; i++ ) {
			if( numberParam.charAt( i ) >= '0' && numberParam.charAt( i ) <= '9' ) {
			}
			else {
				return false ;
			}
		}
		if( len >= 7 && len <= 11 ) {
			return true ;
		}
		return false ;
	}
	
}
