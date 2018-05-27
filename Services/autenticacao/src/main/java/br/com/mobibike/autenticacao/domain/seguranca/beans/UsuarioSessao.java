package br.com.mobibike.autenticacao.domain.seguranca.beans;

import java.time.LocalDateTime;

import br.com.mobibike.autenticacao.domain.seguranca.BikeToken;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;

public class UsuarioSessao {

	private Long id;
	private String aplicacao;
	private String token;
	private LocalDateTime expiracao;

	private UsuarioSessao(Long id, String aplicacao, String token, LocalDateTime expiracao) {
		this.id = id;
		this.aplicacao = aplicacao;
		this.token = token;
		this.expiracao = expiracao;
	}

	public static UsuarioSessao of(Credencial credencial, BikeToken bikeToken) {
		return new UsuarioSessao(credencial.getId(), credencial.getAplicacao().getNome(), bikeToken.getToken(), bikeToken.getExpiracao());
	}

	public Long getId() {
		return id;
	}

	public String getAplicacao() {
		return aplicacao;
	}

	public String getToken() {
		return token;
	}

	public LocalDateTime getExpiracao() {
		return expiracao;
	}

}
