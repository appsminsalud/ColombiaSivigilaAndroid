package com.ipro.utils.redesSociales;

import java.util.List;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;



public class MenuCompartir 
{

	
	private static MensajeRedesSociales mensaje;
	
	public static void mostrarMenuAndCompartir(final Activity caller, MensajeRedesSociales msj)
	{
		
	 String[] opciones = {"Facebook","Twitter","E-Mail","Cancelar"};
     mensaje = msj;
	 new AlertDialog.Builder(caller)
        .setTitle("Compartir")
        .setItems(opciones, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	
            	switch (which)
            	{
	            	case 0:{
	            		compartirFacebook(caller);
	            		break;
	            	}
	            	case 1:{
	            		compartirTwitter(caller);
	            		break;
	            	}
	            	case 2:{
	            		compartirEmail(caller);
	            		break;
	            	}
            	
            	}
            	
	       }
        }).show();
	}
	
	
	private static void compartirFacebook(Activity caller)
	{

		   Intent intent = new Intent(caller, FacebookShareActivity.class);
		   ParcelMensajeRedesSociales msj = new ParcelMensajeRedesSociales(mensaje);
		   intent.putExtra("mensaje", msj);
		   caller.startActivity(intent);

     
	}
	
	private static void compartirTwitter(final Activity caller)
	{
		Intent shareIntent = findTwitterClient(caller); 
		if (shareIntent != null)
		{
			shareIntent.putExtra(Intent.EXTRA_TEXT, "Evento Sivigila "+ mensaje.getUrl() + " Colombia Sivigila");
	        caller.startActivity(Intent.createChooser(shareIntent, "Compartir con Twitter"));
		}
		else
		{
             new AlertDialog.Builder(caller)
            .setTitle("No hay Twitter Instalado")
            .setMessage("Se necesita instalar Twitter para enviar esta información via tweet, deseas instalarlo?")
            .setPositiveButton("Instalar Twitter", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                	Intent intent = new Intent(Intent.ACTION_VIEW);
                	intent.setData(Uri.parse("market://details?id=com.twitter.android"));
                	caller.startActivity(intent);
                }
            })
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            })
            .show();
		}
        
	
	}
	
	private static void compartirEmail(Activity caller)
	{
		
		Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",
				"", null));
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mensaje.getTitulo());
		emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje.getMensaje());
		caller.startActivity(Intent.createChooser(emailIntent, "Compartir via Email"));
	}
	
	public static Intent findTwitterClient(Activity caller) 
	{
	    final String[] twitterApps = {
	            // package // name - nb installs (thousands)
	            "com.twitter.android", // official - 10 000
	            "com.twidroid", // twidroid - 5 000
	            "com.handmark.tweetcaster", // Tweecaster - 5 000
	            "com.thedeck.android" }; // TweetDeck - 5 000 };
	    Intent tweetIntent = new Intent();
	    tweetIntent.setType("text/plain");
	    final PackageManager packageManager = caller.getPackageManager();
	    List<ResolveInfo> list = packageManager.queryIntentActivities(
	            tweetIntent, PackageManager.MATCH_DEFAULT_ONLY);

	    for (int i = 0; i < twitterApps.length; i++) 
	    {
	        for (ResolveInfo resolveInfo : list) {
	            String p = resolveInfo.activityInfo.packageName;
	            if (p != null && p.startsWith(twitterApps[i])) {
	                tweetIntent.setPackage(p);
	                return tweetIntent;
	            }
	        }
	    }
	    return null;
	}
	

}
