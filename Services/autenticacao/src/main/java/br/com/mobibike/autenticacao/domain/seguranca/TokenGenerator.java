package br.com.mobibike.autenticacao.domain.seguranca;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;

import br.com.mobibike.autenticacao.main.Ambiente;
import br.com.mobibike.autenticacao.main.exceptions.FatalErroStartupApplicationException;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;
import br.com.mobibike.autenticacao.main.util.FileUtils;

@ApplicationScoped
public class TokenGenerator {

	private static Logger logger = LogManager.getLogger(TokenGenerator.class);

	private static final String ISSUER = "-PORTAL-EAD";
	private static final String SUBJECT = "User";
	private static final String AUDIENCE = "CMS_PORTAL_EAD_APP";
	

	private PrivateKey privateKey;
	private PublicKey publicKey;
	
	@PostConstruct
	private void init() {
		gerarJwtKeys();
	}

	public void gerarJwtKeys() throws FatalErroStartupApplicationException {
		try {
			if (!FileUtils.exists(JoseKey.DIRETORIO_CHAVES_ASSIMETRICAS + File.separator + JoseConstants.PRIVATE_KEY)
					|| !FileUtils.exists(JoseKey.DIRETORIO_CHAVES_ASSIMETRICAS + File.separator + JoseConstants.PUBLIC_KEY)) {
				JoseKey.gerarChavesAssimetricasJwt();
			}
			
			privateKey = loaderJwtPrivateKey();
			publicKey = loaderJwtPublicKey();
		} catch (Exception e) {
			logger.error("Falha ao gerar/carregar as chaves assimetricas", e);
			throw TypeApplicationException.STARTUP_ERROR.buildException();
		}
	}

	private PrivateKey loaderJwtPrivateKey() throws Exception {
		return JoseKey.loaderJwtPrivateKey();
	}

	private PublicKey loaderJwtPublicKey() throws Exception {
		return JoseKey.loaderJwtPublicKey();
	}
	
	/**
	 * Gera um Json Web Token com os dados Json recebido
	 * 
	 * @param usuario
	 * @return token
	 * @throws JoseException
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public String gerarJwt(String json)
			throws JoseException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		
		JwtClaims claims = new JwtClaims();
		claims.setIssuer(ISSUER);
		claims.setAudience(AUDIENCE);
		claims.setExpirationTimeMinutesInTheFuture(Ambiente.getPrazoValidadeTokenEmMinutos());	
		claims.setGeneratedJwtId();
		claims.setIssuedAtToNow();
		claims.setNotBeforeMinutesInThePast(2);
		claims.setSubject(SUBJECT);
		claims.setStringClaim("data", json);

		JsonWebSignature jws = new JsonWebSignature();
		jws.setPayload(claims.toJson());
		jws.setKey(privateKey);
		jws.setKeyIdHeaderValue(JoseConstants.JWK_ID);
		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

		return jws.getCompactSerialization();
	}

	/**
	 * Recupera as informações do usuário do Token recebido
	 * 
	 * @param token
	 * @return JSON value do usuário
	 * @throws IOException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidJwtException
	 */
	public String getJsonFromToken(String token)
			throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, InvalidJwtException {

		JwtConsumer jwtConsumer = new JwtConsumerBuilder()
				.setRequireExpirationTime()
				.setAllowedClockSkewInSeconds(30)
				.setRequireSubject()
				.setExpectedIssuer(ISSUER)
				.setExpectedAudience(AUDIENCE)
				.setVerificationKey(publicKey).build();

		JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

		return jwtClaims.getClaimValue("data").toString();
	}
}
