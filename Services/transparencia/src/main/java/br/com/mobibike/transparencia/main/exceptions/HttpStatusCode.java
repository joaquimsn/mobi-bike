package br.com.mobibike.transparencia.main.exceptions;

public abstract class HttpStatusCode {
	
	private HttpStatusCode() {
		throw new IllegalStateException("Utility class");
	}
	
	public static final int VALIDACAO_CAMPOS = 422;
	public static final int RECURSO_NAO_ENCONTRADO = 404;
	public static final int NAO_AUTORIZADO = 401;
	public static final int NAO_AUTENTICADO = 403;
	public static final int ERRO_INTERNO_SERVIDOR = 500;
	public static final int CONTRATO_VIOLADO = 400;
	public static final int SEM_RETORNO = 204;
	public static final int CADASTRADO = 201;
	public static final int SUCESSO = 200;

}