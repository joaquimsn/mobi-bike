package br.com.mobibike.autenticacao.domain.perfil.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;
import br.com.mobibike.autenticacao.domain.seguranca.entity.Aplicacao;

//@Entity
//@Table(name = "Perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = -2185143628440022191L;

	@Id
	@Column(name = "ID_PERFIL")
	@SequenceGenerator(name = "seqPerfil", sequenceName = "SEQ_PERFIL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPerfil")
	private Long id;

	@NotNull
	@Size(min = 3, max = 64, message = "O campo nome deve conter entre {min} e {max} caracteres")
	private String nome;

	@Transient
	private Aplicacao aplicacao;

	@Transient
	private List<Funcionalidade> funcionalidades;

	@Column(name = "DT_CADASTRO", updatable = false)
	private LocalDateTime dataCadastro;

	@Column(name = "DT_ALTERACAO", insertable = false)
	private LocalDateTime dataAlteracao;

	@Column(name = "FL_STATUS", columnDefinition = "char")
	@Enumerated(EnumType.STRING)
	private TypeStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public TypeStatus getStatus() {
		return status;
	}

	public void setStatus(TypeStatus status) {
		this.status = status;
	}

}
