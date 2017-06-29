package org.projectorManagementSystem.repository;

import java.util.List;

import org.projectorManagementSystem.modal.ReserveProjector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectorReservationRepo extends JpaRepository<ReserveProjector, Integer> {

	@Query(value = "select new org.projectorManagementSystem.modal.ReserveProjector(rp.projector,rp.startTime, rp.endTime) from ReserveProjector rp where (rp.startTime between"
			+ " ?1 and ?2) or (rp.endTime between ?1 and ?2) or (rp.startTime < ?1 and rp.endTime > ?2)")
	List<ReserveProjector> findByStartTimeAndEndTime(@Param("startTime") long startTime, @Param("endTime") long endTime);
}
