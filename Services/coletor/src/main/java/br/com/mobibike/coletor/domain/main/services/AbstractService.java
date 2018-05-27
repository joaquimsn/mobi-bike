package br.com.mobibike.coletor.domain.main.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.mobibike.coletor.domain.main.data.GenericDAO;
import br.com.mobibike.coletor.domain.main.enums.TypeStatus;
import br.com.mobibike.coletor.domain.main.persistence.DeletableEntity;
import br.com.mobibike.coletor.domain.main.persistence.InsertableEntity;
import br.com.mobibike.coletor.domain.main.persistence.TypeStatusAction;
import br.com.mobibike.coletor.domain.main.persistence.UpdatableEntity;
import br.com.mobibike.coletor.main.exceptions.enums.TypeApplicationException;
import br.com.mobibike.coletor.main.exceptions.enums.TypeExposeBusinessException;

/**
 * @author Joaquim Neto
 * Github joaquimsn
 */
public abstract class AbstractService<T, ID extends Serializable> implements Service<T, ID>, CrudService<T, ID>, Serializable {
	private static final long serialVersionUID = 5304557613527178315L;

	@Override
	public List<T> buscarTodos() {
		return getRepository().findAll();
	}

	@Override
	public T buscarPorId(final ID id) {
		Objects.requireNonNull(id);
		Optional<T> optional = getRepository().findById(id);

		if (!optional.isPresent()) {
			throw TypeExposeBusinessException.NAO_ENCONTRADO.buildException();
		}

		return optional.get();
	}

	@Override
	public void deletarFisicamente(final ID id) {
		getRepository().delete(id);
	}

	@Override
	public void inativar(final ID id) {
		T entity = buscarPorId(id);

		if (!Objects.equals(DeletableEntity.class, entity.getClass())) {
			throw TypeExposeBusinessException.REGRA_GERAL.buildException("Não foi possivel excluir",
					new Throwable("Para deletar é necessário implementar DeletableEntity"));
		}

		((DeletableEntity) entity).setStatus(TypeStatus.I);
		getRepository().save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T atualizar(T entity) {
		Objects.requireNonNull(entity);

		if (entity instanceof UpdatableEntity) {
			UpdatableEntity<ID> updatableEntity = (UpdatableEntity<ID>) entity;

			Objects.requireNonNull(updatableEntity.getId(), "Id da entidade é obrigatório");

			T objetoDB = buscarPorId(updatableEntity.getId());
			executeActionPreUpdate((UpdatableEntity<ID>) objetoDB, entity);

			return getRepository().update(objetoDB);
		}

		return getRepository().update(entity);
	}

	@Override
	public List<T> atualizar(final Iterable<T> entities) {
		List<T> result = new ArrayList<>();

		if (entities == null) {
			return result;
		}

		entities.forEach(entity -> result.add(atualizar(entity)));

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T inserir(final T entity) {
		Objects.requireNonNull(entity);

		if (entity instanceof InsertableEntity) {
			InsertableEntity<ID> insertableEntity =  (InsertableEntity<ID>) entity;

			if (Objects.nonNull(insertableEntity.getId())) {
				throw TypeApplicationException.PERSISTENCIA.buildException("Id deve ser preenchido automáticamente");
			}

			executeActionPrePersist(insertableEntity);
		}

		if (entity instanceof TypeStatusAction) {
			((TypeStatusAction) entity).setStatus(TypeStatus.A);
		}

		return getRepository().save(entity);
	}

	@Override
	public List<T> inserir(final List<T> entities) {
		List<T> result = new ArrayList<>();

		if (entities == null) {
			return result;
		}

		entities.forEach(entity -> result.add(inserir(entity)));

		return result;
	}

	/**
	 * Faz um toggle entre os status {@link TypeStatus#A} e {@link TypeStatus#I}
	 */
	@Override
	public T mudarStatus(final ID id) {
		T entity = getRepository().findById(id).get();

		if (!Objects.equals(TypeStatusAction.class, entity.getClass())) {
			throw TypeExposeBusinessException.REGRA_GERAL.buildException("Não foi possível alterar o status",
					new Throwable("Para mudar o status é necessário implementar TypeStatusAction"));
		}

		((TypeStatusAction) entity).inverterStatus();

		return this.atualizar(entity);
	}

	protected abstract GenericDAO<T> getRepository();
}
