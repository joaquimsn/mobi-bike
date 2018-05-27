package br.com.mobibike.autenticacao.domain.seguranca.beans;

import br.com.mobibike.autenticacao.main.exceptions.enums.TypeExposeBusinessException;

public class TokenAcesso {
	private String token;
	private String funcionalidade;
	private String acesso;

	private static final String AUTHORIZATION_TYPE = "jwt";

	public String getToken() {
		if (token == null || token.split(":").length != 2) {
			throw TypeExposeBusinessException.CONTRATO_VIOLADO.buildException();
		}

		String[] authorization = token.split(":");

		// No index deve possui o tipo da autenticação
		if (!authorization[0].equals(AUTHORIZATION_TYPE)) {
			throw TypeExposeBusinessException.CONTRATO_VIOLADO.buildException();
		}

		return authorization[1];
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(String funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public String getAcesso() {
		return acesso;
	}

	public void setAcesso(String acesso) {
		this.acesso = acesso;
	}

}
