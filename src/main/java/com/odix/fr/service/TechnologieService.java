package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import com.odix.fr.model.Technologie;

public interface TechnologieService {
	
	public List<Technologie> getAllTechnologies();
	
	public Technologie getTechnologie(UUID id);
	
	public Technologie addTechnologie(Technologie technologie);
	
	public Technologie editTechnologie(Technologie technologie);
	
	public boolean deleteTechnologie(UUID id);
}
