package br.com.mobibike.autenticacao.domain.seguranca;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;

import br.com.mobibike.autenticacao.main.Ambiente;
import br.com.mobibike.autenticacao.main.util.FileUtils;

/**
 * Classe responsável pela geração de chave assimetrica
 * @author Joaquim Neto
 */
public class JoseKey {

	private JoseKey() {
		throw new IllegalStateException("Utility class");
	}

	private static Logger logger = LogManager.getLogger(JoseKey.class);
	public static final String DIRETORIO_CHAVES_ASSIMETRICAS = Ambiente.getDiretorioConfiguracoes() + File.separator + "chaves";
	
	/**
	 * Gera a chave assimetrica utilizando o diretório padrão da aplicação
	 * @throws JoseException
	 * @throws IOException
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:17:27
	 */
	public static void gerarChavesAssimetricasJwt() throws JoseException, IOException {
		gerarChavesAssimetricasJwt(DIRETORIO_CHAVES_ASSIMETRICAS);
	}
	
	/**
	 * Gera as chaves assimetricas salvando no diretório passado por parâmetro
	 * @param diretorio de destino para as chaves geradas
	 * @throws IOException
	 * @throws JoseException
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:18:32
	 */
	public static void gerarChavesAssimetricasJwt(String diretorio) throws IOException, JoseException {
		RsaJsonWebKey rsaJsonWebKey = RsaJwkGenerator.generateJwk(2048);
		rsaJsonWebKey.setKeyId(JoseConstants.JWK_ID);
		
		gerarPrivateKey(rsaJsonWebKey, diretorio);
		gerarPublicKey(rsaJsonWebKey, diretorio);
		
		logger.info("Gerado no conjunto de chaves assimetricas");
	}
	
	private static void gerarPrivateKey(RsaJsonWebKey rsaJsonWebKey, String diretorio) throws IOException {
		logger.debug("Iniciado metodo gerarPrivateKey(RsaJsonWebKey rsaJsonWebKey)");
		
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaJsonWebKey.getPrivateKey().getEncoded());
		FileOutputStream fosPrivate = new FileOutputStream(diretorio + File.separator + JoseConstants.PRIVATE_KEY);
		fosPrivate.write(pkcs8EncodedKeySpec.getEncoded());
		fosPrivate.close();		
		
		logger.debug("Gerada privateKey");
	}
	
	private static void gerarPublicKey(RsaJsonWebKey rsaJsonWebKey, String diretorio) throws IOException {
		logger.debug("Iniciado metodo gerarPublicKey(RsaJsonWebKey rsaJsonWebKey)");
				
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaJsonWebKey.getKey().getEncoded());
		FileOutputStream fileOutputStream = new FileOutputStream(diretorio + File.separator + JoseConstants.PUBLIC_KEY);
		fileOutputStream.write(x509EncodedKeySpec.getEncoded());
		fileOutputStream.close();
		
		logger.debug("Gerada privateKey");
	}
	
	/**
	 * Cria uma instância do objeto PrivateKey, a partir
	 * da chave privada armazenda no diretório padrão da aplicação 
	 * @return PrivatyKey
	 * @throws Exception
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:35:19
	 */
	public static PrivateKey loaderJwtPrivateKey() throws Exception{
		return loaderJwtPrivateKey(DIRETORIO_CHAVES_ASSIMETRICAS + File.separator + JoseConstants.PRIVATE_KEY);
	}
	
	/**
	 * Cria uma instância do objeto PrivateKey, a partir
	 * do caminho passado por parâmetro
	 * @param fileName caminho absoluto da chave 
	 * @return PrivatyKey
	 * @throws Exception
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:35:19
	 */
	public static PrivateKey loaderJwtPrivateKey(String fileName) throws Exception{
		KeyFactory keyFactory = KeyFactory.getInstance(JoseConstants.KEY_ALGORITHM);
		PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(FileUtils.readFile(fileName));	

		return keyFactory.generatePrivate(privateKeySpec);
	}
	
	/**
	 * Cria uma instância do objeto <b>PublicKey</b>, a partir
	 * da chave publica armazenda no diretório padrão da aplicação 
	 * @return PrivatyKey
	 * @throws Exception
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:35:19
	 */
	public static PublicKey loaderJwtPublicKey() throws Exception {
		return loaderJwtPublicKey(DIRETORIO_CHAVES_ASSIMETRICAS + File.separator + JoseConstants.PUBLIC_KEY);
	}
	
	/**
	 *  Cria uma instância do objeto <b>PublicKey</b>, a partir
	 * da chave publica armazenda no diretório padrão da aplicação 
	 * @param fileName caminho absoluto da chave
	 * @return PublicKey
	 * @throws Exception
	 *
	 * @author Joaquim Neto
	 * Created 19 de mai de 2016 - 17:43:55
	 */
	public static PublicKey loaderJwtPublicKey(String fileName) throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance(JoseConstants.KEY_ALGORITHM);
		X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(FileUtils.readFile(fileName));
		
		return keyFactory.generatePublic(publicKeySpec);
	}
}
