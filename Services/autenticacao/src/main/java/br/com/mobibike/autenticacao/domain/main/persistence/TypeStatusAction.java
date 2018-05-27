package br.com.mobibike.autenticacao.domain.main.persistence;

import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;

public interface TypeStatusAction {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);

	public default void inverterStatus() {
		setStatus(TypeStatus.inverterStatus(getStatus()));
	}
}
