package br.com.mobibike.autenticacao.domain.main.persistence;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface InsertableEntity <ID extends Serializable> {

	ID getId();

	LocalDateTime getDataCadastro();
	
	void setDataCadastro(LocalDateTime dataCadastro);

	default void actionPreInsert() {
		setDataCadastro(LocalDateTime.now());
	}

}
