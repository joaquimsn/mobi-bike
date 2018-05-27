package br.com.mobibike.coletor.domain.main.persistence;

import br.com.mobibike.coletor.domain.main.enums.TypeStatus;

public interface TypeStatusAction {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);

	public default void inverterStatus() {
		setStatus(TypeStatus.inverterStatus(getStatus()));
	}
}
