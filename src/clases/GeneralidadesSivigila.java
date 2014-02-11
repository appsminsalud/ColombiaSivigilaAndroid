package clases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.vigilatusalud_v3.BasedeDatos;
import com.vigilatusalud_v3.R;
import com.vigilatusalud_v3.R.id;

public class GeneralidadesSivigila extends Activity {

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) 
		{
		case R.id.salirAtras:
			finish();
			break;

		default:
			break;
		}		
		
		return super.onOptionsItemSelected(item);		
	}
	
	
	Spinner SpindominioInfo, SpintemaSpi,SpinSubtem;
	TextView TxtDescripcion, subtema;
	
	BasedeDatos baseDatos=new BasedeDatos();
	Context contextoM=this;	
	ArrayList<String> DominioListaString;
	ArrayList<String> TemasListaString;
	ArrayList<String> SubtemaListaString;
	ArrayList<String> DescripcionListaString;
	TextView tituloDescripcion;
	String inte;
	WebView enlaceView;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generalidades_sivigila);
//		setContentView(R.layout.prueba_margenes);
		
//		Se elimin� el spin con el fin de poner los 4 botones
//		SpindominioInfo=(Spinner)findViewById(R.id.spinnerGeneralidades);
		SpintemaSpi=(Spinner)findViewById(R.id.spinnerTEM);
		SpinSubtem=(Spinner)findViewById(R.id.spinnerSubtema);
		TxtDescripcion=(TextView)findViewById(R.id.txtGenralDescrip);
		tituloDescripcion = (TextView)findViewById(R.id.ac2);
		subtema=(TextView)findViewById(R.id.textoSubtema);
		enlaceView=(WebView)findViewById(R.id.webView);
		
//		llenar_spinner_dominio();
//		Se reemplaza la anterior linea por:
		
		Intent recibeInten=getIntent();
		inte=recibeInten.getStringExtra("TIPO-ITEM");
		setTitle(inte);
		llenar_spinner_Tema(inte);
//		Log.e("TIPO RESULTADO",inte);
		
//		SpindominioInfo.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1,
//					int index, long arg3) {
////				Log.e("SpinnerDominio",DominioListaString.get(index));
//				llenar_spinner_Tema(DominioListaString.get(index));				
//			}
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {}
//		});
		
		SpintemaSpi.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				
				String mensaje=TemasListaString.get(index);
				habilitar_subtema_descripcion(mensaje);
				Log.e("SpinnerTema",mensaje);
//				llenar_descripcion(TemasListaString.get(index));
				String mensaje2=TxtDescripcion.getText().toString();
				if(mensaje2.startsWith("http") || mensaje2.startsWith("https"))
				{
					Log.e("SI","Contiene http");
					enlaceView.setVisibility(View.VISIBLE);
					TxtDescripcion.setVisibility(View.INVISIBLE);
					tituloDescripcion.setVisibility(View.INVISIBLE);
				
					 DisplayMetrics displaymetrics = new DisplayMetrics();
					 getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
					 int height = displaymetrics.heightPixels;
					if (height <= 320)
					{
						enlaceView.setVisibility(View.INVISIBLE);
						AlertDialog.Builder alert = new AlertDialog.Builder(GeneralidadesSivigila.this);
						alert.setTitle("REPRESENTACI�N GR�FICA");
						WebView wv = new WebView(GeneralidadesSivigila.this);
						wv.loadUrl("https://scontent-a-mia.xx.fbcdn.net/hphotos-frc3/t1/1901270_1390107397919468_921929203_n.jpg");
						wv.getSettings().setJavaScriptEnabled(true);
						wv.getSettings().setBuiltInZoomControls(true);
						wv.setWebViewClient(new WebViewClient() {
						    @Override
						    public boolean shouldOverrideUrlLoading(WebView view, String url) {
						        view.loadUrl(url);

						        return true;
						    }
						});
						
						alert.setView(wv);
						alert.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int id) {
						        dialog.dismiss();
						    }
						});
						alert.show();
					}
					else
					{
						lanzar_webview("https://scontent-a-mia.xx.fbcdn.net/hphotos-frc3/t1/1901270_1390107397919468_921929203_n.jpg");
					}


					
				}
				else
				{
					Log.e("NO","No Contiene http");
					enlaceView.setVisibility(View.INVISIBLE);
					TxtDescripcion.setVisibility(View.VISIBLE);
					tituloDescripcion.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		SpinSubtem.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int index, long arg3) {
				String mensaje3=baseDatos.consultar_descripcion(SubtemaListaString.get(index)).get(0);				
				TxtDescripcion.setText(mensaje3);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		ImageView atras=(ImageView)findViewById(R.id.imgAtrasCuatro);
		atras.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.generalidades_sivigila, menu);
		return true;
	}
	
