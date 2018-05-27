package br.com.mobibike.autenticacao.main;

public abstract class BikeUriConventions {

	private BikeUriConventions() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final String BASE_URL_API = "/api";
	public static final String BASE_URL_PUBLIC_API = "/api/public";
	public static final String BASE_URL_API_AUTH = "/api/auth";
}
