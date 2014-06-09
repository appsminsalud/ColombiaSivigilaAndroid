package consultas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import clases.EventosClassNewLocalWEB;
import clases.MainActivity;

import com.ipro.utils.redesSociales.MensajeRedesSociales;
import com.ipro.utils.redesSociales.MenuCompartir;
import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import android.R.style;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Generalidades extends FragmentActivity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch(item.getItemId())
		{			
		// Para agregar una etiqueta		//
			case R.id.action_shared:
				if (MainActivity.isInternetAvailable(this))
				{
					MensajeRedesSociales msj = new MensajeRedesSociales();
					msj.setTitulo("Informacion de Evento de Salud Sivigila "+ evento);
					StringBuilder sb = new StringBuilder();
				
					sb.append("Nombre del Evento: \n"+evento+"\n");
					sb.append("Generalidades del Evento \n\n");
					if(claseEvento.getDescr_event().compareTo("")!=0) 
						sb.append("Descripción del Evento:\n "+claseEvento.getDescr_event()+"\n\n");
					
					if(claseEvento.getCas_sosp().compareTo("")!=0) 
						sb.append("Caso Sospechoso:\n "+claseEvento.getCas_sosp()+"\n\n");
					
					if(claseEvento.getCas_prob().compareTo("")!=0) 
						sb.append("Caso Probable:\n "+claseEvento.getCas_prob()+"\n\n");
					
					if(claseEvento.getCas_conf().compareTo("")!=0) 
						sb.append("Caso Confirmado:\n "+claseEvento.getCas_conf()+"\n\n");
					
					if(claseEvento.getTiem_notif().compareTo("")!=0) 
						sb.append("Tiempo de Notificación:\n "+claseEvento.getTiem_notif()+"\n\n");
					
					if(claseEvento.getFich_notif().compareTo("")!=0) 
						sb.append("Ficha de Notificación:\n "+claseEvento.getFich_notif()+"\n\n");
					
					if(claseEvento.getDiag_dif().compareTo("")!=0) 
						sb.append("Diagnostico Diferencial:\n "+claseEvento.getDiag_dif()+"\n\n");

						sb.append("Apoyo Diagnostico \n\n");
					
					if(claseEvento.getApo_lab().compareTo("")!=0) 
						sb.append("Apoyo de Laboratorio:\n "+claseEvento.getApo_lab()+"\n\n");
					
					if(claseEvento.getOtr_apoyo().compareTo("")!=0) 
						sb.append("Otro Apoyo:\n "+claseEvento.getOtr_apoyo()+"\n\n");
					
						sb.append("Acciones de Control \n\n");
						
					if(claseEvento.getAcc_ind().compareTo("")!=0) 
						sb.append("Acciones Individuales:\n "+claseEvento.getAcc_ind()+"\n\n");
					
					if(claseEvento.getAcc_colec().compareTo("")!=0) 
						sb.append("Acciones Colectivas:\n "+claseEvento.getAcc_colec()+"\n\n");
					
					if(claseEvento.getLink_url().compareTo("")!=0) 
						sb.append("Link/URL Protocolo Completo:\n "+claseEvento.getLink_url()+"\n\n");
					
					sb.append("Enviado desde Colombia SiVigila App");
					
					msj.setMensaje(sb.toString());
					
					msj.setUrl(claseEvento.getLink_url());
					MenuCompartir.mostrarMenuAndCompartir(this, msj);
				}
				else
				{
					 Toast.makeText(this, "Se necesita activar el acceso a internet para usar esta funcionalidad.", Toast.LENGTH_LONG).show();
				}
				return true;	
			case R.id.atras_gen:
				finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
		
	}

	String evento;
	EventosClassNewLocalWEB claseEvento;
	TextView Grupo, Subgrupo,Evento, Descripcion, CasosSospechosos, CasosProvados,
	CasosConfirmado, TiemposNotif, FichaNotif, DiagDiff, ApoyoLaboratorio,
	OtroApoyo, AccionesIndividuales, AccionesColectias, LinkUrl;
	
//	TextView casosPro,casosSosp, casosConfir, fichaNotifa, timeNotifa;
	
	LinearLayout linearLayot;
