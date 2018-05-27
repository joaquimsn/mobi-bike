package br.com.mobibike.transparencia.main.configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.com.mobibike.transparencia.main.BikeUriConventions;

@ApplicationPath(BikeUriConventions.BASE_URL_API)
public class ApiConfig extends Application {
	
	@PostConstruct
	private void init() {
		new SwaggerConfig();
	}
}
