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
/****
 * This class have all the service level implementation for the team like
 * Add team, Delete Team, and Get all the Teams.
 * @author vinay
 *
 */
public class TeamService implements ITeamService {

	
	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;

	/**
	 * @return List of teams
	 */
	@Override
	public List<Team> getTeam() {
		
		logger.info("Get all the teams");
		return teamRepo.findAll();
	}

	/**
	 * @param Will take the team name as parameter
	 * @return will return the list of teams
	 */
	@Override
	@Transactional
	public List<Team> addTeam(String name) {
		logger.info("Adding team to the database with name " +name);
		Team team = new Team();
		team.setName(name);
		teamRepo.save(team);
		return teamRepo.findAll();
	}

	/**
	 * @param Will take team id as the parameter
	 * @return will return list of the teams
	 */
	@Override
	@Transactional
	public List<Team> deleteTeam(int id) {
		logger.info("Deleting team with id " +id);
		teamRepo.delete(id);
		return teamRepo.findAll();
	}

	/**
	 * @return list of the reserved projectors
	 */
	@Override
	@Transactional
	public List<ReserveProjector> getReservedForProjectors() {
		logger.info("Get All the reserved Projectors");
		return projectorReservationRepo.findAll();

	}

}
