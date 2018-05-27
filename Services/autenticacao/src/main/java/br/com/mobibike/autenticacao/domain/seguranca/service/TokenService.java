package br.com.mobibike.autenticacao.domain.seguranca.service;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.lang.JoseException;

import br.com.mobibike.autenticacao.domain.seguranca.BikeToken;
import br.com.mobibike.autenticacao.domain.seguranca.TokenGenerator;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialSessao;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;
import br.com.mobibike.autenticacao.main.exceptions.ApplicationException;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeExposeBusinessException;
import br.com.mobibike.autenticacao.main.util.JsonUtils;


public class TokenService implements Serializable {
	private static final long serialVersionUID = -7662650127847163261L;
	private static Logger logger = LogManager.getLogger(AutenticacaoService.class);
	
	@Inject
	private TokenGenerator tokenGenerator;
	
	
	public BikeToken gerarTokenSessao(Credencial credencial) {
		String json = JsonUtils.objectToJson(CredencialSessao.of(credencial), CredencialSessao.class);
		
		String jwt = "";
		
		try {
			jwt = tokenGenerator.gerarJwt(json);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | JoseException | IOException e) {
			logger.error(e);
			throw TypeApplicationException.ERRO_GERACAO_TOKEN.buildException();
		} catch (Exception e) {
			logger.error("Não foi possível gerar o JWT", e);
			throw TypeApplicationException.FATAL.buildException();
		}

		return BikeToken.of(jwt);
	}
	
	public <T> BikeToken gerarTokenFromBean(T valueObject){
		String json = JsonUtils.objectToJson(valueObject, valueObject.getClass());
		
		String jwt = "";
		
		try {
			jwt = tokenGenerator.gerarJwt(json);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | JoseException | IOException e) {
			logger.error(e);
			throw TypeApplicationException.ERRO_GERACAO_TOKEN.buildException();
		} catch (Exception e) {
			logger.error("Não foi possível gerar o JWT", e);
			throw TypeApplicationException.FATAL.buildException();
		}

		return BikeToken.of(jwt);
	}

	public CredencialSessao recuperarUsuarioPor(String token) {
		String jwtDecriptado = "";	
		try {
			jwtDecriptado  = tokenGenerator.getJsonFromToken(token);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | InvalidJwtException e) {
			logger.info("O token informado é invalido", e);
			throw TypeExposeBusinessException.TOKEN_INVALIDO.buildException();
		}
		
		return JsonUtils.jsonToObject(jwtDecriptado, CredencialSessao.class);
	}
	
	public <T> T recuperarInformacao(String token, Class<T> classe){
		String jwtDecriptado = "";	
		try {
			jwtDecriptado  = tokenGenerator.getJsonFromToken(token);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | InvalidJwtException e) {
			logger.info("O token informado é invalido", e);
			throw TypeExposeBusinessException.TOKEN_INVALIDO.buildException();
		}
		
		try{
			return JsonUtils.jsonToObject(jwtDecriptado, classe);			
		}catch(ApplicationException ae){
			throw TypeExposeBusinessException.RECUPERACAO_CONTEUDO_TOKEN.buildException();
		}
		
	}
	
	public void validate(String token){
		try {
			tokenGenerator.getJsonFromToken(token);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException | InvalidJwtException e) {
			logger.info("Falha na validação do Token", e);
			throw TypeExposeBusinessException.TOKEN_INVALIDO.buildException("Para utilizar a API é obrigatório informar um token válido, envie o token no header com a chave Authorization");
		}
	}
}
