package web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Mappings.MappingNames;
import Views.ViewNames;
import data.Spitter;
import data.SpitterRepositoryImpl;

@Controller
@RequestMapping(value = MappingNames.SPITTER)
public class SpitterController {

	// == fields ==
	private SpitterRepositoryImpl spitterRepository;
	

	// == constructors ==
	@Autowired
	public SpitterController(SpitterRepositoryImpl spitterRepository) {
		super();
		this.spitterRepository = spitterRepository;
	}
	
	public SpitterController() {
		super();
		this.spitterRepository=null;
	}
	
	
	@RequestMapping(value = MappingNames.REGISTER, method = RequestMethod.GET)
	public String showRegistrationForm() {
		return ViewNames.REGISTER;
	}

	@RequestMapping(value=MappingNames.REGISTER, method=RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors) {
		
		if (errors.hasErrors()) {
			return ViewNames.REGISTER;
		}
		
		spitterRepository.save(spitter);
		return "redirect:" + ViewNames.SPITTER + "/" + spitter.getUsername();
	}
	
	@RequestMapping(value="/{username}",method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable("username") String username, Model model) {
		Spitter spitter = spitterRepository.findByUsername(username);
		model.addAttribute(spitter);
		return ViewNames.PROFILE;
		
	}

}
