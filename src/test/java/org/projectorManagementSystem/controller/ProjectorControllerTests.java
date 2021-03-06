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
import org.projectormanagementsystem.busisness.ConvertDate;
import org.projectormanagementsystem.controller.ProjectorController;
import org.projectormanagementsystem.modal.Projector;
import org.projectormanagementsystem.service.ProjectorManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@WebMvcTest(ProjectorController.class)
public class ProjectorControllerTests {

	
	@Autowired
	private MockMvc mockMvc;
	
	
	@MockBean
	private ProjectorManagementService projectorManagementService;
	
	@MockBean
	private ConvertDate convertDate;
	
	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	
	@Test
	public void GivenProjector_ThenReturnProjectorJsonArray() throws Exception{
		
		List<Projector> teamList = Arrays.asList(new Projector("sdsa"));
	
		when(this.projectorManagementService.addProjector("Projector1")).thenReturn(teamList);
		
		mockMvc.perform(post("/add-projector").param("name", "Projector1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(1)))
				.andDo(print());
	}
	
	@Test
	public void GivenListProjectors_ThenReturnProjectorJsonArray() throws Exception{
		
		List<Projector> projectorsList = Arrays.asList(new Projector("sdsa"),new Projector("abc"));
	
		when(this.projectorManagementService.getProjectors()).thenReturn(projectorsList);
		
		mockMvc.perform(get("/get-projectors")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(2)))
				.andDo(print());
	}
	
	@Test
	public void GivenProjectorId_WhenProjectorDeletedThenReturnNewList() throws Exception{
		
		Projector projector = new Projector();
		projector.setId(1);
		projector.setName("ABC");
		Projector projector1 = new Projector();
		projector1.setId(2);
		projector1.setName("ABCC");
		
		
		List<Projector> projectorsList = Arrays.asList(projector,projector1);
	
		when(this.projectorManagementService.deleteProjector(1)).thenReturn(projectorsList);
		
		mockMvc.perform(post("/delete-projector").param("id","1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(2)))
				.andDo(print());
	}
	
	@Test
	public void GivenProjectorRequest_ThenReturnSutitableRequest() throws Exception{
		
		String some = "Some Message About the Projector Request";
		when(this.projectorManagementService.reserveProjector(1,111,121)).thenReturn(some);	
		mockMvc.perform(post("/make-reservation").param("teamId","1").param("from","12/02/2017 23:23").param("to","12/03/2017 23:43")
				.contentType(MediaType.TEXT_HTML_VALUE))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	
}
