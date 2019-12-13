package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import com.odix.fr.model.OpportunitesFavoris;


public interface OpportunitesFavorisService {

	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(UUID idUtilisateur);
	
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(OpportunitesFavoris opportunitesFavoris);
	
	public void deleteOpportuniteFromFavorisToUtilisateur(UUID idOpportuniteFavorie);
	
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(UUID idUtilisateur, UUID idOpportunite);
}
