package com.ipro.utils.redesSociales;

public class MensajeRedesSociales {

	private String titulo;
	private String mensaje;
	private String url;
	

	public MensajeRedesSociales()
	{
		
	}
	public MensajeRedesSociales(String titulo, String mensaje, String url) {
		super();
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.url = url;
	}
	
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
