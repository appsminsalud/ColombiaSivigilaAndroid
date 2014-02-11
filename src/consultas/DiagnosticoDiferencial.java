package consultas;

import clases.EventosClassNewLocalWEB;
import clases.MainActivity;

import com.ipro.utils.redesSociales.MensajeRedesSociales;
import com.ipro.utils.redesSociales.MenuCompartir;
import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.JustifiedTextView;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.TextJustify;
import com.vigilatusalud_v3.R.layout;
import com.vigilatusalud_v3.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class DiagnosticoDiferencial extends Activity {
	
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
	TextView Grupo, Subgrupo,Evento, Descripcion, CasosSospechosos, CasosProvados,
	CasosConfirmado, TiemposNotif, FichaNotif, DiagDiff, ApoyoLaboratorio,
	OtroApoyo, AccionesIndividuales, AccionesColectias, LinkUrl;
	float ancho;
	
	TextView diagnotico;
	EventosClassNewLocalWEB claseEvento;
	LinearLayout contenedor;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.plantilla_diagnostico_diferencial);
		
		Intent i=getIntent();
		evento=i.getStringExtra("EVENTO");
		
//		Grupo=(TextView)findViewById(R.id.PublicNomGrup);
//		Subgrupo=(TextView)findViewById(R.id.PublicNomSubGrup);
		Evento=(TextView)findViewById(R.id.lynomevent);
//		Descripcion=(TextView)findViewById(R.id.lyDescrip);
//		CasosSospechosos=(TextView)findViewById(R.id.lyCasSosp);
//		CasosProvados=(TextView)findViewById(R.id.lyCasProb);
//		CasosConfirmado=(TextView)findViewById(R.id.lyCasConf);
//		TiemposNotif=(TextView)findViewById(R.id.lyTime);
//		FichaNotif=(TextView)findViewById(R.id.lyFicha);
		DiagDiff=(TextView)findViewById(R.id.lyDiagnostico);
		diagnotico=(TextView)findViewById(R.id.tx_accIndivi);		
//		ApoyoLaboratorio=(TextView)findViewById(R.id.PublicApoyoLab);		
//		OtroApoyo=(TextView)findViewById(R.id.PublicOtroApoyo);
//		AccionesIndividuales=(TextView)findViewById(R.id.PublicAccInd);
//		AccionesColectias=(TextView)findViewById(R.id.PublicAccColec);
//		LinkUrl=(TextView)findViewById(R.id.PublicLinURl);
		
		contenedor=(LinearLayout)findViewById(R.id.contenedor);		
		
		BasedeDatos database=new BasedeDatos();
		EventosClassNewLocalWEB EventoSelected=database.consultar_Evento_unico(evento);
		claseEvento=EventoSelected;
		Log.e("Parametro Evento",evento);
		publicarInformacion(EventoSelected);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_compartir, menu);
		return true;
	}

	public void publicarInformacion(EventosClassNewLocalWEB eventoSelect)
	{
		TextJustify TextViewJustify=new TextJustify();
		float ancho2=(float)contenedor.getWidth();
		
//		Grupo.setText(eventoSelect.getNom_grup());
//		Subgrupo.setText(eventoSelect.getNom_subgru());
		Evento.setText(eventoSelect.getNom_even());
//		Descripcion.setText(eventoSelect.getDescr_event());
//		CasosSospechosos.setText(eventoSelect.getCas_sosp());
//		CasosProvados.setText(eventoSelect.getCas_prob());
//		CasosConfirmado.setText(eventoSelect.getCas_conf());
//		TiemposNotif.setText(eventoSelect.getTiem_notif());
//		FichaNotif.setText(eventoSelect.getFich_notif());
		if(!eventoSelect.getDiag_dif().equals(""))//||eventoSelect.getDiag_dif()!=null
		{
		DiagDiff.setText(eventoSelect.getDiag_dif());
//		TextViewJustify.run(DiagDiff, 275f); 
//		Log.e("Ancho",275f+"");
		}
		else
		{DiagDiff.setVisibility(View.INVISIBLE);
		diagnotico.setVisibility(View.INVISIBLE);}

//		ApoyoLaboratorio.setText(eventoSelect.getApo_lab());
//		OtroApoyo.setText(eventoSelect.getOtr_apoyo());
//		AccionesIndividuales.setText(eventoSelect.getAcc_ind());
//		AccionesColectias.setText(eventoSelect.getAcc_colec());
//		try
//		{
//			if(!(eventoSelect.getLink_url().equals("")))
//			{
//				Log.e("Vacio","Es diferente a vacio");
//				if(!(eventoSelect.getLink_url().equals(null)))					
//				{Log.e("Vacio","Es diferente a null");
//				mostrar_linkINFO(eventoSelect.getLink_url());
//				}
//				else
//				{LinkUrl.setText("");}
//			}
//			else
//			{LinkUrl.setText("");}
//		}
//		catch(Exception e)
//		{
//			Log.e("Error-Link","No se pudo formar el link "+e.toString());
//			LinkUrl.setText("");
//		}
		
//		LinkUrl.setText(eventoSelect.getLinkurl());		
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
	
	 public void shareSocialNetwork(String title, String extraTitle,String filename) 
	 {
		 // "claseEvento" contiene todos los valores de la consulta del evento
	        Intent share = new Intent(Intent.ACTION_SEND);
	        share.setType("text/plain");
	        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
//	        share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filename)));
	        share.putExtra(Intent.EXTRA_TITLE, extraTitle);
	        share.putExtra(Intent.EXTRA_SUBJECT, extraTitle);
	        String msg= "Nombre del Evento: \n"+evento+"\n\n" +
	        		"Descripción:\n "+claseEvento.getDescr_event()+"\n\n" +
	    	        		"Colombia SiVigila";											
			share.putExtra(Intent.EXTRA_TEXT,msg);
//	        share.setType("image/png");
	        startActivity(Intent.createChooser(share, title));
	 }
}