//	ScrollView linearLayot;
	Context contexto=this;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.plantilla_generalidades);
		setContentView(R.layout.plantilla_generalidades_mejoras);
		
		Intent i=getIntent();
		evento=i.getStringExtra("EVENTO");
		
		Evento=(TextView)findViewById(R.id.lynomevent);
		
//		Descripcion=(TextView)findViewById(R.id.lyDescrip);
//		CasosSospechosos=(TextView)findViewById(R.id.lyCasSosp);
//		CasosProvados=(TextView)findViewById(R.id.lyCasProb);
//		CasosConfirmado=(TextView)findViewById(R.id.lyCasConf);
//		TiemposNotif=(TextView)findViewById(R.id.lyTime);
//		FichaNotif=(TextView)findViewById(R.id.lyFicha);
		
//		casosConfir=(TextView)findViewById(R.id.tx_casosConfir);
//		casosPro=(TextView)findViewById(R.id.tx_enlaces);
//		casosSosp=(TextView)findViewById(R.id.tx_casosSospe);
//		fichaNotifa=(TextView)findViewById(R.id.tx_fichaNotif);
//		timeNotifa=(TextView)findViewById(R.id.tx_timenotif);

		
		
		/**
		 * Nuevo Código para las Mejoras de los espacio version 1
		 */
//		linearLayot=(LinearLayout)findViewById(R.id.espacio);
		linearLayot=(LinearLayout)findViewById(R.id.espacio);
		
		
		BasedeDatos database=new BasedeDatos();
		EventosClassNewLocalWEB EventoSelected=database.consultar_Evento_unico(evento);
		claseEvento=EventoSelected;
		Log.e("Parametro Evento",evento);
		
		//publicarInformacion(EventoSelected);
		try
		{
		publicarInformacionConsecutivos(EventoSelected);
		}
		catch(Exception e)
		{
			Log.e("Error Publicando",e.toString());
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_compartir, menu);
		return true;
	}
	
