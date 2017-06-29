package org.projectorManagementSystem.service;

import java.util.List;

import org.projectorManagementSystem.modal.Projector;
import org.projectorManagementSystem.modal.ReserveProjector;

public interface IProjectorManagementService {

	List<Projector> addProjector(String name);
	
	List<Projector> getProjectors();
	
	String reserveProjector(int teamId,long startTime,long endTime) throws Exception;
	
	List<ReserveProjector> deleteTheRequestForProjector(int id);
	
	long getLatestAvailableProjector(List<ReserveProjector> reserveProjectors);
	
	Projector findTheAvailableProjector(List<ReserveProjector> reserveProjectorList,
			List<Projector> projectorList);
	
	List<Projector> deleteProjector(int id);
}