//	public void llenar_spinner_dominio()
//	{
//		try{
//			DominioListaString=baseDatos.consultar_DomInfo();
//		}
//		catch(Exception e)
//		{
//			Log.e(e.toString(),"NO pudo conectarse a la BD Genralidades table1");
//		}				
//		HashSet hs = new HashSet();
//		hs.addAll(DominioListaString);
//		DominioListaString.clear();
//		DominioListaString.addAll(hs);
//		Collections.sort(DominioListaString);
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,DominioListaString);
//		SpindominioInfo.setAdapter(adapter);
//	}
	
	
	
	public void lanzar_webview(String url)
	{
		enlaceView.getSettings().setJavaScriptEnabled(true);
	    enlaceView.getSettings().setBuiltInZoomControls(true);
		enlaceView.setWebViewClient(new WebViewClient());
		enlaceView.loadUrl(url);
	}
	
	public void llenar_spinner_Tema(String gener)
	{
		try{
			TemasListaString=baseDatos.consultar_Tema(gener);
//			for (String index: TemasListaString) {
//				if(TemasListaString.get(0).equals("null"))//||TemasListaString==null)
//				{
//					Log.e("NULA","La lista TEMA es null");
//					SpintemaSpi.setVisibility(View.INVISIBLE);
//				}
//				else{SpintemaSpi.setVisibility(View.VISIBLE);}
//			}
			
		}
		catch(Exception e)
		{
			Log.e(e.toString(),"NO pudo conectarse a la BD generalides Table2");
		}				
		HashSet hs = new HashSet();
		hs.addAll(TemasListaString);
		TemasListaString.clear();
		TemasListaString.addAll(hs);
		Collections.sort(TemasListaString);
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,TemasListaString);
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_listatexto,TemasListaString);
		SpintemaSpi.setAdapter(adapter);
	}
	
	public void habilitar_subtema_descripcion(String tema)
	{
		try{			
			SubtemaListaString=baseDatos.consultar_subTema(tema);			
			if(SubtemaListaString.get(0).equals("null")||SubtemaListaString.get(0).equals(""))//||SubtemaListaString==null)
			{
				Log.e("NULA","La lista SUBTEMA es null o vacia");
				SpinSubtem.setVisibility(View.INVISIBLE);
				subtema.setVisibility(View.INVISIBLE);
				imprimir_descripcion(tema,inte);
			}	
			else{
				HashSet hs = new HashSet();
				hs.addAll(SubtemaListaString);
				SubtemaListaString.clear();
				SubtemaListaString.addAll(hs);
				Collections.sort(SubtemaListaString);
				SpinSubtem.setVisibility(View.VISIBLE);
				subtema.setVisibility(View.VISIBLE);
//				ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,SubtemaListaString);
				ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.spinner_listatexto,SubtemaListaString);
				SpinSubtem.setAdapter(adapter);	
//				DescripcionListaString=baseDatos.consultar_descripcion(SubtemaListaString.get(0));
			}
		}
		catch(Exception e)
		{
			Log.e(e.toString(),"NO pudo conectarse a la BD generalides Table3");
		}	
	}
	
	public void imprimir_descripcion(String tema, String dominio)
	{
		String mensaje="";
		DescripcionListaString=baseDatos.consultar_descripcion_por_tema(tema, dominio);
		
		for (String cadaDescripcion: DescripcionListaString) 
		{	
//			Log.e("cadaDescripcion",cadaDescripcion);
			mensaje=mensaje+cadaDescripcion+"\n\n";
		}
		
		TxtDescripcion.setText(mensaje);
	}
	
//	public void llenar_descripcion(String tema)
//	{
//		try{
//			DescripcionListaString=baseDatos.getDescripcion();
//			SubtemaListaString=baseDatos.getSubtema();
//			if(SubtemaListaString.get(0).equals("null"))//||SubtemaListaString==null)
//			{
//				Log.e("NULA","La lista SUBTEMA es null");
//				SpinSubtem.setVisibility(View.INVISIBLE);
//			}	
//			else{SpinSubtem.setVisibility(View.VISIBLE);}
//		}
//		catch(Exception e)
//		{
//			Log.e(e.toString(),"NO pudo conectarse a la BD generalides Table4");
//		}		
//		
//		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,SubtemaListaString);
//		SpinSubtem.setAdapter(adapter);
//		
//		if(SpinSubtem.getVisibility()==View.INVISIBLE)
//		{
//		TxtDescripcion.setText(DescripcionListaString.get(0));
//		}
//		else{}		
//	}

}
