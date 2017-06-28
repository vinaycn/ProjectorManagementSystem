package org.projector_management_system.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.projector_management_system.modal.Projector;
import org.projector_management_system.modal.ReserveProjector;
import org.projector_management_system.modal.Team;
import org.projector_management_system.repository.ProjectorManagementRepo;
import org.projector_management_system.repository.ProjectorReservationRepo;
import org.projector_management_system.repository.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectorManagementService implements IProjectorManagementService {

	@Autowired
	private ProjectorManagementRepo projectorManagementRepo;

	@Autowired
	private ProjectorReservationRepo projectorReservationRepo;

	@Autowired
	private TeamRepo teamRepo;

	@Override
	@Transactional
	public List<Projector> addProjector(String name) {
		Projector projector = new Projector();
		projector.setName(name);
		projectorManagementRepo.saveAndFlush(projector);
		return projectorManagementRepo.findAll();
	}

	@Override
	@Transactional
	public List<Projector> getProjectors() {
		return projectorManagementRepo.findAll();
	}

	@Override
	@Transactional
	public String reserveProjector(int teamId, long startTime, long endTime) {

		List<ReserveProjector> reservedProjectorList = projectorReservationRepo.findByStartTimeAndEndTime(startTime,
				endTime);
		List<Projector> projectorList = this.getProjectors();
		System.out.println(reservedProjectorList.size());
		Team team = teamRepo.findOne(teamId);
		Projector availableprojector = null;
		if (reservedProjectorList.size() == 0) {
			availableprojector = projectorList.get(0);
			ReserveProjector reserveProjector = new ReserveProjector();
			reserveProjector.setEndTime(endTime);
			reserveProjector.setProjector(availableprojector);
			reserveProjector.setTeam(team);
			reserveProjector.setStartTime(startTime);
			projectorReservationRepo.saveAndFlush(reserveProjector);
			return "Projector " + availableprojector.getName() + " Reserved Successfully fopr you!";
		} else {
			boolean proFound = true;
			Set<Projector> reservedProjectorSet = new HashSet<>();
			for (ReserveProjector rprojector2 : reservedProjectorList) {
				System.out.println(" Projector Which Are not Availanble " + rprojector2.getProjector().getName());
				reservedProjectorSet.add(rprojector2.getProjector());
			}
			for (Projector projector : projectorList) {
				System.out.println("Projector Name " + projector.getName());
				if (!reservedProjectorSet.contains(projector)) {
					availableprojector = projector;
					proFound = false;
					break;
				}
			}
			if (proFound) {
				long getNextTime = getLatestAvailableProjector(reservedProjectorList);
				return "Sorry! Projectors are not Available " + "the earliest Available is @ " + new Date(getNextTime);
			}
			ReserveProjector reserveProjector = new ReserveProjector();
			reserveProjector.setEndTime(endTime);
			reserveProjector.setProjector(availableprojector);
			reserveProjector.setTeam(team);
			reserveProjector.setStartTime(startTime);
			projectorReservationRepo.saveAndFlush(reserveProjector);
			return "Projector " + availableprojector.getName() + " Reserved Successfully fopr you!";
		}

	}

	@Override
	public long getLatestAvailableProjector(List<ReserveProjector> reservedProjectorList) {

		reservedProjectorList.sort((ReserveProjector r1,
				ReserveProjector r2) -> (int) (r1.getEndTime() - r2.getEndTime()));
		return reservedProjectorList.get(0).getEndTime();

	}

	@Override
	@Transactional
	public List<Projector> deleteProjector(int id) {
		projectorManagementRepo.delete(id);
		return projectorManagementRepo.findAll();
	}

	@Override
	@Transactional
	public List<ReserveProjector> deleteTheRequestForProjector(int id) {
		projectorReservationRepo.delete(id);
		return projectorReservationRepo.findAll();
	}

}
