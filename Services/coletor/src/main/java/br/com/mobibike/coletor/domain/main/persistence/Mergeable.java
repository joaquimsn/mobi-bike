package br.com.mobibike.coletor.domain.main.persistence;

public interface Mergeable {

	<T> T merge(T revision);
}
