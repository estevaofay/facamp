package com.facamp.pandora.chemical;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ChemicalController {
	
	@Autowired
	private ChemicalService chemicalService;
	
	@RequestMapping("/chemicals")
	public List<Chemical> getAll(){
		return chemicalService.getAllChemicals();
	}
	
	@RequestMapping("/chemicals/{id}")
	public Chemical getChemical(@PathVariable Integer id){
		return chemicalService.getChemical(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/chemicals")
	public void addChemical(@RequestBody Chemical chemical) {
		chemicalService.addChemical(chemical);
	}
	
	/*
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public void updateTopic(@RequestBody Topic topic, @PathVariable String id) {
		topicService.updateTopic(id, topic);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public void deleteTopic(@PathVariable String id){
		topicService.deleteTopic(id);
			
	}*/
}
