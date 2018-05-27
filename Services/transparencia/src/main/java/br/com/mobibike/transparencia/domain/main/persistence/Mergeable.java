package br.com.mobibike.transparencia.domain.main.persistence;

public interface Mergeable {

	<T> T merge(T revision);
}
