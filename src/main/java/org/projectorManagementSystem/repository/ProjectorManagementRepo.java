package org.projectorManagementSystem.repository;

import org.projectorManagementSystem.modal.Projector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectorManagementRepo extends JpaRepository<Projector,Integer> {

	
}
