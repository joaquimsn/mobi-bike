package br.com.mobibike.coletor.domain.ciclista.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;

@Entity
@Table(name = "VIAGEM_TRACK")
public class ViagemTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long ciclistaId;

	private LocalDateTime startTime;

	private LocalDateTime endTime;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "viagem", fetch = FetchType.EAGER)
	private List<Track> tracks = new ArrayList<>();

	@JsonIgnore
	@Transient
	private JsonNode device;

	private LocalDateTime dataCadastro;

	@PrePersist
	private void actionPrePersiste() {
		tracks.forEach(track -> {
			track.setViagem(this);
		});
	}

	public void addTrack(List<Track> tracks) {
		tracks.forEach(track -> {
			track.setViagem(this);
			this.tracks.add(track);
		});
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCiclistaId() {
		return ciclistaId;
	}

	public void setCiclistaId(Long ciclistaId) {
		this.ciclistaId = ciclistaId;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public JsonNode getDevice() {
		return device;
	}

	public void setDevice(JsonNode device) {
		this.device = device;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ViagemTrack other = (ViagemTrack) obj;
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
