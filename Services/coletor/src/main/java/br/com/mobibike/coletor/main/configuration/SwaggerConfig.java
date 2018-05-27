package br.com.mobibike.coletor.main.configuration;

import br.com.mobibike.coletor.main.Ambiente;
import br.com.mobibike.coletor.main.BikeUriConventions;
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
		beanConfig.setTitle("Mobi Bike API - Coletor");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost(Ambiente.getPublicHost());
		beanConfig.setBasePath("/coletor" + BikeUriConventions.BASE_URL_API);
		beanConfig.setResourcePackage("br.com.mobibike.coletor.expose.resources");
		beanConfig.setScan(true);
	}
}
