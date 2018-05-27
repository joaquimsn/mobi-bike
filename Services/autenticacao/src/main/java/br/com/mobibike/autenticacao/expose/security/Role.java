package br.com.mobibike.autenticacao.expose.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.mobibike.autenticacao.expose.security.enums.Access;

public class Role {
	private String code;
	private String name;
	private List<Access> accesses;
	
	public boolean containsAny(Access[] roleAccess) {
		for (Access access : roleAccess) {
			if (getAccesses().contains(access)) {
				return true;
			}
		}
		
		return false;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public List<Access> getAccesses() {
		return Optional.ofNullable(this.accesses).orElse(new ArrayList<Access>());
	}

	@Override
	public String toString() {
		return "Role [code=" + code + ", name=" + name + ", access=" + accesses + "]";
	}
}
