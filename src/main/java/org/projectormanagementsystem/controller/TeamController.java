package org.projectormanagementsystem.controller;

import java.util.List;

import org.projectormanagementsystem.modal.ReserveProjector;
import org.projectormanagementsystem.modal.Team;
import org.projectormanagementsystem.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

	
	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value="/add-team",method=RequestMethod.POST,produces = "application/json")
	public List<Team> addTeam(@RequestParam("name") String name){
		logger.info(" Adding Team with the name " +name);
		return teamService.addTeam(name);
	}
	
	
	@RequestMapping(value="/delete-team",method=RequestMethod.POST)
	public List<Team> deleteTeam(@RequestParam("id") String id){
		logger.info("-- Deleting Team with the id " + id);
		return teamService.deleteTeam(Integer.valueOf(id));
	}
	
	
	@RequestMapping(value="/get-teams")
	public List<Team> getTeam(){
		logger.info("-- get all the Teams --");
		return  teamService.getTeam();
	}
	
	
	@RequestMapping(value="/getReserved-projectors",method=RequestMethod.GET,produces = "application/json")
	public List<ReserveProjector> getReservedProjectorsForTeam(){
		logger.info("-- Get all the Reserved Projectors --");
		return  teamService.getReservedForProjectors();
	}
	
	
}
