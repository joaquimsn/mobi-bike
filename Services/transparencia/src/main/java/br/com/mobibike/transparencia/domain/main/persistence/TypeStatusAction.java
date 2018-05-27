package br.com.mobibike.transparencia.domain.main.persistence;

import br.com.mobibike.transparencia.domain.main.enums.TypeStatus;

public interface TypeStatusAction {

	public TypeStatus getStatus();

	public void setStatus(TypeStatus status);

	public default void inverterStatus() {
		setStatus(TypeStatus.inverterStatus(getStatus()));
	}
}
