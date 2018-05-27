package br.com.mobibike.transparencia.main.configuration;

import br.com.mobibike.transparencia.main.Ambiente;
import br.com.mobibike.transparencia.main.BikeUriConventions;
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
		beanConfig.setTitle("Mobi Bike API - Transparência");
		beanConfig.setVersion("1.0.0");
		beanConfig.setHost(Ambiente.getPublicHost());
		beanConfig.setBasePath("/transparencia" + BikeUriConventions.BASE_URL_API);
		beanConfig.setResourcePackage("br.com.mobibike.transparencia.expose.resources");
		beanConfig.setScan(true);
	}
}
