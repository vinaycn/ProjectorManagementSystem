package org.projector_management_system.modal;

public class ReservedProjectorWrapper {

	private Projector projector;
	private long startTime;
	private long endTime;
	
	
	
	public Projector getProjector() {
		return projector;
	}
	public void setProjector(Projector projector) {
		this.projector = projector;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	
	
}
