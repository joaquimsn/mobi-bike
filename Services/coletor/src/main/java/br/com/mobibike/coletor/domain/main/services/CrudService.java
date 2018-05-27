package br.com.mobibike.coletor.domain.main.services;

import java.io.Serializable;
import java.util.List;

import br.com.mobibike.coletor.domain.main.persistence.InsertableEntity;
import br.com.mobibike.coletor.domain.main.persistence.UpdatableEntity;

public interface CrudService<T, ID extends Serializable> {

	void deletarFisicamente(ID id);

	T atualizar(T entity);

	List<T> atualizar(Iterable<T> entities);

	T inserir(T entity);

	List<T> inserir(List<T> entities);

	void inativar(ID id);

	T mudarStatus(ID id);

	default void executeActionPrePersist(final InsertableEntity<?> insertableEntity) {
		insertableEntity.actionPreInsert();
	}

	@SuppressWarnings("unchecked")
	default T executeActionPreUpdate(final UpdatableEntity<ID> updatableEntity, final T entityChange) {
		T entityMergeble = updatableEntity.merge(entityChange);
		((UpdatableEntity<ID>) entityMergeble).actionPreUpdate();
		
		return entityMergeble;
	}
}
