package org.projectorManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.projectorManagementSystem.modal.ReserveProjector;
import org.projectorManagementSystem.modal.Team;
import org.projectorManagementSystem.repository.ProjectorReservationRepo;
import org.projectorManagementSystem.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService implements ITeamService {

	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;

	@Override
	public List<Team> getTeam() {
		// TODO Auto-generated method stub
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<Team> addTeam(String name) {
		Team team = new Team();
		team.setName(name);
		teamRepo.save(team);
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<Team> deleteTeam(int id) {
		teamRepo.delete(id);
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<ReserveProjector> getReservedForProjectors(String id) {

		return projectorReservationRepo.findAll();

	}

}
