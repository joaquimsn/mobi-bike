package br.com.mobibike.coletor.main.handlers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.coletor.main.exceptions.ApplicationException;
import br.com.mobibike.coletor.main.exceptions.MessageResponseError;

@Provider
public class ApplicationExceptionMapper implements ExceptionMapper<ApplicationException>{
	private static Logger logger = LogManager.getLogger(ApplicationExceptionMapper.class);
	
	@Override
	public Response toResponse(ApplicationException exception) {
		logger.error(exception.getSystemCode() + ": " + exception.getMessage(), exception.getCause());
		
		MessageResponseError message = MessageResponseError.of(exception.getSystemCode(), Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro inesperado, tente novamente");
		return Response.status(Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
}
