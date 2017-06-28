package org.projector_management_system.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.projector_management_system.modal.Projector;
import org.projector_management_system.modal.Team;
import org.projector_management_system.repository.ProjectorManagementRepo;
import org.projector_management_system.repository.ProjectorReservationRepo;
import org.projector_management_system.repository.TeamRepo;
import org.projector_management_system.service.ProjectorManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ProjectorManagementTests {

	
	@Mock
	private ProjectorManagementRepo projectorManagementRepo;

	@Mock
	private ProjectorReservationRepo projectorReservationRepo;

	@Mock
	private TeamRepo teamRepo;
	
	@InjectMocks
	private ProjectorManagementService projectorManagementService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void WhenGetProjectorsIsCalled_ThenReturnProjectorList(){
		List<Projector> proList = new ArrayList<>();
		Projector p = new Projector();
		Projector p1 = new Projector();
		Projector p2 = new Projector();
		Projector p3 = new Projector();
		proList.add(p3);
		proList.add(p2);
		proList.add(p1);
		proList.add(p);
		when(projectorManagementRepo.findAll()).thenReturn(proList);
		
		List<Projector> result = projectorManagementService.getProjectors();
		assertThat(result.size()).isEqualTo(4);
		
	}
	
	
	@Test
	public void WhenGivenProId_ThenDeleteProjector(){
		
		Projector deletProjector = new Projector();
		deletProjector.setName("wqsdw");
		deletProjector.setId(1);
		projectorManagementService.deleteProjector(1);
		verify(projectorManagementRepo,times(1)).delete(1);
	}
	
}
