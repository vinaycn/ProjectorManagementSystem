package org.projectorManagementSystem.testservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.projectormanagementsystem.modal.Projector;
import org.projectormanagementsystem.modal.ReserveProjector;
import org.projectormanagementsystem.modal.Team;
import org.projectormanagementsystem.repository.ProjectorManagementRepo;
import org.projectormanagementsystem.repository.ProjectorReservationRepo;
import org.projectormanagementsystem.repository.TeamRepo;
import org.projectormanagementsystem.service.ProjectorManagementService;
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
	public void WhenGetProjectorsListofProjectorsShouldBeReturned(){
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
	public void GivenProIdThenDeleteProjector(){
		
		Projector deletProjector = new Projector();
		deletProjector.setName("wqsdw");
		deletProjector.setId(1);
		projectorManagementService.deleteProjector(1);
		verify(projectorManagementRepo,times(1)).delete(1);
	}
	
	@Test
	public void WhenProjectorIsRequestedThenBookTheProjector() throws ParseException{
		Team team = new Team("Some");	
		Projector projector = new Projector();
		projector.setName("PRO1");
		Projector projector1 = new Projector();
		projector.setName("PRO2");
		Projector projector2 = new Projector();
		projector.setName("PRO3");
		List<Projector> projectorList = new ArrayList<>();
		projectorList.add(projector2);
		projectorList.add(projector1);
		projectorList.add(projector);
		
		ReserveProjector reserveProjector = new ReserveProjector();
		reserveProjector.setEndTime(12344);
		reserveProjector.setStartTime(11101);
		reserveProjector.setTeam(team);
		reserveProjector.setProjector(projector);
		List<ReserveProjector> reserveProjectorsList = new ArrayList<>();
		reserveProjectorsList.add(reserveProjector);
		
		when(projectorReservationRepo.findByStartTimeAndEndTime(11111,22222)).thenReturn(reserveProjectorsList);
		when(projectorManagementRepo.findAll()).thenReturn(projectorList);
		when(teamRepo.findOne(1)).thenReturn(team);
		String some = projectorManagementService.reserveProjector(1, 11123,12354);
		assertThat(some).isNotEmpty();
		
	}
	
	@Test
	public void WhenProjectorIsAvailableThenAvailableProjectorShouldNotReturnNUll(){
		Team team = new Team("Some");
		Projector projector = new Projector();
		projector.setName("PRO1");
		projector.setId(1);
		Projector projector1 = new Projector();
		projector1.setName("PRO2");
		projector1.setId(2);
		Projector projector2 = new Projector();
		projector2.setName("PRO3");
		projector2.setId(3);
		List<Projector> projectorList = new ArrayList<>();
		projectorList.add(projector2);
		projectorList.add(projector1);
		projectorList.add(projector);
		ReserveProjector reserveProjector = new ReserveProjector();
		reserveProjector.setEndTime(12344);
		reserveProjector.setStartTime(11101);
		reserveProjector.setTeam(team);
		reserveProjector.setProjector(projector);
		List<ReserveProjector> reserveProjectorsList = new ArrayList<>();
		reserveProjectorsList.add(reserveProjector);
		when(projectorManagementRepo.findAll()).thenReturn(projectorList);
		
		Projector aprojector = projectorManagementService.findTheAvailableProjector(reserveProjectorsList, projectorList);
		assertThat(aprojector).isNotNull();
	}
	
	
	@Test
	public void WhenProjectorIsNotAvailableThenAvailableProjectorReturnNUll(){
		Team team = new Team("Some");
		Projector projector = new Projector();
		projector.setName("PRO1");
		projector.setId(1);
		List<Projector> projectorList = new ArrayList<>();
		projectorList.add(projector);
		ReserveProjector reserveProjector = new ReserveProjector();
		reserveProjector.setEndTime(12344);
		reserveProjector.setStartTime(11101);
		reserveProjector.setTeam(team);
		reserveProjector.setProjector(projector);
		List<ReserveProjector> reserveProjectorsList = new ArrayList<>();
		reserveProjectorsList.add(reserveProjector);
		when(projectorManagementRepo.findAll()).thenReturn(projectorList);
		
		Projector aprojector = projectorManagementService.findTheAvailableProjector(reserveProjectorsList, projectorList);
		assertThat(aprojector).isNull();
	}
}
