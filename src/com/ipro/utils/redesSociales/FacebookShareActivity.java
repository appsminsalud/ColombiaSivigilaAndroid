package com.ipro.utils.redesSociales;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.PackageManager.NameNotFoundException;


public class FacebookShareActivity extends FragmentActivity {
	
	/***********************************
	 * 
	 * 
	 * ADVERTENCIA
	 * 
	 * 
	 * ESTE METODO NECESITA TENER CONFIGURADA UN HASH PARA LA APLICACION EN FACEBOOK ASI QUE
	 * SI EL HASH NO SE ENCUENTRA NO SERVIRA!.
	 * 
	 * 
	 */
	
	private FacebookShareFragment mainFragment;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        if (savedInstanceState == null) {
        	// Add the fragment on initial activity setup
        	mainFragment = new FacebookShareFragment();
        	
        	//permite pasar los extras del activity padre
        	mainFragment.setArguments(getIntent().getExtras());
        	
            getSupportFragmentManager()
            .beginTransaction()
            .add(android.R.id.content, mainFragment)
            .commit();
        } else {
        	// Or set the fragment from restored state info
        	mainFragment = (FacebookShareFragment) getSupportFragmentManager()
        	.findFragmentById(android.R.id.content);
        }
        
        /*******************************************************
         * conseguir el hash key del app para facebook
         * *****************************************************
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.facebook.samples.publishfeedhowto", 
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
        } catch (NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
        ************************************************************/
    }
    
    
	public static boolean isInternetAvailable(Context context) {
	    boolean haveConnectedWifi = false;
	    boolean haveConnectedMobile = false;
	    boolean connectionavailable = false;
	    ConnectivityManager cm = (ConnectivityManager) context
	            .getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    NetworkInfo informationabtnet = cm.getActiveNetworkInfo();
	    for (NetworkInfo ni : netInfo) {
	        try {
	            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	                if (ni.isConnected()) haveConnectedWifi = true; //Log.e("Type-Conection",ni.getTypeName());
	            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	                if (ni.isConnected()) haveConnectedMobile = true;//Log.e("Type-Conection",ni.getTypeName());
	            if (informationabtnet.isAvailable()
	                && informationabtnet.isConnected())
	                connectionavailable = true;//Log.e("Internet","Esta Conectado a Internet");
	            Log.i("ConnectionAvailable", "" + connectionavailable);
	        } catch (Exception ex) {
//	           Log.e("Catch Internet","Inside utils catch clause , exception is" + ex.toString());
	            ex.printStackTrace();
	        }
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
}