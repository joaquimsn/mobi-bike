package br.com.mobibike.autenticacao.expose.security.enums;

public enum Role {
	DEFAULT("DEFAULT"),
	GESTOR("GESTOR"),
	PARCEIRO("PARCEIRO"),
	AUDITOR("AUDITOR");
	
	private String code;

	private Role(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
}
