package br.com.mobibike.autenticacao.domain.perfil.entity;

import java.util.List;

import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;

public class Funcionalidade {

	private Long id;

	private String nome;

	private String descricao;

	private Aplicacao aplicacao;

	private List<Acesso> acessos;
}
