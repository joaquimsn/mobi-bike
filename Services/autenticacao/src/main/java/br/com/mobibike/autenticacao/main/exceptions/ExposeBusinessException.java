package br.com.mobibike.autenticacao.main.exceptions;

public class ExposeBusinessException extends ApplicationException {

	private static final long serialVersionUID = 8246610249702126170L;
	private int httpStatusCode;
	private String detail;

	protected ExposeBusinessException(String businessCode, String message, String detail, int httpStatusCode) {
		super(businessCode, message);
		this.httpStatusCode = httpStatusCode;
		this.detail = detail;
	}

	protected ExposeBusinessException(String businessCode, String message, String detail, int httpStatusCode,
			Throwable cause) {
		super(businessCode, message, cause);
		this.httpStatusCode = httpStatusCode;
		this.detail = detail;
	}

	public static ExposeBusinessException of(String businessCode, String message, int httpStatusCode) {
		return new ExposeBusinessException(businessCode, message, "", httpStatusCode);
	}

	public static ExposeBusinessException of(String businessCode, String message, String detail, int httpStatusCode) {
		return new ExposeBusinessException(businessCode, message, detail, httpStatusCode);
	}

	public static ExposeBusinessException of(String message, String businessCode, int httpStatusCode, Throwable cause) {
		return new ExposeBusinessException(businessCode, message, "", httpStatusCode, cause);
	}

	public static ExposeBusinessException of(String message, String businessCode, String detail, int httpStatusCode,
			Throwable cause) {
		return new ExposeBusinessException(businessCode, message, detail, httpStatusCode, cause);
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getDetail() {
		return detail;
	}
}
