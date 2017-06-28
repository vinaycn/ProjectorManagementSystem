package org.projector_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.projector_management_system.modal.ReserveProjector;
import org.projector_management_system.modal.Team;
import org.projector_management_system.repository.ProjectorReservationRepo;
import org.projector_management_system.repository.TeamRepo;
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
