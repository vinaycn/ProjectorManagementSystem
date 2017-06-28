package org.projector_management_system.modal;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Projector implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "projector")
	@JsonBackReference
	private List<ReserveProjector> projectorReservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReserveProjector> getProjectorReservations() {
		return projectorReservations;
	}

	public void setProjectorReservations(List<ReserveProjector> projectorReservations) {
		this.projectorReservations = projectorReservations;
	}

	@Override
	public boolean equals(Object obj) {
		Projector projector = (Projector) obj;
		return projector.id == this.id;
	}

	@Override
	public int hashCode() {
		return this.id;
	}

	public Projector(String name) {
		this.name = name;
	}

	public Projector() {

	}
}
