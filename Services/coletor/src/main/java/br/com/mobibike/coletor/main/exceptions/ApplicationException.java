package br.com.mobibike.coletor.main.exceptions;

public class ApplicationException extends RuntimeException implements SystemException {

	private static final long serialVersionUID = -1731111869397030499L;

	private String systemCode;

	protected ApplicationException(String systemCode, String message) {
		super(message);
		this.systemCode = systemCode;
	}

	protected ApplicationException(String systemCode, String message, Throwable cause) {
		super(message, cause);
		this.systemCode = systemCode;
	}

	/**
	 * Cria uma instância ApplicationException
	 * 
	 * @param applicatonCode
	 *            codigo unico para identificação da falha
	 * @param message
	 *            detalhamento da falha
	 * @return Object ApplicationException
	 */
	public static ApplicationException of(String applicatonCode, String message) {
		return new ApplicationException(applicatonCode, message);
	}

	/**
	 * Cria uma instância ApplicationException
	 * 
	 * @param applicatonCode
	 *            codigo unico para identificação da falha
	 * @param message
	 *            detalhamento da falha
	 * @param cause
	 *            throwable de origem
	 * @return Object ApplicationException
	 */
	public static ApplicationException of(String applicatonCode, String message, Throwable cause) {
		return new ApplicationException(applicatonCode, message, cause);
	}
	
	@Override
	public String getSystemCode() {
		return systemCode;
	}

}
