package org.projectormanagementsystem.service;

import java.util.List;

import org.projectormanagementsystem.modal.ReserveProjector;
import org.projectormanagementsystem.modal.Team;

public interface ITeamService {

	
	List<Team> addTeam(String name);
	
	List<Team> getTeam();
	
	List<Team> deleteTeam(int id);
	
	List<ReserveProjector> getReservedForProjectors();
}
