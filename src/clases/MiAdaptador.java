package clases;

import java.util.ArrayList;
import java.util.Vector;

import com.vigilatusalud_v3.R;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MiAdaptador extends BaseAdapter {
       
//       private final Vector<String> lista;
//       private final String[] lista;
       private final ArrayList<String> lista;
       private final Activity actividad;
//       String 
       
//       public MiAdaptador(Activity actividad, Vector<String> lista) {
       public MiAdaptador(Activity actividad, ArrayList<String> lista)
       {
             super();
             this.actividad = actividad;
             this.lista = lista;             
       }

       public View getView(int position, View convertView,ViewGroup parent) {
    	   	
    	   	 TextView evento;
    	   
             LayoutInflater inflater = actividad.getLayoutInflater();
             View view = inflater.inflate(R.layout.referlista_negra, null,true);
             
             evento =(TextView)view.findViewById(R.id.textoRefer);           
             
             evento.setText(
            		 lista.get(position)+""
            		 );                                               
             
             return view;
       }               
       

       public int getCount() {
             return lista.size();
//    	   return lista.length;
       }

       public Object getItem(int arg0) {
//         return lista.elementAt(arg0);
//    	   return lista[arg0];
    	   return lista.get(arg0);
       }

       public long getItemId(int position) {
             return position;
       }
}
