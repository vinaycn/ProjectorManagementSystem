package org.projectorManagementSystem.service;

import java.util.List;

import org.projectorManagementSystem.modal.ReserveProjector;
import org.projectorManagementSystem.modal.Team;
import org.projectorManagementSystem.repository.ProjectorReservationRepo;
import org.projectorManagementSystem.repository.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TeamService implements ITeamService {

	
	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;

	@Override
	public List<Team> getTeam() {
		// TODO Auto-generated method stub
		logger.info("Get all the teams");
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<Team> addTeam(String name) {
		logger.info("Adding team to the database with name " +name);
		Team team = new Team();
		team.setName(name);
		teamRepo.save(team);
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<Team> deleteTeam(int id) {
		logger.info("Deleting team with id " +id);
		teamRepo.delete(id);
		return teamRepo.findAll();
	}

	@Override
	@Transactional
	public List<ReserveProjector> getReservedForProjectors() {
		logger.info("Get All the reserved Projectors");
		return projectorReservationRepo.findAll();

	}

}
