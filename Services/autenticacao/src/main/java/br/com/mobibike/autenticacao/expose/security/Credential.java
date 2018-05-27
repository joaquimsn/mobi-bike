package br.com.mobibike.autenticacao.expose.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.mobibike.autenticacao.expose.security.enums.Access;

public class Credential {
	private String code;
	private String user;
	private String application;
	private List<Role> roles;

	public Role contains(br.com.mobibike.autenticacao.expose.security.enums.Role role, Access[] accesses) {
		List<Role> rolesFound = getRoles().stream().filter(credentialRole -> 
			credentialRole.getCode().equals(role.getCode()) && credentialRole.containsAny(accesses)
		).collect(Collectors.toList());
		
		return rolesFound.isEmpty() ? null : rolesFound.get(0);
	}
	
	public String getCode() {
		return code;
	}

	public String getUser() {
		return user;
	}

	public String getApplication() {
		return application;
	}

	public List<Role> getRoles() {
		return Optional.ofNullable(this.roles).orElse(new ArrayList<>());
	}
}