//	public void publicarInformacion(EventosClassNewLocalWEB eventoSelect)
//	{
//		Evento.setText(eventoSelect.getNom_even());
//		if(!eventoSelect.getDescr_event().equals(""))//eventoSelect.getDescr_event()!=null||
//		Descripcion.setText(eventoSelect.getDescr_event());
//		else
//		{Descripcion.setVisibility(View.INVISIBLE);}
//		if((!eventoSelect.getCas_sosp().equals("")))//(eventoSelect.getCas_sosp()!=null)||
//		{
////			Log.e("Sospechoso","Diferente de nulo:"+eventoSelect.getCas_sosp()+"ParaPRobelmas");
//			CasosSospechosos.setText(eventoSelect.getCas_sosp());
//		}
//		else
//		{CasosSospechosos.setVisibility(View.INVISIBLE);
//		casosSosp.setVisibility(View.INVISIBLE);}
//		if(!eventoSelect.getCas_prob().equals(""))//eventoSelect.getCas_prob()!=null||
//		CasosProvados.setText(eventoSelect.getCas_prob());
//		else
//		{CasosProvados.setVisibility(View.INVISIBLE);
//		casosPro.setVisibility(View.INVISIBLE);}
//		if(!eventoSelect.getCas_conf().equals(""))//eventoSelect.getCas_conf()!=null||
//		CasosConfirmado.setText(eventoSelect.getCas_conf());
//		else
//		{CasosConfirmado.setVisibility(View.INVISIBLE);
//		casosConfir.setVisibility(View.INVISIBLE);}
//		if(!eventoSelect.getTiem_notif().equals(""))//eventoSelect.getTiem_notif()!=null||
//		TiemposNotif.setText(eventoSelect.getTiem_notif());
//		else
//		{TiemposNotif.setVisibility(View.INVISIBLE);
//		timeNotifa.setVisibility(View.INVISIBLE);}
//		if(!eventoSelect.getFich_notif().equals(""))//eventoSelect.getFich_notif()!=null||
//		FichaNotif.setText(eventoSelect.getFich_notif());
//		else
//		{FichaNotif.setVisibility(View.INVISIBLE);
//		fichaNotifa.setVisibility(View.INVISIBLE);}		
//
//	}
	
	public void publicarInformacionConsecutivos(EventosClassNewLocalWEB eventoSelect)
	{
		Evento.setText(eventoSelect.getNom_even());
		if(!eventoSelect.getDescr_event().equals(""))//eventoSelect.getDescr_event()!=null||
		{
//			Descripcion.setText(eventoSelect.getDescr_event());
//			ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
			ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
			bloDesp.get(0).setText("Descripción del Evento");
			bloDesp.get(1).setText(eventoSelect.getDescr_event());
			agregarEspacioBlanco(linearLayot);
		}
//		else
//		{Descripcion.setVisibility(View.INVISIBLE);}
		
		
		if((!eventoSelect.getCas_sosp().equals("")))//(eventoSelect.getCas_sosp()!=null)||
		{
//			Log.e("Sospechoso","Diferente de nulo:"+eventoSelect.getCas_sosp()+"ParaPRobelmas");
//			CasosSospechosos.setText(eventoSelect.getCas_sosp());
//			ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
			ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
			bloDesp.get(0).setText("Casos Sospechosos:");
			bloDesp.get(1).setText(eventoSelect.getCas_sosp());
			agregarEspacioBlanco(linearLayot);
		}
//		else
//		{CasosSospechosos.setVisibility(View.INVISIBLE);
//		casosSosp.setVisibility(View.INVISIBLE);}
		
		
		if(!eventoSelect.getCas_prob().equals(""))//eventoSelect.getCas_prob()!=null||
		{
//		CasosProvados.setText(eventoSelect.getCas_prob());
//		ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
		ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
		bloDesp.get(0).setText("Casos Probables:");
		bloDesp.get(1).setText(eventoSelect.getCas_prob());
		agregarEspacioBlanco(linearLayot);
		}
//		else
//		{CasosProvados.setVisibility(View.INVISIBLE);
//		casosPro.setVisibility(View.INVISIBLE);}
		
		
		if(!eventoSelect.getCas_conf().equals(""))//eventoSelect.getCas_conf()!=null||
		{
//			CasosConfirmado.setText(eventoSelect.getCas_conf());
//			ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
			ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
			bloDesp.get(0).setText("Casos Confirmados:");
			bloDesp.get(1).setText(eventoSelect.getCas_conf());
			agregarEspacioBlanco(linearLayot);
		}
//		else
//		{CasosConfirmado.setVisibility(View.INVISIBLE);
//		casosConfir.setVisibility(View.INVISIBLE);}
		
		
		if(!eventoSelect.getTiem_notif().equals(""))//eventoSelect.getTiem_notif()!=null||
		{
//		TiemposNotif.setText(eventoSelect.getTiem_notif());
//		ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
		ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
		bloDesp.get(0).setText("Tiempo de Notificación:");
		bloDesp.get(1).setText(eventoSelect.getTiem_notif());
		agregarEspacioBlanco(linearLayot);
		}
//		else
//		{TiemposNotif.setVisibility(View.INVISIBLE);
//		timeNotifa.setVisibility(View.INVISIBLE);}
		
		
		if(!eventoSelect.getFich_notif().equals(""))//eventoSelect.getFich_notif()!=null||
		{
//			FichaNotif.setText(eventoSelect.getFich_notif());
//			ArrayList<TextView> bloDesp=agregarBloquesTextosScroll(linearLayot);
			ArrayList<TextView> bloDesp=agregarBloquesTextos(linearLayot);
			bloDesp.get(0).setText("Ficha de Notificación:");
			bloDesp.get(1).setText(eventoSelect.getFich_notif());
			agregarEspacioBlanco(linearLayot);
		}
//		else
//		{FichaNotif.setVisibility(View.INVISIBLE);
//		fichaNotifa.setVisibility(View.INVISIBLE);}		

	}
	
	public void quitar_antiguosTextos()
	{
		
	}
	
	public void mostrar_linkINFO(String url_link)
	 {
		//Agrega el formato link
		SpannableString myString  = new SpannableString(url_link);
		myString.setSpan(new UnderlineSpan(), 0, myString.length(), 0);
		LinkUrl.setTextSize(12);
		LinkUrl.setText(myString);
		
		
		//Agrega el Enlace
		if((url_link!=null)||(!url_link.equals("null")))
		{
		LinkUrl.setText(Html.fromHtml(
				" "
				+ "<a href=" 
				+ url_link 
				+ ">" 
				+url_link 
				+"</a>"
				));
		LinkUrl.setMovementMethod(LinkMovementMethod.getInstance());
		}
	 }
	
	 public void action_shared()
		{
		 //Para compartir en redes sociales
			Intent share =new Intent();
			share.setAction(Intent.ACTION_SEND);
			String msg= "El usuario '";						
			share.putExtra(Intent.EXTRA_TEXT,msg);	
			startActivity(Intent.createChooser(share, "Compartir"));			
		}
	 
	 public void shareSocialNetwork(String title, String extraTitle,String filename) 
	 {
		 // "claseEvento" contiene todos los valores de la consulta del evento
	        Intent share = new Intent(Intent.ACTION_SEND);
	        share.setType("text/plain");
	        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+evento+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+"\n\n" +
	    	        		"Colombia SiVigila";											
			share.putExtra(Intent.EXTRA_TEXT,msg);
	        startActivity(Intent.createChooser(share, title));
	 }
	 
	 
	 public void shareSocialNetwork2(String title, String extraTitle,String filename) 
	 {
		 // "claseEvento" contiene todos los valores de la consulta del evento
	        Intent share = new Intent(Intent.ACTION_SEND);
	        share.setType("text/plain");
			share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+evento+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+"\n\n" +
	    	        		"Colombia SiVigila";											
			share.putExtra(Intent.EXTRA_TEXT,msg);					
	        startActivity(Intent.createChooser(share, title));
	 }
	 
	 
	 public void compartirFacebbok(View v)
	 {
	 Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
	 shareIntent.setType("text/plain");
	 shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, 
//			 (String) v.getTag(R.string.app_name))
			 "Subtema")
			 ;
	 shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, 
