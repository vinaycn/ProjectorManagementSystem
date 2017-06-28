package org.projector_management_system.service;

import java.util.List;

import org.projector_management_system.modal.Projector;
import org.projector_management_system.modal.ReserveProjector;
import org.projector_management_system.modal.ReservedProjectorWrapper;
import org.projector_management_system.modal.Team;

public interface IProjectorManagementService {

	List<Projector> addProjector(String name);
	
	List<Projector> getProjectors();
	
	String reserveProjector(int teamId,long startTime,long endTime);
	
	List<ReserveProjector> deleteTheRequestForProjector(int id);
	
	long getLatestAvailableProjector(List<ReserveProjector> reserveProjectors);
	
	List<Projector> deleteProjector(int id);
}
