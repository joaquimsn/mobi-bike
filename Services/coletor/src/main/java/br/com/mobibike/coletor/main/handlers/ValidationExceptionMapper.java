package br.com.mobibike.coletor.main.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.coletor.main.exceptions.BikeHttpStatusCode;
import br.com.mobibike.coletor.main.exceptions.MessageResponseError;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{
	private static Logger logger = LogManager.getLogger(ValidationExceptionMapper.class);

	@Override
	public Response toResponse(ConstraintViolationException constraintViolationExceptionException) {
		logger.debug("Resource enviado não atende as normas", constraintViolationExceptionException);
		return Response.status(BikeHttpStatusCode.CONTRATO_VIOLADO).type(MediaType.APPLICATION_JSON)
				.entity(buildResponseMessage(constraintViolationExceptionException)).build();
	}

	private List<MessageResponseError> buildResponseMessage(ConstraintViolationException constraintViolationException) {
		List<MessageResponseError> list = new ArrayList<>();
		for (ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
			MessageResponseError messageResponseError = MessageResponseError.of(
					"api.validation." + violation.getPropertyPath(), BikeHttpStatusCode.CONTRATO_VIOLADO,
					violation.getMessage());
			list.add(messageResponseError);
		}
		return list;
	}
}
