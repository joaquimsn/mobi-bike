package br.com.mobibike.autenticacao.main.configuration;

import br.com.mobibike.autenticacao.main.Ambiente;
import br.com.mobibike.autenticacao.main.BikeUriConventions;
import io.swagger.jaxrs.config.BeanConfig;

/**
 * @author Joaquim Neto
 * Github joaquimsn
 */
public class SwaggerConfig {

	public SwaggerConfig() {
		inicializar();
	}

	public void inicializar() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setTitle("Bike API - Autenticacao");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost(Ambiente.getPublicHost());
		beanConfig.setBasePath("/autenticacao" + BikeUriConventions.BASE_URL_API);
		beanConfig.setResourcePackage("br.com.mobibike.autenticacao.expose.resources");
		beanConfig.setScan(true);
	}
}
