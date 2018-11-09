package spittr;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import Mappings.MappingNames;
import Views.ViewNames;
import data.Spitter;
import data.SpitterRepositoryImpl;
import data.Spittle;
import data.SpittleRepository;
import web.HomeController;
import web.SpitterController;
import web.SpittleController;

public class ControllerTest {

	@Test
	public void testHomePage() throws Exception {
		HomeController controller = new HomeController();

		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get(MappingNames.HOME)).andExpect(view().name(ViewNames.HOME));

	}

	@Test
	public void shouldShowRecentSpittles() throws Exception {
		List<Spittle> expectedSpittles = createSpittleList(20);
		SpittleRepository mockRepository = mock(SpittleRepository.class);
		when(mockRepository.findSpittles(Long.MAX_VALUE, 20)).thenReturn(expectedSpittles);

		SpittleController controller = new SpittleController(mockRepository);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp")).build();
		mockMvc.perform(get("/spittles")).andExpect(view().name("spittles"))
				.andExpect(model().attributeExists("spittleList"))
				.andExpect(model().attribute("spittleList", hasItems(expectedSpittles.toArray())));
	}

	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterController controller = new SpitterController();
		MockMvc mockMvc = standaloneSetup(controller).build();

		mockMvc.perform(get(MappingNames.SPITTER + MappingNames.REGISTER)).andExpect(view().name(ViewNames.REGISTER));
	}
	
	@Test
	public void shouldProcessRegistration() {
		SpitterRepositoryImpl mockRespository = mock(SpitterRepositoryImpl.class);
		Spitter unsaved = new Spitter("Jack", "Bauer", "jbauer", "24hours");
		Spitter saved = new Spitter(24L, "Jack", "Bauer", "jbauer", "24hours");
		
		SpitterController controller = new SpitterController(mockRespository);
		
		when(mockRespository.save(unsaved)).thenReturn(saved);
		
		MockMvc mockMvc = standaloneSetup(controller).build();
		try {
			
			mockMvc.perform(post(MappingNames.SPITTER+MappingNames.REGISTER)
													.param("firstName", "Jack")
													.param("lastName", "Bauer")
													.param("username", "jbauer")
													.param("password", "24hours"))
													.andExpect(redirectedUrl("/spitter/jbauer"));
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		try {
			verify(mockRespository,atLeastOnce()).save(unsaved);
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

	// == private methods ==
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("Spittle#" + i, new Date()));
		}
		return spittles;
	}

}
