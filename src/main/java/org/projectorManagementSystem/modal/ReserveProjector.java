package org.projectorManagementSystem.modal;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ReserveProjector implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reservationId;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@ManyToOne
	@JoinColumn(name = "projctor_id")
	private Projector projector;

	private long startTime;

	private long endTime;

	public int getReservationId() {

		return reservationId;

	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Projector getProjector() {
		return projector;
	}

	public void setProjector(Projector projector) {
		this.projector = projector;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public ReserveProjector(Team team, Projector projector, long startTime, long endTime) {
		super();

		this.team = team;
		this.projector = projector;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public ReserveProjector() {
		// TODO Auto-generated constructor stub
	}
	
	public ReserveProjector(Projector projector,long startTime,long endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.projector = projector;
	}
}
