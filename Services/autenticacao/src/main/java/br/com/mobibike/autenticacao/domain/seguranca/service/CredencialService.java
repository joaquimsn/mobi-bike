package br.com.mobibike.autenticacao.domain.seguranca.service;

import java.io.Serializable;
import java.util.Objects;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mobibike.autenticacao.domain.main.data.GenericDAO;
import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;
import br.com.mobibike.autenticacao.domain.main.services.AbstractService;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialAcesso;
import br.com.mobibike.autenticacao.domain.seguranca.data.CredencialDAO;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;

@Stateless
public class CredencialService extends AbstractService<Credencial, Long> implements Serializable {
	private static final long serialVersionUID = 7334537626480530184L;

	@Inject
	private CredencialDAO credencialDAO;

	@Override
	protected GenericDAO<Credencial> getRepository() {
		return credencialDAO;
	}

	public Credencial buscarPor(CredencialAcesso credencialAcesso, Aplicacao aplicacao) {
		Objects.requireNonNull(credencialAcesso, "É obrigatório informar uma credencial");
		Objects.requireNonNull(aplicacao, "É obrigatório informar a aplicação");

		return credencialDAO.buscarPor(credencialAcesso, aplicacao, TypeStatus.A);
	}
}
