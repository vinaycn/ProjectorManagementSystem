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
import org.projector_management_system.modal.Team;
import org.projector_management_system.repository.ProjectorReservationRepo;
import org.projector_management_system.repository.TeamRepo;
import org.projector_management_system.service.TeamService;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TeamServiceTests {

	/*
	 * @TestConfiguration static class TeamServiceConfiguration{
	 * 
	 * @Bean public TeamService teamService(){ return new TeamService(); } }
	 */

	@Mock
	private TeamRepo teamrepo;

	@Mock
	private ProjectorReservationRepo projectorReservationRepo;

	@InjectMocks
	private TeamService teamService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	/*
	 * @Test public void WhenTeamAdded_thenReturnTeamList(){ Team testTeam = new
	 * Team(); testTeam.setName("sad"); testTeam.setTeamId(1);
	 * when(teamrepo.save(testTeam)).thenReturn(testTeam); teamService.
	 * List<Team> teamList = teamService.addTeam("Some");;
	 * 
	 * assertThat(teamList.size()).isEqualTo(1);
	 * 
	 * }
	 */

	@Test
	public void WhenGetTeamIsAsked_ThenTeamListIsReturned() {
		List<Team> teamList = new ArrayList<>();
		Team team1 = new Team();
		Team team2 = new Team();
		Team team3 = new Team();
		Team team4 = new Team();
		teamList.add(team4);
		teamList.add(team3);
		teamList.add(team2);
		teamList.add(team1);
		when(teamrepo.findAll()).thenReturn(teamList);
		List<Team> result = teamService.getTeam();
		assertThat(result.size()).isEqualTo(4);
	}

	@Test
	public void GivenTeamIDToDelete_ThenTeamIsDeleted(){
		
		Team deleteTeam = new Team();
		deleteTeam.setName("wqsdw");
		deleteTeam.setTeamId(1);
		teamService.deleteTeam(1);
		verify(teamrepo,times(1)).delete(1);
	}

}
