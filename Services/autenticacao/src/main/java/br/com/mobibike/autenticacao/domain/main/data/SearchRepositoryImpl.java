package br.com.mobibike.autenticacao.domain.main.data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.github.joaquimsn.querysearch.data.SearchRepository;

/**
 * @author Joaquim Neto
 * Github joaquimsn
 */
public class SearchRepositoryImpl implements SearchRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Query getQueryJpql(String jpql) {
		return entityManager.createQuery(jpql);
	}

	@Override
	public Query getQueryNativeQuery(String sqlQuery) {
		return entityManager.createNativeQuery(sqlQuery);
	}

}