//			 (String) v.getTag(R.drawable.ic_launcher))
			 "texto que conttiene este asusnto")
			 ;
	 PackageManager pm = v.getContext().getPackageManager();
	 List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
	 for (final ResolveInfo app :activityList) {
	 if ((app.activityInfo.name).contains("facebook")) 
	 {
		 final ActivityInfo activity = app.activityInfo;
		 final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
		 shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		 shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
		 shareIntent.setComponent(name);
		 v.getContext().startActivity(shareIntent);
		 break;
	 }
	 }
	 }
	 
	 
	/* ---------------------------------------------------------------------------------------------------
	 * Nuevo Código para las Mejoras de los espacio version 1
	 *-------------------------------------------------------------------------------------------------*/
	private ArrayList<TextView> agregarBloquesTextos(LinearLayout layout)
	{
		/*Declaramos los estilos para */ 
		/*El sguiente código es para agregar fuentes*/
		//Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/myFont.ttf");
		//MyTextView.setTypeface(mFont);
		
		TextView titulo=new TextView(contexto);			
		TextView descrip=new TextView(contexto);		
		
		titulo.setTextAppearance(contexto, R.style.subtitulos);
		descrip.setTextAppearance(contexto, R.style.normaltext);
		
		layout.addView(titulo, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
		layout.addView(descrip, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
		
		ArrayList<TextView> objeto=new ArrayList<TextView>();
		objeto.add(titulo);
		objeto.add(descrip);
		
		return objeto;
	}
	private void quitarBloquesTextos(LinearLayout layout, Object objeto)
	{
		layout.removeView((View)objeto);
	}
	
	private void agregarEspacioBlanco(LinearLayout ly)
	{
		TextView margen=new TextView(contexto);		
		ly.addView(margen, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
		margen.setText(" ");
	}
	
	private ArrayList<TextView> agregarBloquesTextosScroll(ScrollView layout)
	{
		/*Declaramos los estilos para */ 
		/*El sguiente código es para agregar fuentes*/
		//Typeface mFont = Typeface.createFromAsset(getAssets(), "fonts/myFont.ttf");
		//MyTextView.setTypeface(mFont);
		
		TextView titulo=new TextView(contexto);			
		TextView descrip=new TextView(contexto);
		
		titulo.setTextAppearance(contexto, R.style.subtitulos);
		descrip.setTextAppearance(contexto, R.style.normaltext);
		
		layout.addView(titulo, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
		layout.addView(descrip, ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT);
		
		ArrayList<TextView> objeto=new ArrayList<TextView>();
		objeto.add(titulo);
		objeto.add(descrip);
		
		return objeto;
	}
 

}
