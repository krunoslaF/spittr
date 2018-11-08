package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import Mappings.MappingNames;
import Views.ViewNames;

@Controller
public class HomeController {

	@RequestMapping(value=MappingNames.HOME, method=RequestMethod.GET)
	public String home() {
		return ViewNames.HOME;
	}
}
