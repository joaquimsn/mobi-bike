package br.com.mobibike.autenticacao.domain.seguranca;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import br.com.mobibike.autenticacao.main.Ambiente;

public class BikeToken {
	
	private String token;
	private LocalDateTime expiracao;
	
	private BikeToken(String token) {
		this.token = token;
		LocalDateTime localDateTime = LocalDateTime.now();
		expiracao = localDateTime.plus(Ambiente.getPrazoValidadeTokenEmMinutos(), ChronoUnit.MINUTES);
	}
	
	public static BikeToken of(String jwt) {
		return new BikeToken(jwt);
	}
	
	public String getToken() {
		return token;
	}
	
	public LocalDateTime getExpiracao() {
		return expiracao;
	}
}
