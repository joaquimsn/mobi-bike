package br.com.mobibike.coletor.domain.ciclista;

import br.com.mobibike.coletor.domain.ciclista.entity.ViagemTrack;
import br.com.mobibike.coletor.domain.main.data.GenericDAO;

public class ViagemTrackDAO extends GenericDAO<ViagemTrack> {

	private static final long serialVersionUID = 1624084718833433182L;

	protected ViagemTrackDAO() {
		super(ViagemTrack.class);
	}
}
