package br.com.mobibike.coletor.main.handlers;

import java.util.Optional;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.mobibike.coletor.main.exceptions.BikeHttpStatusCode;
import br.com.mobibike.coletor.main.exceptions.ExposeBusinessException;
import br.com.mobibike.coletor.main.exceptions.MessageResponseError;
import br.com.mobibike.coletor.main.exceptions.SystemException;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
	private static Logger logger = LogManager.getLogger(GenericExceptionMapper.class);

	@Override
	public Response toResponse(Exception exception) {
		logger.warn(exception.getMessage(), exception.getCause());

		Exception exceptionToComapre = verifyCauseFromApplicationException(exception) ? (Exception) exception.getCause() : exception;
		if (exceptionToComapre instanceof WebApplicationException) {
			WebApplicationException webException = (WebApplicationException) exceptionToComapre;
			return Response.status(webException.getResponse().getStatus()
					).type(MediaType.APPLICATION_JSON)
					.entity(MessageResponseError.of("api.error", webException.getResponse().getStatus(), webException.getMessage())).build();
		}
		if (exceptionToComapre instanceof ExposeBusinessException) {
			ExposeBusinessException exposeBusinessException = (ExposeBusinessException) exceptionToComapre;
			return Response.status(exposeBusinessException.getHttpStatusCode()
					).type(MediaType.APPLICATION_JSON)
					.entity(MessageResponseError.of(exposeBusinessException.getSystemCode(),
							exposeBusinessException.getHttpStatusCode(), exposeBusinessException.getMessage()))
					.build();
		}
		if (exceptionToComapre instanceof JsonParseException) {
			return Response.status(BikeHttpStatusCode.CONTRATO_VIOLADO).entity(MessageResponseError.of("api.parse.error", BikeHttpStatusCode.CONTRATO_VIOLADO, exceptionToComapre.getMessage()))
					.build();
		} else {
			logger.error(exception.getMessage(), exception.getCause());
			return Response.status(BikeHttpStatusCode.ERRO_INTERNO_SERVIDOR)
					.entity(MessageResponseError.of("erro.inesperado", BikeHttpStatusCode.ERRO_INTERNO_SERVIDOR, "Algo está errado", exceptionToComapre.getMessage())).build();
		}
	}

	private boolean verifyCauseFromApplicationException(Exception exception) {
		if (Optional.ofNullable(exception.getCause()).isPresent()) {
			return exception.getCause() instanceof SystemException;
		}

		return false;
	}
}
