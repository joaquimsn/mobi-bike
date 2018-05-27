package br.com.mobibike.autenticacao.domain.seguranca.data;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.autenticacao.domain.main.data.GenericDAO;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;

public class AplicacaoDAO extends GenericDAO<Aplicacao> {
	private static final long serialVersionUID = -9167665652550618902L;
	private static Logger logger = LogManager.getLogger(AplicacaoDAO.class);

	private AplicacaoDAO() {
		super(Aplicacao.class);
	}

	public Optional<Aplicacao> buscarPorNome(String aplicacao) {
		Objects.requireNonNull(aplicacao, "O nome da aplicação não pode ser null");

		String jpql = "SELECT a FROM  Aplicacao a WHERE a.nome LIKE :nomeAplicacao";

		TypedQuery<Aplicacao> typedQuery = entityManager.createQuery(jpql, Aplicacao.class);
		typedQuery.setParameter("nomeAplicacao", aplicacao);

		try {
			return Optional.of(typedQuery.getSingleResult());
		} catch (NoResultException noResultException) {
			return Optional.ofNullable(null);
		} catch (Exception e) {
			logger.error(e);
			throw TypeApplicationException.PERSISTENCIA.buildException("Ocorreu uma falha na persistencia: " + " cause: " + extractCause(e.getCause(), ""));
		}
	}

}
