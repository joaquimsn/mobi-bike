package br.com.mobibike.coletor.domain.main.persistence;

import br.com.mobibike.coletor.domain.main.enums.TypeStatus;

public interface DeletableEntity {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);
}
