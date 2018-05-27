package br.com.mobibike.autenticacao.domain.main.data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;

public abstract class GenericDAO<T> implements Serializable {
	private static final long serialVersionUID = -4843781544193464186L;

	private static Logger logger = LogManager.getLogger(GenericDAO.class);

	protected Class<T> classe;

	@PersistenceContext
	protected transient EntityManager entityManager;

	protected GenericDAO(final Class<T> classe) {
		this.classe = classe;
	}

	public T save(final T entity) {
		try {
			entityManager.persist(entity);
			entityManager.flush();
		} catch (Exception e) {
			logger.error("Falha ao persistir a entidade: " + entity.toString(), e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha na persistencia: " + " cause: " + extractCause(e.getCause(), ""));
		}

		return entity;
	}

	public T update(final T entity) {
		try {
			entityManager.merge(entity);
			entityManager.flush();
		} catch (Exception e) {
			logger.error("Falha ao atualizar a entidade: " + entity.toString(), e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha na atualização: " + e.getMessage() + " cause: " + extractCause(e.getCause(), ""));
		}

		return entity;
	}

	public void delete(final Serializable id) {
		try {
			T entityToBeRemoved = entityManager.getReference(classe, id);
			entityManager.remove(entityToBeRemoved);
			entityManager.flush();
		} catch (Exception e) {
			logger.error("Falha ao excluir a entidade " + classe.getName() + " com id: " + id, e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha na remoção: " + " cause: " + extractCause(e.getCause(), ""));
		}
	}

	public Optional<T> findById(final Serializable id) {
		try {
			return Optional.ofNullable(entityManager.find(classe, id));
		} catch (Exception e) {
			logger.error("Falha ao consultar a entidade " + classe.getName() + " com id: " + id, e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha consulta: " + " cause: " + extractCause(e.getCause(), ""));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		CriteriaQuery criteriaQuery = entityManager.getCriteriaBuilder().createQuery();
		criteriaQuery.select(criteriaQuery.from(classe));
		try {
			return entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			logger.error("Falha ao listar todos da entidade" + classe.getName(), e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha na consulta: " + " cause: " + extractCause(e.getCause(), ""));
		}
	}

	protected String extractCause(Throwable cause, String lastCause) {
		if (Objects.isNull(cause)) {
			return lastCause;
		}

		return extractCause(cause.getCause(), cause.toString());
	}
}