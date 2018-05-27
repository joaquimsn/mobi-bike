package br.com.mobibike.transparencia.main.exceptions.enums;

import java.util.Objects;

import br.com.mobibike.transparencia.main.exceptions.ExposeBusinessException;
import br.com.mobibike.transparencia.main.exceptions.HttpStatusCode;

public enum TypeExposeBusinessException {

	CONTRATO_VIOLADO("geral.contratoViolado", "O dado informado não atende o padrão da model", HttpStatusCode.CONTRATO_VIOLADO),
	NAO_ENCONTRADO("geral.dadoNaoEncontrado", "Dado não encontrado", HttpStatusCode.RECURSO_NAO_ENCONTRADO),
	REGRA_GERAL("geral.regraNecocio", "Regra de negócio violada", HttpStatusCode.CONTRATO_VIOLADO),
	REGRA_CAMPOS_ENTITY("entity.campo.regraNegocio", "Existem campos obrigatórios não preenchidos", HttpStatusCode.VALIDACAO_CAMPOS),
	NAO_AUTENTICADO("autenticacao.naoAutenticado", "A credencial não tem acesso ao recurso solicitado", HttpStatusCode.NAO_AUTENTICADO),
	NAO_AUTORIZADO("autenticacao.naoAutorizado", "Credencial de acesso invalida", HttpStatusCode.NAO_AUTORIZADO),
	RECUPERACAO_CONTEUDO_TOKEN("autenticacao.token.erroRecuperarConteudo", "", HttpStatusCode.NAO_AUTORIZADO),
	TOKEN_INVALIDO("autenticacao.tokenInvalido", "", HttpStatusCode.NAO_AUTORIZADO),
	TOKEN_OBRIGATORIO("autenticacao.token.obrigatorio", "Autenticação necessaria, token não encontrado no header param authorization", HttpStatusCode.NAO_AUTENTICADO);

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
		return ExposeBusinessException.of(codigoSistema, message, detalhe, codigoHttp);
	}

	public ExposeBusinessException buildException(String detalhe, Throwable cause) {
		Objects.requireNonNull(detalhe);
		return ExposeBusinessException.of(codigoSistema, message, detalhe, codigoHttp, cause);
	}

	/**
	 * Cria uma BusinessException com uma mensagem padrão
	 *
	 * @return BusinessException
	 */
	public ExposeBusinessException buildException() {
		return ExposeBusinessException.of(codigoSistema, message, codigoHttp);
	}

	public ExposeBusinessException buildException(Throwable cause) {
		return ExposeBusinessException.of(codigoSistema, message, codigoHttp, cause);
	}

	public String getCodigoSistema() {
		return codigoSistema;
	}
}
