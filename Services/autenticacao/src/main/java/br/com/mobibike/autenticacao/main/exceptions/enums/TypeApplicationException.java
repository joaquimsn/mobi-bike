package br.com.mobibike.autenticacao.main.exceptions.enums;

import br.com.mobibike.autenticacao.main.exceptions.ApplicationException;
import br.com.mobibike.autenticacao.main.exceptions.FatalErroStartupApplicationException;

public enum TypeApplicationException {
	GENERIC_ERROR("applicaton.erro.inesperado", "Erro inesperado, tente novamente", FatalErroStartupApplicationException.class),
	STARTUP_ERROR("applicaton.fatal.startup", "Não foi possível iniciar a aplicação", FatalErroStartupApplicationException.class),
	EMAIL_ERROR("email.send.erro", "Erro no envio de email", ApplicationException.class),
	PERSISTENCIA("application.dao.erro", "Erro na camada de persistência", ApplicationException.class),
	FATAL("bike.system.fatal", "Falha interna", ApplicationException.class),
	ERRO_SALVAR_ARQUIVO("application.erro.gerenciadorArquivo", "Falha ao salvar o arquivo", ApplicationException.class),
	ERRO_CARREGAR_ARQUIVO("application.erro.carregarArquivo", "Não foi possível carregar o arquivo", ApplicationException.class),
	OBJECT_CONVERTER_JSON("application.erro.objectConverter", "Impossivel converter o objeto para Json", ApplicationException.class),
	JSON_CONVERTER_OBJECT("application.erro.jsonConverter", "Impossivel converter o json para Objeto", ApplicationException.class),
	ERRO_GERACAO_TOKEN("application.erro.geracaoToken", "Falha na geração do Token", ApplicationException.class);
	
	private String codigo;
	private String mensagem;
	private Class<?> classException;

	private TypeApplicationException(String codigo, String mensagem, Class<?> classException) {
		this.codigo = codigo;
		this.mensagem = mensagem;
		this.classException = classException;
	}

	public ApplicationException buildException() {
		if (classException.isAssignableFrom(ApplicationException.class)) {
			return ApplicationException.of(this.codigo, this.mensagem);
		}
		
		return FatalErroStartupApplicationException.of(this.codigo, this.mensagem);
	}
	
	public ApplicationException buildException(String mensagem) {
		if (classException.isAssignableFrom(ApplicationException.class)) {
			return ApplicationException.of(this.codigo, this.mensagem + ": " + mensagem);
		}
		
		return FatalErroStartupApplicationException.of(this.codigo, this.mensagem + ": " + mensagem);
	}
	
	public String getCodigo(){
		return this.codigo;
	}
	
	public String getMensagem() {
		return mensagem;
	}
}
