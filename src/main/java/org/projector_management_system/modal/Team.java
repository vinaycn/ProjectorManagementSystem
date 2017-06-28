package org.projector_management_system.modal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Team implements Serializable {

	
	private static final long serialVersionUID = -3009157732242241606L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teamId;
	private String name;
	@OneToMany(cascade=CascadeType.REMOVE,mappedBy="team")
	@JsonBackReference
	private List<ReserveProjector> teamReservations;
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ReserveProjector> getTeamReservations() {
		return teamReservations;
	}
	public void setTeamReservations(List<ReserveProjector> teamReservations) {
		this.teamReservations = teamReservations;
	}
	
	
	
}
