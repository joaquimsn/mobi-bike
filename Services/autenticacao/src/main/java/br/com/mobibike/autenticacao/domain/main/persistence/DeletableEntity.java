package br.com.mobibike.autenticacao.domain.main.persistence;

import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;

public interface DeletableEntity {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);
}
