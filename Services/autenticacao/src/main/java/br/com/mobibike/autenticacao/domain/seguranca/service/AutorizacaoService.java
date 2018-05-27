package br.com.mobibike.autenticacao.domain.seguranca.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialSessao;

@Singleton
public class AutorizacaoService {

	@Inject
	private CredencialService credencialService;

	public void autorizar(CredencialSessao credencialSessao, String aplicacao) {

	}

}
