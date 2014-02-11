package clases;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

public class ControladorPantalla extends Activity{

	public Resources getResources2() {return resources2;}
	public void setResources2(Resources resources2) {this.resources2 = resources2;}
	public DisplayMetrics getMetrics() {return metrics;}
	public void setMetrics(DisplayMetrics metrics) {this.metrics = metrics;}

	public Display getDisplay() {return display;}
	public void setDisplay(Display display) {this.display = display;}

	//	Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
	Display display;
	DisplayMetrics metrics;
	Resources resources2;
	
	public ControladorPantalla()
	{}
	
	
	public void dimensionesPantalla()
	{		
		Log.e("Ancho de la Pantalla " , Integer.toString(display.getWidth())+"");
		Log.e("Alto de la pantalla " , Integer.toString(display.getHeight())+"");
		Log.e("Densidad de la pantalla (dpi) " , getResources().getDisplayMetrics().densityDpi+"");
		
		float scale = getApplicationContext().getResources().getDisplayMetrics().density;
	    Log.e("Escala " , Float.toString(getApplicationContext().getResources().getDisplayMetrics().density)+"");
	    
	    // buscando los pixeles a partir de dips con la densidad
        int dips = 200;
        float pixelBoton = 0;
        float scaleDensity = 0;
         
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metrics);
         
        switch(metrics.densityDpi)
        {

        case DisplayMetrics.DENSITY_XHIGH: //HDPI
            Log.e("Tipo Densidad","Muy Alta Densidad");
            scaleDensity = scale * 300;
            pixelBoton = dips * (scaleDensity / 240);
            break;
        case DisplayMetrics.DENSITY_HIGH: //HDPI
            Log.e("Tipo Densidad","Alta Densidad");
            scaleDensity = scale * 240;
            pixelBoton = dips * (scaleDensity / 240);
            break;
        case DisplayMetrics.DENSITY_MEDIUM: //MDPI
        	Log.e("Tipo Densidad","Mediana Densidad");
            scaleDensity = scale * 160;
            pixelBoton = dips * (scaleDensity / 160);
            break;
             
        case DisplayMetrics.DENSITY_LOW:  //LDPI
        	Log.e("Tipo Densidad","Baja Densidad");
            scaleDensity = scale * 120;
            pixelBoton = dips * (scaleDensity / 120);
            break;
        }
        Log.e(getClass().getSimpleName(), "pixels:"+Float.toString(pixelBoton));
	}
	
	public void recursosPantalla()
	{
//		Resources resources2 = getResources();
		int dips = 160;
		float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dips, resources2.getDisplayMetrics());
		Log.e("Pixeles:",pixels+"");
	}
}