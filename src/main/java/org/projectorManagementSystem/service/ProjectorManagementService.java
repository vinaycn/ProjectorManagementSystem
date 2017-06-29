package org.projectorManagementSystem.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.projectorManagementSystem.modal.Projector;
import org.projectorManagementSystem.modal.ReserveProjector;
import org.projectorManagementSystem.modal.Team;
import org.projectorManagementSystem.repository.ProjectorManagementRepo;
import org.projectorManagementSystem.repository.ProjectorReservationRepo;
import org.projectorManagementSystem.repository.TeamRepo;
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
		final Projector projector = new Projector();
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
	public String reserveProjector(int teamId, long startTime, long endTime)  {

		List<ReserveProjector> reservedProjectorList = projectorReservationRepo.findByStartTimeAndEndTime(startTime,
				endTime);
		List<Projector> projectorList = this.getProjectors();

		Team team = teamRepo.findOne(teamId);
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

	@Override
	public long getLatestAvailableProjector(List<ReserveProjector> reservedProjectorList) {

		reservedProjectorList
				.sort((ReserveProjector r1, ReserveProjector r2) -> (int) (r1.getEndTime() - r2.getEndTime()));
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

	@Override
	public Projector findTheAvailableProjector(final List<ReserveProjector> reserveProjectorList,
			final List<Projector> projectorList) {
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
