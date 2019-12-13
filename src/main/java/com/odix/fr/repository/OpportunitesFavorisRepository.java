package com.odix.fr.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.odix.fr.model.OpportunitesFavoris;



public interface OpportunitesFavorisRepository  extends JpaRepository<OpportunitesFavoris, UUID> {

	List<OpportunitesFavoris> findByIdUtilisateur(@Param("idUtilisateur") UUID idUtilisateur);
	
	// Vérifie si une Opportunité existe dèja dans la liste des favoris d'un Utilisateur
	OpportunitesFavoris findByIdUtilisateurAndIdOpportunite(
			@Param("idUtilisateur") UUID idUtilisateur, 
			@Param("idOpportunite") UUID idOpportunite);
}
