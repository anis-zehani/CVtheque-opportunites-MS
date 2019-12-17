package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Technologie;
import com.odix.fr.repository.TechnologieRepository;

@Service
public class TechnologieServiceImpl implements TechnologieService{
	

	private final TechnologieRepository technologieRepository;

	TechnologieServiceImpl(TechnologieRepository technologieRepository) {
		super();
		this.technologieRepository = technologieRepository;
	}
	
	public List<Technologie> getAllTechnologies() {
		
		List<Technologie> listeTechnologies = technologieRepository.findAll();

		return listeTechnologies;
	}
	
	public Technologie getTechnologie(UUID id) {
		
		return technologieRepository.getOne(id);
	}
	
	//Ajouter une technologie
	public Technologie addTechnologie(Technologie technologie) 
	{
		if(technologieRepository.findByNomTechnologie(technologie.getNomTechnologie()) == null)
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Modifier une technologie
	public Technologie editTechnologie(Technologie technologie) 
	{
		if(technologieRepository.existsById(technologie.getId()))
		{
			return technologieRepository.save(technologie);
		}
		return null;
	}
	
	//Supprimer une technologie
	public boolean deleteTechnologie(UUID id) 
	{
		if(technologieRepository.existsById(id))
		{
			try 
			{
			technologieRepository.deleteById(id);
			return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteTechnologie :"+e);
			}
		}
		return false;
	}
}
