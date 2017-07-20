package org.projectormanagementsystem.service;

import java.util.List;

import org.projectormanagementsystem.modal.ReserveProjector;
import org.projectormanagementsystem.modal.Team;
import org.projectormanagementsystem.repository.ProjectorReservationRepo;
import org.projectormanagementsystem.repository.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/****
 * This class have all the service level implementation for the team like
 * Add team, Delete Team, and Get all the Teams.
 * @author vinay
 */

@Service
public class TeamService implements ITeamService {

	
	private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
	
	@Autowired
	private TeamRepo teamRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;

	/**
	 * return the list of teams in the system
	 * 
	 * @return List of teams
	 */
	@Override
	public List<Team> getTeam() {
		
		logger.info("Get all the teams");
		return teamRepo.findAll();
	}

	/**
	 * add the team for the given name
	 * 
	 * @param name
	 *           name of the team to be added
	 * @return will return list of teams
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
	 * 
	 * delete the team of the given id
	 * 
	 * @param id
	 *          team id to delete
	 *          
	 * @return will return list of the teams after deletion
	 */
	@Override
	@Transactional
	public List<Team> deleteTeam(int id) {
		logger.info("Deleting team with id " +id);
		teamRepo.delete(id);
		return teamRepo.findAll();
	}

	/**
	 * 
	 * return list of the reserved projectors
	 * 
	 * @return list of the reserved projectors
	 */
	@Override
	@Transactional
	public List<ReserveProjector> getReservedForProjectors() {
		logger.info("Get All the reserved Projectors");
		return projectorReservationRepo.findAll();

	}

}
