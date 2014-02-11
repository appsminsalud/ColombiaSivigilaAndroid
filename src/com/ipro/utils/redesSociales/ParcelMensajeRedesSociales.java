package com.ipro.utils.redesSociales;


import android.os.Parcel;
import android.os.Parcelable;

public class ParcelMensajeRedesSociales implements Parcelable {

	private MensajeRedesSociales objetoSimple;

	
	public MensajeRedesSociales getMensajeRedesSociales()
	{
		return objetoSimple;
	}
	
	
	public ParcelMensajeRedesSociales(MensajeRedesSociales init)
	{
		super();
		this.objetoSimple = init;
	}
	
	
	private ParcelMensajeRedesSociales(Parcel in) {
			objetoSimple = new MensajeRedesSociales();
			objetoSimple.setTitulo(in.readString());
			objetoSimple.setMensaje(in.readString());
			objetoSimple.setUrl(in.readString());
    }
	
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int flags) {
	parcel.writeString(objetoSimple.getTitulo());
	parcel.writeString(objetoSimple.getMensaje());
	parcel.writeString(objetoSimple.getUrl());	
	
    
	}


	 public static final Parcelable.Creator<ParcelMensajeRedesSociales> CREATOR =
	            new Parcelable.Creator<ParcelMensajeRedesSociales>() {
	        public ParcelMensajeRedesSociales createFromParcel(Parcel in) {
	            return new ParcelMensajeRedesSociales(in);
	        }
	 
	        public ParcelMensajeRedesSociales[] newArray(int size) {
	            return new ParcelMensajeRedesSociales[size];
	        }
	    };

}
