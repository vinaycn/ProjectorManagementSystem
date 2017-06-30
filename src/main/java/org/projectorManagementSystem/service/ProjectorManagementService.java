package org.projectorManagementSystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.projectorManagementSystem.modal.Projector;
import org.projectorManagementSystem.modal.ReserveProjector;
import org.projectorManagementSystem.modal.Team;
import org.projectorManagementSystem.repository.ProjectorManagementRepo;
import org.projectorManagementSystem.repository.ProjectorReservationRepo;
import org.projectorManagementSystem.repository.TeamRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectorManagementService implements IProjectorManagementService {

	
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectorManagementService.class);
	
	@Autowired
	private ProjectorManagementRepo projectorManagementRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;


	@Autowired
	private TeamRepo teamRepo;

	/**
	 * @param will take projector name
	 * @return will return list of the projector
	 */
	@Override
	@Transactional
	public List<Projector> addProjector(String name) {
		logger.info("Adding projector with the name " +name);
		final Projector projector = new Projector();
		projector.setName(name);
		projectorManagementRepo.saveAndFlush(projector);
		return projectorManagementRepo.findAll();
	}

	/**
	 * @return Will return  list of the projectors
	 */
	@Override
	@Transactional
	public List<Projector> getProjectors() {
		logger.info("Getting all the projectors from the database ");
		return projectorManagementRepo.findAll();
	}

	/**
	 * @param will take the teamId
	 * @param starttime requested from the user
	 * @param endtime requested from the user
	 * @return the approriate messages 
	 */
	@Override
	@Transactional
	public String reserveProjector(int teamId, long startTime, long endTime)  {
		
		
		List<ReserveProjector> reservedProjectorList = projectorReservationRepo.findByStartTimeAndEndTime(startTime,
				endTime);
		List<Projector> projectorList = this.getProjectors();

		Team team = teamRepo.findOne(teamId);
		logger.info("Reserving the projector for the team with the " +team.getName());
		final ReserveProjector reserveProjector;
		Projector availableprojector = null;
		if (reservedProjectorList.size() == 0) {
			availableprojector = projectorList.get(0);
			reserveProjector = new ReserveProjector(team, availableprojector, startTime, endTime);
			projectorReservationRepo.saveAndFlush(reserveProjector);
			return "Projector " + availableprojector.getName() + " Reserved Successfully!";
		} else {
			availableprojector = findTheAvailableProjector(reservedProjectorList, projectorList);
			if (null == availableprojector) {
				long getNextTime = getLatestAvailableProjector(reservedProjectorList);
				return "Sorry! Projectors are not Available. The earliest Available is @" + getNextTime;
			} else {
				reserveProjector = new ReserveProjector(team, availableprojector, startTime, endTime);
				projectorReservationRepo.saveAndFlush(reserveProjector);
				return "Projector " + availableprojector.getName() + " Reserved Successfully!";
			}
		}

	}

	/**
	 * @param will take the list of the reservedProjectorLists
	 * @return will return next available projector in the systems
	 */
	@Override
	public long getLatestAvailableProjector(List<ReserveProjector> reservedProjectorList) {

		reservedProjectorList
				.sort((ReserveProjector r1, ReserveProjector r2) -> (int) (r1.getEndTime() - r2.getEndTime()));
		return reservedProjectorList.get(0).getEndTime();

	}

	/**
	 * @param will take the id of the projector to delete
	 * @return will return the list of the projectors
	 */
	@Override
	@Transactional
	public List<Projector> deleteProjector(int id) {
		logger.info("Deleting the Projector with id " +id);
		projectorManagementRepo.delete(id);
		return projectorManagementRepo.findAll();
	}

	/**
	 * @param will take the id to delete the projector request
	 * @return list of the reserve projector
	 */
	@Override
	@Transactional
	public List<ReserveProjector> deleteTheRequestForProjector(int id) {
		logger.info("Deleting the ReserveProjector with id " +id);
		projectorReservationRepo.delete(id);
		return projectorReservationRepo.findAll();
	}

	/**
	 * @param reservedProjector list
	 * @param projector list
	 * @return return the available project which can be reserved
	 */
	@Override
	public Projector findTheAvailableProjector(final List<ReserveProjector> reserveProjectorList,
			final List<Projector> projectorList) {
		logger.info("Find the latest available projector for the given list ");
		final Set<Projector> reservedProjectorSet = new HashSet<>();
		for (ReserveProjector rprojector2 : reserveProjectorList) {
			reservedProjectorSet.add(rprojector2.getProjector());
		}
		for (Projector projector : projectorList) {

			if (!reservedProjectorSet.contains(projector)) {
				return projector;
			}
		}
		return null;
	}
}
