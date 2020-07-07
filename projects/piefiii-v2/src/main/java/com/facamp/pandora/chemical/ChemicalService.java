package com.facamp.pandora.chemical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChemicalService {

	@Autowired
	private ChemicalRepository chemicalRepository;

	/*private List<Chemical> chemicals = new ArrayList<> (Arrays.asList(
			new Chemical(0000, "Hidrogênio Líquido", 5),
			new Chemical(0001, "Metano", 10),
			new Chemical(0002, "Ácido Sulfúrico", 15)
			));*/
	
	public List<Chemical> getAllChemicals(){
		//return chemicals;
		List<Chemical> chemicals = new ArrayList<>();
		chemicalRepository.findAll().forEach(chemicals::add);
		return chemicals;
	}
	
	public Chemical getChemical(Integer id) {
		return chemicalRepository.findOne(id);
	}
	
	public void addChemical(Chemical chemical){
		chemicalRepository.save(chemical);
	}

	public void updateChemical(int id, Chemical chemical) {
		chemicalRepository.save(chemical);
		
	}

	public void deleteChemical(int id) {
		//chemicals.removeIf(t->t.getId().equals(id));
		chemicalRepository.delete(id);
	}
}
