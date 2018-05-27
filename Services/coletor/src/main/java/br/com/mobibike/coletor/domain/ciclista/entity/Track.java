package br.com.mobibike.coletor.domain.ciclista.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRACK")
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_VIAGEM_TRACK")
	private ViagemTrack viagem;

	private String coordinates;

	private Long acurracy;

	private Long speedAcurracy;

	private Long speed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ViagemTrack getViagem() {
		return viagem;
	}

	public void setViagem(ViagemTrack viagem) {
		this.viagem = viagem;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public Long getAcurracy() {
		return acurracy;
	}

	public void setAcurracy(Long acurracy) {
		this.acurracy = acurracy;
	}

	public Long getSpeedAcurracy() {
		return speedAcurracy;
	}

	public void setSpeedAcurracy(Long speedAcurracy) {
		this.speedAcurracy = speedAcurracy;
	}

	public Long getSpeed() {
		return speed;
	}

	public void setSpeed(Long speed) {
		this.speed = speed;
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
		Track other = (Track) obj;
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
