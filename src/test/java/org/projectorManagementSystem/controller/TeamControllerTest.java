package org.projectorManagementSystem.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.projectorManagementSystem.controller.TeamController;
import org.projectorManagementSystem.modal.Team;
import org.projectorManagementSystem.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private TeamService teamService;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void GivenTeamName_ThenReturnTeamJsonArray() throws Exception{
		
		List<Team> teamList = Arrays.asList(new Team("sdsa"));
	
		when(this.teamService.addTeam("sdsa")).thenReturn(teamList);
		
		mockMvc.perform(post("/add-team").param("name", "sdsa")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(1)))
				.andDo(print());
	}
	
	@Test
	public void GivenListTeams_ThenReturnTeamJsonArray() throws Exception{
		
		List<Team> teamList = Arrays.asList(new Team("sdsa"),new Team("abc"));
	
		when(this.teamService.getTeam()).thenReturn(teamList);
		
		mockMvc.perform(get("/get-teams")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(2)))
				.andDo(print());
	}
	
	@Test
	public void GivenTeamId_WhenTeamDeletedThenReturnNewList() throws Exception{
		
		Team team1 = new Team();
		team1.setTeamId(1);
		team1.setName("Abc");
		Team team2 = new Team();
		team2.setTeamId(2);
		team2.setName("CFC");
		
		List<Team> teamList = Arrays.asList(team1,team2);
	
		when(this.teamService.deleteTeam(1)).thenReturn(teamList);
		
		mockMvc.perform(post("/delete-team").param("id","1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(2)))
				.andDo(print());
	}
}
