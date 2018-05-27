package br.com.mobibike.autenticacao.domain.seguranca.service;

import java.io.Serializable;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.autenticacao.domain.seguranca.BikeToken;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialAcesso;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialSessao;
import br.com.mobibike.autenticacao.domain.seguranca.beans.UsuarioSessao;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeExposeBusinessException;

@Singleton
public class AutenticacaoService implements Serializable {
	private static final long serialVersionUID = 2066145209618602032L;

	private static Logger logger = LogManager.getLogger(AutenticacaoService.class);

	@Inject
	private TokenService tokenService;
	@Inject
	private CredencialService credencialService;
	@Inject
	private AplicacaoService aplicacaoService;

	public UsuarioSessao autenticar(CredencialAcesso credencialAcesso, String nomeAplicacao) {

		Aplicacao aplicacao = aplicacaoService.buscarPorNome(nomeAplicacao)
				.orElseThrow(() -> TypeExposeBusinessException.CONTRATO_VIOLADO.buildException("A aplicação: " + nomeAplicacao + " não é suportada"));

		UsuarioSessao usuario;
		try {
			usuario = criarUsuarioSessao(credencialService.buscarPor(credencialAcesso, aplicacao));
		} catch (Exception e) {
			throw TypeExposeBusinessException.NAO_AUTORIZADO.buildException();
		}

		if (Objects.isNull(usuario)) {
			logger.warn("Tentativa de login: " + credencialAcesso.getUsuario() + " Aplicação: " + nomeAplicacao);
			throw TypeExposeBusinessException.NAO_AUTORIZADO.buildException();
		}

		logger.info("Login realizado para: " + credencialAcesso);

		return usuario;
	}

	private UsuarioSessao criarUsuarioSessao(Credencial credencial) {
		System.out.println("Credencial retornada" + credencial);
		BikeToken bikeToken = tokenService.gerarTokenSessao(credencial);

		return UsuarioSessao.of(credencial, bikeToken);
	}

	public CredencialSessao recuperarCredencialToken(String token) {
		return tokenService.recuperarUsuarioPor(token);
	}

}
