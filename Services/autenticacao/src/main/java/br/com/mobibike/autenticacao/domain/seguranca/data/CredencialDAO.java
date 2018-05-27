package br.com.mobibike.autenticacao.domain.seguranca.data;

import java.util.Objects;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.mobibike.autenticacao.domain.main.data.GenericDAO;
import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;
import br.com.mobibike.autenticacao.domain.seguranca.beans.CredencialAcesso;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Credencial;
import br.com.mobibike.autenticacao.main.exceptions.enums.TypeApplicationException;

public class CredencialDAO extends GenericDAO<Credencial> {
	private static final long serialVersionUID = 3027413416098447716L;
	private static Logger logger = LogManager.getLogger(CredencialDAO.class);

	public CredencialDAO() {
		super(Credencial.class);
	}

	public Credencial buscarPor(CredencialAcesso credencialAcesso, Aplicacao aplicacao, TypeStatus status) {
		Objects.requireNonNull(credencialAcesso, "É obrigatório informar a credencial de acesso");
		String jpql = "SELECT c FROM Credencial c WHERE c.usuario = :usuario AND c.senha = :senha AND c.aplicacao.id = :idAplicacao AND c.status = :status";

		TypedQuery<Credencial> typedQuery = entityManager.createQuery(jpql, Credencial.class);
		typedQuery.setParameter("usuario", credencialAcesso.getUsuario().toLowerCase());
		typedQuery.setParameter("senha", credencialAcesso.getSenha());
		typedQuery.setParameter("idAplicacao", aplicacao.getId());
		typedQuery.setParameter("status", status);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (NonUniqueResultException e) {
			throw TypeApplicationException.FATAL.buildException("Encontrado mais de uma resultado para a credencial de acesso: " + credencialAcesso + aplicacao);
		} catch (Exception e) {
			logger.error("Erro ao consultar a credencial", e);
			throw TypeApplicationException.FATAL.buildException("Erro inesperado ao cunsultar a credencial: " + credencialAcesso + aplicacao);
		}
	}

}
