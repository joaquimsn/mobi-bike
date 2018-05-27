package br.com.mobibike.coletor.domain.ciclista.service;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.mobibike.coletor.domain.ciclista.ViagemTrackDAO;
import br.com.mobibike.coletor.domain.ciclista.entity.ViagemTrack;
import br.com.mobibike.coletor.domain.main.data.GenericDAO;
import br.com.mobibike.coletor.domain.main.services.AbstractService;

@Stateless
public class ViagemTrackService extends AbstractService<ViagemTrack, Long> {
	private static final long serialVersionUID = -5523801355126710425L;

	@Inject
	private ViagemTrackDAO viagemTrackDAO;

	@Override
	protected GenericDAO<ViagemTrack> getRepository() {
		return viagemTrackDAO;
	}

}
