package org.projector_management_system.repository;

import org.projector_management_system.modal.Projector;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectorManagementRepo extends JpaRepository<Projector,Integer> {

	
}
