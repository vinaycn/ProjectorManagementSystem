package org.projectormanagementsystem.controller;

import java.text.ParseException;
import java.util.List;

import org.projectormanagementsystem.busisness.ConvertDate;
import org.projectormanagementsystem.modal.Projector;
import org.projectormanagementsystem.modal.ReserveProjector;
import org.projectormanagementsystem.service.ProjectorManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author vinay
 */
@RestController
public class ProjectorController {

	
	private static final Logger logger = LoggerFactory.getLogger(ProjectorController.class);
	
	@Autowired
	private ProjectorManagementService projectorManagementService;

	@Autowired
	private ConvertDate convertDate;
	

	@RequestMapping(value = "/add-projector", method = RequestMethod.POST)
	public List<Projector> addprojector(@RequestParam("name") String name) {
		logger.info("-- Adding Projector with name -- " + name);
		return projectorManagementService.addProjector(name);
	}

	@RequestMapping(value = "/get-projectors")
	public List<Projector> getProjector() {
		logger.info("-- Getting all the Projectors --");
		return projectorManagementService.getProjectors();
	}

	@RequestMapping(value = "/delete-projector")
	public List<Projector> deleteProjector(@RequestParam("id") String id) {
		logger.info("-- Deleting Projector with id " + id);
		return projectorManagementService.deleteProjector(Integer.valueOf(id));
	}
	
	@RequestMapping(value = "/delete-projectorRequest",method=RequestMethod.POST)
	public List<ReserveProjector> deleteProjectorRequest(@RequestParam("id") String id) {
		logger.info("-- Deleting Projector Request with id " + id);
		return projectorManagementService.deleteTheRequestForProjector(Integer.valueOf(id));
	}

	
	
	@RequestMapping(value = "/make-reservation", method = RequestMethod.POST)
	public String makeProjectorReservation(@RequestParam String teamId, @RequestParam String from,
			@RequestParam String to) throws NumberFormatException, ParseException {
		logger.info("-- User Requesting for a Projector for the team Id " +teamId + " from " + from + "to " +to);
		return projectorManagementService.reserveProjector(Integer.valueOf(teamId),convertDate.convertDate(from),
				convertDate.convertDate(to));
	}
}
