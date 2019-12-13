package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.OpportunitesFavoris;
import com.odix.fr.repository.OpportunitesFavorisRepository;


@Service
public class OpportunitesFavorisServiceImpl implements OpportunitesFavorisService {

	private final OpportunitesFavorisRepository opportunitesFavorisRepository;

	public OpportunitesFavorisServiceImpl(OpportunitesFavorisRepository opportunitesFavorisRepository) {
		super();
		this.opportunitesFavorisRepository = opportunitesFavorisRepository;
	}

    // Lister les Opportunités Favorites pour un Utilisateur
	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(UUID idUtilisateur) {

		List<OpportunitesFavoris> liste = opportunitesFavorisRepository.findByIdUtilisateur(idUtilisateur);
		
		return liste;
	}
	
	// Vérifie si une Opportunité existe dèja dans la liste des favoris d'un Utilisateur
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(UUID idUtilisateur, UUID idOpportunite) {
		
		if(opportunitesFavorisRepository.findByIdUtilisateurAndIdOpportunite(idUtilisateur, idOpportunite) != null)
			return true;
		return false;
	}

	// Ajouter une Opportunité Favorite à un Utilisateur
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(OpportunitesFavoris opportunitesFavoris) {
		
		return opportunitesFavorisRepository.save(opportunitesFavoris);
	}

	// Supprimer une Opportunité Favorite pour un Utilisateur
	public void deleteOpportuniteFromFavorisToUtilisateur(UUID idOpportuniteFavorie) {
		
		opportunitesFavorisRepository.deleteById(idOpportuniteFavorie);
	}
}
