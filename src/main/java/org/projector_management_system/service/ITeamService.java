package org.projector_management_system.service;

import java.util.List;

import org.projector_management_system.modal.ReserveProjector;
import org.projector_management_system.modal.Team;

public interface ITeamService {

	
	List<Team> addTeam(String name);
	
	List<Team> getTeam();
	
	List<Team> deleteTeam(int id);
	
	List<ReserveProjector> getReservedForProjectors(String id);
}
