package org.projectorManagementSystem.service;

import java.util.List;

import org.projectorManagementSystem.modal.ReserveProjector;
import org.projectorManagementSystem.modal.Team;

public interface ITeamService {

	
	List<Team> addTeam(String name);
	
	List<Team> getTeam();
	
	List<Team> deleteTeam(int id);
	
	List<ReserveProjector> getReservedForProjectors();
}
