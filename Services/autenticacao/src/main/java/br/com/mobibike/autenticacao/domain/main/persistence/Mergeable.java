package br.com.mobibike.autenticacao.domain.main.persistence;

public interface Mergeable {

	<T> T merge(T revision);
}
