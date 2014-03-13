package sshopbd.com ;

import android.content.Context;
import android.content.Intent;
 

public final class SendApiRequest {
    private MainActivity maObj ;
    private SearchDonorActivity sdaObj ;
    private UserRegistrationActivity uraObj ;
    private UserProfileActivity upaObj ;
    private ForgotPasswordActivity fpObj ;
    private ContactUsActivity cuaObj ;
    private String exceptionMsg ;
    private HttpsClient HS ;
    private boolean prerequisiteFulfilled ;
    
    public SendApiRequest() {
    	this.maObj = null ;
    	this.exceptionMsg = "" ;
    	this.prerequisiteFulfilled = false ;
    }
    
    public String getExceptionMessage() {
    	return this.exceptionMsg ;
    }
    
	public void setActivity( MainActivity maObjParam ) {
		this.maObj = maObjParam ;
		this.HS = new HttpsClient( this.maObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
	public void setActivity( SearchDonorActivity sdaObjParam ) {
		this.sdaObj = sdaObjParam ;
		this.HS = new HttpsClient( this.sdaObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
	public void setActivity( UserRegistrationActivity uraObjParam ) {
		this.uraObj = uraObjParam ;
		this.HS = new HttpsClient( this.uraObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
	public void setActivity( UserProfileActivity upaObjParam ) {
		this.upaObj = upaObjParam ;
		this.HS = new HttpsClient( this.upaObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
	public void setActivity( ForgotPasswordActivity maObjParam ) {
		this.fpObj = maObjParam ;
		this.HS = new HttpsClient( this.fpObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
	public void setActivity( ContactUsActivity cuaObjObjParam ) {
		this.cuaObj = cuaObjObjParam ;
		this.HS = new HttpsClient( this.cuaObj ) ;
		this.prerequisiteFulfilled = true ;
	}
	
    private String doRequest( String urlParametersParam , String fileNameParam ) {
    	if( this.prerequisiteFulfilled == false ) {
    		throw new RuntimeException( "Main Activity Object Not Initialized!" ) ;
    	}
        String targetURL = new String( "" ) ;
        String pathPrefix = new String( "" ) ;
        String response = new String( "" ) ;
        pathPrefix = "http://www.amassbd.com/blood_bank_api/controller/" ;
        try {
            targetURL = new String( pathPrefix + fileNameParam ) ;
            response = HS.excutePost( targetURL, urlParametersParam ) ;
            if( response == null ) {
            	this.exceptionMsg = HS.getExceptionMessage() ;
            }
            return response ;
        }
        catch( Exception ex ) {
        	this.exceptionMsg = "Exception occurred in SendApiRequest class." ;
        }
        return null ;
	}
    
    public String sendRequest( Context cObjParam , String urlParametersParam , String fileNameParam , String mainExceptionMessage , String moduleNameParam ) {
    	String res ;
    	Intent iObj ;
    	res = null ;
    	try {
			String response = new String( "" ) ;
			String exceptionMsg = new String( "" ) ;
			response = this.doRequest( urlParametersParam , fileNameParam ) ;
			if( response == null ) {
				exceptionMsg = this.getExceptionMessage() ;
				AlertDialogHandler.showDialog( cObjParam , "Error!" , exceptionMsg ) ;
			}
			else {
				res = response ;
			}
		}
		catch( Exception ex ) {
			AlertDialogHandler.showDialog( cObjParam , moduleNameParam + " Error!" , mainExceptionMessage ) ;
		}
    	return res ;
    }
}
