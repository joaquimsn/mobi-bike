package br.com.mobibike.autenticacao.domain.seguranca.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.mobibike.autenticacao.domain.main.enums.TypeStatus;
import br.com.mobibike.autenticacao.domain.main.persistence.InsertableEntity;
import br.com.mobibike.autenticacao.domain.main.persistence.UpdatableEntity;
import br.com.mobibike.autenticacao.main.util.HashUtils;

@Entity
@Table(name = "CREDENCIAL")
public class Credencial implements InsertableEntity<Long>, UpdatableEntity<Long> {

	@Id
	@Column(name = "ID_CREDENCIAL")
	@SequenceGenerator(name = "seqCredencial", sequenceName = "SEQ_CREDENCIAL", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqCredencial")
	private Long id;

	@NotNull
	@Size(min = 3, max = 128, message = "O campo usuário deve conter entre {min} e {max} caracteres")
	private String usuario;

	@NotNull
	private String senha;

	@ManyToOne
	@JoinColumn(name = "ID_APLICACAO_FK", updatable = false)
	private Aplicacao aplicacao;

	@Column(name = "TOKEN_RESET_SENHA")
	private String tokenResetSenha;

	@Column(name = "EXPIRACAO_TOKEN_RESET_SENHA")
	private LocalDateTime expiracaoTokenResetSenha;

	@Column(name = "DT_CADASTRO", updatable = false)
	private LocalDateTime dataCadastro;

	@Column(name = "DT_ALTERACAO", insertable = false)
	private LocalDateTime dataAlteracao;

	@Column(name = "FL_STATUS", columnDefinition = "char")
	@Enumerated(EnumType.STRING)
	private TypeStatus status;

	@PrePersist
	private void executarAcaoPrePersistencia() {
		usuario = usuario.toLowerCase();
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T merge(T revision) {
		Credencial credencialRevision = (Credencial) revision;
		usuario = credencialRevision.getUsuario();
		status = credencialRevision.getStatus();
		dataAlteracao = credencialRevision.getDataAlteracao();

		return (T) this;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@JsonIgnore
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = HashUtils.gerarHashMD5(senha);
	}

	public String getTokenResetSenha() {
		return tokenResetSenha;
	}

	public void setTokenResetSenha(String tokenResetSenha) {
		this.tokenResetSenha = tokenResetSenha;
	}

	public LocalDateTime getExpiracaoTokenResetSenha() {
		return expiracaoTokenResetSenha;
	}

	public void setExpiracaoTokenResetSenha(LocalDateTime expiracaoTokenResetSenha) {
		this.expiracaoTokenResetSenha = expiracaoTokenResetSenha;
	}

	@Override
	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	@Override
	public LocalDateTime getDataAlteracao() {
		return dataAlteracao;
	}

	public TypeStatus getStatus() {
		return status;
	}

	public void setStatus(TypeStatus status) {
		this.status = status;
	}

	public Aplicacao getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(Aplicacao aplicacao) {
		this.aplicacao = aplicacao;
	}

	@Override
	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public void setDataAlteracao(LocalDateTime dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	@Override
	public String toString() {
		return "Credencial [id=" + id + ", usuario=" + usuario + ", aplicacao=" + aplicacao + ", status=" + status
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aplicacao == null ? 0 : aplicacao.hashCode());
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Credencial other = (Credencial) obj;
		if (aplicacao == null) {
			if (other.aplicacao != null) {
				return false;
			}
		} else if (!aplicacao.equals(other.aplicacao)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
