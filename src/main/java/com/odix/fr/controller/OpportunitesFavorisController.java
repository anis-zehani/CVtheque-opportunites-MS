package com.odix.fr.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.odix.fr.model.OpportunitesFavoris;
import com.odix.fr.service.OpportunitesFavorisService;


@CrossOrigin
@RestController
@RequestMapping("/api/opportunitesfavoris")
public class OpportunitesFavorisController {
	
	@Autowired
	OpportunitesFavorisService opportunitesFavorisService;
	
	// Lister les Opportunités Favorites pour un Utilisateur
	@GetMapping("/getAllOpportunitesFavorisForUtilisateur/{idUtilisateur}")
	public List<OpportunitesFavoris> getAllOpportunitesFavorisForUtilisateur(@PathVariable UUID idUtilisateur){
		
		return opportunitesFavorisService.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
	}
	
	// Vérifie si une Opportunité existe dèja dans la liste des favoris d'un Utilisateur
	@GetMapping("/checkIfOpportuniteExistsDansFavorisUtilisateur/{idUtilisateur}/{idOpportunite}")
	public boolean checkIfOpportuniteExistsDansFavorisUtilisateur(@PathVariable UUID idUtilisateur, @PathVariable UUID idOpportunite) {
		
		return opportunitesFavorisService.checkIfOpportuniteExistsDansFavorisUtilisateur(idUtilisateur, idOpportunite);
	}
	
	// Ajouter une Opportunité Favorite à un Utilisateur
	@PostMapping("/addOpportuniteToFavorisToUtilisateur")
	public OpportunitesFavoris addOpportuniteToFavorisToUtilisateur(@RequestBody OpportunitesFavoris opportunitesFavoris) {
		
		return opportunitesFavorisService.addOpportuniteToFavorisToUtilisateur(opportunitesFavoris);
	}
	
	// Supprimer une Opportunité Favorite pour un Utilisateur
	@DeleteMapping("/deleteOpportuniteFromFavorisToUtilisateur/{idOpportuniteFavorie}")
	public void deleteOpportuniteFromFavorisToUtilisateur(@PathVariable UUID idOpportuniteFavorie) {
		
		opportunitesFavorisService.deleteOpportuniteFromFavorisToUtilisateur(idOpportuniteFavorie);
	}
}
