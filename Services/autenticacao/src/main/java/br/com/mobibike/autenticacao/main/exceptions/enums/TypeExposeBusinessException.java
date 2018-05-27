package br.com.mobibike.autenticacao.main.exceptions.enums;

import java.util.Objects;

import br.com.mobibike.autenticacao.main.exceptions.ExposeBusinessException;
import br.com.mobibike.autenticacao.main.exceptions.BikeHttpStatusCode;

public enum TypeExposeBusinessException {

	CONTRATO_VIOLADO("geral.contratoViolado", "Existem campos obrigatórios não preenchidos", BikeHttpStatusCode.CONTRATO_VIOLADO), 
	NAO_ENCONTRATO("geral.dadoNaoEncontrado", "Dado não encontrado", BikeHttpStatusCode.RECURSO_NAO_ENCONTRADO),
	REGRA_GERAL_("geral.regraNecocio", "", BikeHttpStatusCode.CONTRATO_VIOLADO),
	REGRA__CAMPOS("entity.campo.regraNegocio", "Regra de negócio violada", BikeHttpStatusCode.VALIDACAO_CAMPOS),
	NAO_AUTENTICADO("autenticacao.naoAutenticado", "A credencial não tem acesso ao recurso solicitado", BikeHttpStatusCode.NAO_AUTENTICADO), 
	NAO_AUTORIZADO("autenticacao.naoAutorizado", "Credencial de acesso invalida", BikeHttpStatusCode.NAO_AUTORIZADO),
	RECUPERACAO_CONTEUDO_TOKEN("autenticacao.token.erroRecuperarConteudo", "", BikeHttpStatusCode.NAO_AUTORIZADO),
	TOKEN_INVALIDO("autenticacao.tokenInvalido", "", BikeHttpStatusCode.NAO_AUTORIZADO),
	TOKEN_OBRIGATORIO("autenticacao.token.obrigatorio", "Autenticação necessaria, token não encontrado no header param authorization", BikeHttpStatusCode.NAO_AUTENTICADO);

	private String codigoSistema;
	private String message;
	private int codigoHttp;

	TypeExposeBusinessException(String codigoSistema, String message, int codigoHttp) {
		this.codigoSistema = codigoSistema;
		this.message = message;
		this.codigoHttp = codigoHttp;
	}

	/**
	 * Cria uma instancia da BusinessException com o detalhe informado
	 * 
	 * @param detalhe String que será concatenda a mensagem padrão
	 * @return BusinessException
	 */
	public ExposeBusinessException buildException(String detalhe) {
		Objects.requireNonNull(detalhe);
		return ExposeBusinessException.of(this.codigoSistema, detalhe, this.codigoHttp);
	}
	
	public ExposeBusinessException buildException(String detalhe, Throwable cause) {
		Objects.requireNonNull(detalhe);
		return ExposeBusinessException.of(this.codigoSistema, this.message + ": " + detalhe, this.codigoHttp, cause);
	}

	/**
	 * Cria uma BusinessException com uma mensagem padrão
	 * 
	 * @return BusinessException
	 */
	public ExposeBusinessException buildException() {
		return ExposeBusinessException.of(this.codigoSistema, this.message, this.codigoHttp);
	}
	
	public ExposeBusinessException buildException(Throwable cause) {
		return ExposeBusinessException.of(this.codigoSistema, this.message, this.codigoHttp, cause);
	}

	public String getCodigoSistema() {
		return this.codigoSistema;
	}
}
