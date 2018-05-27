package br.com.mobibike.autenticacao.domain.seguranca.service;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mobibike.autenticacao.domain.main.data.GenericDAO;
import br.com.mobibike.autenticacao.domain.main.services.AbstractService;
import br.com.mobibike.autenticacao.domain.seguranca.data.AplicacaoDAO;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;

@Stateless
public class AplicacaoService extends AbstractService<Aplicacao, Long> {
	private static final long serialVersionUID = -3683928193423471149L;

	@Inject
	private AplicacaoDAO aplicacaoDAO;

	public Optional<Aplicacao> buscarPorNome(String aplicacao) {
		return aplicacaoDAO.buscarPorNome(aplicacao);
	}

	@Override
	protected GenericDAO<Aplicacao> getRepository() {
		return aplicacaoDAO;
	}


}
