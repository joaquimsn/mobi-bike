package br.com.mobibike.coletor.domain.ciclista;

import br.com.mobibike.coletor.domain.ciclista.entity.Ciclista;
import br.com.mobibike.coletor.domain.main.data.GenericDAO;

public class CiclistaDAO extends GenericDAO<Ciclista> {

	private static final long serialVersionUID = -1370449805270443799L;

	protected CiclistaDAO() {
		super(Ciclista.class);
	}

}
