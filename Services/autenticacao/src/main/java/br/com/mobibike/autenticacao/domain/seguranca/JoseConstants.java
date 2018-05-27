package br.com.mobibike.autenticacao.domain.seguranca;

public abstract class JoseConstants {
	
	/**
	 * Nome do arquivo da chave pública
	 */
	public static final String PUBLIC_KEY = "rest_public.key";

	public static final String KEY_ALGORITHM = "RSA";
	
	/**
	 * Nome do arquivo da chave privada
	 */
	public static final String PRIVATE_KEY = "rest_private.key";

	/**
	 * ID da chave para os Tokens da plataforma
	 */
	protected static final String JWK_ID = "";
}