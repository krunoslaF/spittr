package web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Mappings.MappingNames;
import Views.ViewNames;
import data.Spittle;
import data.SpittleRepository;
import util.ConstantsUsed;

@Controller
public class SpittleController {

	private SpittleRepository spittleRepository;

	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		super();
		this.spittleRepository = spittleRepository;
	}
	
	@RequestMapping(value= "/spittles",method=RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam(value="max", defaultValue=ConstantsUsed.MAX_LONG_AS_STRING) long max,
									@RequestParam(value="count",defaultValue="20") int count) {
		// model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		// return ViewNames.SPITTLES;
		
		// ako handler metoda vrac objekt ili kolekciju, vrijednost se stavlja u model i key je 
		// odabran iz tipa koji se vraca (ovdje je: spittleList), dok je ime viewa odabrano iz valuea
		// koji metoda handlea (ovdje spittles, bez '/')
		return spittleRepository.findSpittles(max, count);
	}
	
	@RequestMapping(value=MappingNames.SHOW, method=RequestMethod.GET)
	public String showSpittle(@RequestParam(value="spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return ViewNames.SPITTLE;
	}
	
	
	// ako na PathVariable ne stavimo nikakvo ime, pretpostavlja da je Placeholder
	// iz RequestMappinga istog imena kao i argument metode, pa se može izostaviti
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return ViewNames.SPITTLE;
	}
	
	
}
