package br.com.mobibike.coletor.main.exceptions;

public class FatalErroStartupApplicationException extends ApplicationException {
	private static final long serialVersionUID = -3894758591828162730L;

	protected FatalErroStartupApplicationException(String applicationCode, String message) {
		super(applicationCode, message);
	}
}
