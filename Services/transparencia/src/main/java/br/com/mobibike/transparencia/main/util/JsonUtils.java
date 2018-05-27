package br.com.mobibike.transparencia.main.util;

import java.io.IOException;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.mobibike.transparencia.main.exceptions.enums.TypeApplicationException;

public class JsonUtils {
	private static Logger logger = LogManager.getLogger(HashUtils.class);
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public JsonUtils() {
		throw new IllegalStateException("util class");
	}

	/**
	 * Faz a deserialização do json passdo por parâmetro para um objeto do type
	 * fornecido, a estrutura do Json deve corresponder ao objeto
	 * 
	 * @author Joaquim Neto
	 * @param json
	 *            String
	 * @param type
	 *            <T>
	 * @return Object<T>
	 *
	 *         Created 17 de mai de 2016 - 10:03:53
	 */
	public static <T> T jsonToObject(String json, Class<T> type) {
		Objects.requireNonNull(json, "Imposivel fazer uma serialização de null para Object");

		T retorno;
		try {
			retorno = objectMapper.readValue(json, type);
		} catch (IOException e) {
			logger.error(TypeApplicationException.JSON_CONVERTER_OBJECT.getMensagem(), e);

			throw TypeApplicationException.JSON_CONVERTER_OBJECT.buildException();
		}

		return retorno;
	}

	/**
	 * Faz a serialização apenas dos Getters do Objeto passado para JSON
	 * 
	 * @author "Joaquim Neto"
	 * @param object
	 * @return String
	 *
	 *         Created 17 de mai de 2016 - 10:02:24
	 */
	public static String objectToJson(Object object, Class<?> type) {
		String json = "";

		try {
			json = objectMapper.writerFor(type).writeValueAsString(object);
		} catch (JsonProcessingException e) {
			logger.error(TypeApplicationException.OBJECT_CONVERTER_JSON.getMensagem(), e);

			throw TypeApplicationException.OBJECT_CONVERTER_JSON.buildException();
		}

		return json;
	}
}