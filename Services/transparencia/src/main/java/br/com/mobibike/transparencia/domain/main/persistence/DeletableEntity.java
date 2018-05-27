package br.com.mobibike.transparencia.domain.main.persistence;

import br.com.mobibike.transparencia.domain.main.enums.TypeStatus;

public interface DeletableEntity {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);
}
