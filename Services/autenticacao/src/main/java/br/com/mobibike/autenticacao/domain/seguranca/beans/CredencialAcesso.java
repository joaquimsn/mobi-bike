package br.com.mobibike.autenticacao.domain.seguranca.beans;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mobibike.autenticacao.main.util.HashUtils;

public class CredencialAcesso {

	@NotNull
	@Size(min = 3, max = 128)
	private String usuario;
	@Size(min = 6, max = 32)
	private String senha;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = HashUtils.gerarHashMD5(senha);
	}

	@Override
	public String toString() {
		return "CredencialAcesso [usuario=" + usuario + "]";
	}
}
