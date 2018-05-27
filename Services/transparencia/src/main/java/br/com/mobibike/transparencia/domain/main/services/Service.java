package br.com.mobibike.transparencia.domain.main.services;

import java.io.Serializable;
import java.util.List;

public interface Service<T, ID extends Serializable> {

	List<T> buscarTodos();

	T buscarPorId(ID id);
}
