package org.projector_management_system.controller;

import java.util.List;

import org.projector_management_system.modal.ReserveProjector;
import org.projector_management_system.modal.Team;
import org.projector_management_system.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

	
	private static final Logger logger = LoggerFactory.getLogger(ProjectorManagementController.class);
	
	@Autowired
	private TeamService teamService;
	
	@RequestMapping(value="/add-team",method=RequestMethod.POST,produces = "application/json")
	public List<Team> addTeam(@RequestParam("name") String name){
		logger.info("-- Adding Team --");
		return teamService.addTeam(name);
	}
	
	
	@RequestMapping(value="/delete-team",method=RequestMethod.POST)
	public List<Team> deleteTeam(@RequestParam("id") String id){
		logger.info("-- Deleting Team --");
		return teamService.deleteTeam(Integer.valueOf(id));
	}
	
	
	@RequestMapping(value="/get-teams")
	public List<Team> getTeam(){
		logger.info("-- get Teams --");
		return  teamService.getTeam();
	}
	
	
	@RequestMapping(value="/getReserved-projectors",method=RequestMethod.POST,produces = "application/json")
	public List<ReserveProjector> getReservedProjectorsForTeam(@RequestParam("id") String id){
		logger.info("-- Get Reserved Projectors --");
		return  teamService.getReservedForProjectors(id);
	}
	
	
}
