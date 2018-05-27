package br.com.mobibike.coletor.domain.farejador;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.JsonNode;

import br.com.mobibike.coletor.main.exceptions.enums.TypeExposeBusinessException;

public class FarejadorService {

	private static JsonNode request(String url) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(url).request().get();

		if (response.getStatus() == 200) {
			return response.readEntity(JsonNode.class);
		}

		throw TypeExposeBusinessException.REGRA_GERAL.buildException();
	}

	public void consultarRegiao(String coordenadas) {
		JsonNode response = request(
				"https://maps.googleapis.com/maps/api/geocode/json?latlng=-23.508225799999998,-46.6514129&key=AIzaSyAY-8g83T8FfdWLmRVNoMCbdbulIGO3v8w");
	}
}
