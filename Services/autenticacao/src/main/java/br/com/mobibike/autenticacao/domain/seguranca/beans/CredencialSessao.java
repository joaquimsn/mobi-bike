package br.com.mobibike.autenticacao.domain.seguranca.beans;

import java.util.Objects;

import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;

public class CredencialSessao {
	private Long id;
	private String usuario;
	private String aplicacao;
	private String perfil;

	public CredencialSessao() {
	}

	private CredencialSessao(Long id, String usuario, String aplicacao, String perfil) {
		this.id = id;
		this.usuario = usuario;
		this.aplicacao = aplicacao;
		this.perfil = perfil;
	}

	public static CredencialSessao of(Credencial credencial) {
		Objects.requireNonNull(credencial, "A credencial não pode ser null");
		return new CredencialSessao(credencial.getId(), credencial.getUsuario(), credencial.getAplicacao().getNome(), null);
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public String getPerfil() {
		return perfil;
	}

}
