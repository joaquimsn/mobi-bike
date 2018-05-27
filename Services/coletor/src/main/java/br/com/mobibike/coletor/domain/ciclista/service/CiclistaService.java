package br.com.mobibike.coletor.domain.ciclista.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mobibike.coletor.domain.ciclista.CiclistaDAO;
import br.com.mobibike.coletor.domain.ciclista.entity.Ciclista;
import br.com.mobibike.coletor.domain.main.data.GenericDAO;
import br.com.mobibike.coletor.domain.main.services.AbstractService;

@Stateless
public class CiclistaService extends AbstractService<Ciclista, Long> {
	private static final long serialVersionUID = 7959766241687361830L;

	@Inject
	private CiclistaDAO ciclistaDAO;

	@Override
	protected GenericDAO<Ciclista> getRepository() {
		return ciclistaDAO;
	}

}
