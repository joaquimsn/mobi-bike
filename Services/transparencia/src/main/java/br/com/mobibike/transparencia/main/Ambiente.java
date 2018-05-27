package br.com.mobibike.transparencia.main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.transparencia.main.exceptions.enums.TypeApplicationException;

public abstract class Ambiente {
	private static Logger logger = LogManager.getLogger(Ambiente.class);

	private static final String ENV_VARIABLE_NAME = "ENV";
	private static final String ENV_PROD = "envProd.properties";
	private static final String ENV_DESENV = "envDesenv.properties";
	private static final String ENV_HOMOL = "envHomol.properties";
	private static Properties applicationProperties;

	private Ambiente() {
		throw new IllegalStateException("Utility class");
	}

	private static String getVariavelAmbiente() {
		String ambiente = System.getProperty(ENV_VARIABLE_NAME);
		logger.info("Aplicação rodando no ambiente: " + ambiente);
		return ambiente;
	}

	private static Properties getPropertisConfiguracao() {
		if (Objects.nonNull(applicationProperties)) {
			return applicationProperties;
		}
		String env = getVariavelAmbiente() == null ? " " : getVariavelAmbiente();
		switch (env) {
		case "desenv":
			applicationProperties = getCustomProperties(ENV_DESENV);
			return applicationProperties;
		case "homol":
			applicationProperties = getCustomProperties(ENV_HOMOL);
			return applicationProperties;
		case "prod":
			applicationProperties = getCustomProperties(ENV_PROD);
			return applicationProperties;
		default:
			throw TypeApplicationException.STARTUP_ERROR.buildException("Variavel de ambiente " + env + " não definida/suportada");
		}
	}

	public static boolean isEnvironmentProduction() {
		return getVariavelAmbiente().equals("prod");
	}

	private static Properties lerLocalProperties(String env) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = Ambiente.class.getClassLoader().getResourceAsStream(env);
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error("Falha ao ler o properties de configuracao", e);
		}
		return properties;
	}

	private static Properties getCustomProperties(String env) {
		Properties properties = lerLocalProperties(env);
		properties.setProperty("DIRETORIO_CONFIGURACOES", File.listRoots()[0].getAbsolutePath() + File.separator + "data" + File.separator + "configuracoes");

		return properties;
	}

	public static String getDiretorioConfiguracoes() {
		return getPropertisConfiguracao().getProperty("DIRETORIO_CONFIGURACOES");
	}

	public static String getPublicHost() {
		return getPropertisConfiguracao().getProperty("PUBLIC_HOST");
	}

	public static String getTimeZone() {
		return getPropertisConfiguracao().getProperty("TIME_ZONE");
	}

	public static Integer getPrazoValidadeTokenEmMinutos() {
		return Integer.valueOf(getPropertisConfiguracao().getProperty("PRAZO_VALIDADE_TOKEN_EM_MINUTOS"));
	}
}

