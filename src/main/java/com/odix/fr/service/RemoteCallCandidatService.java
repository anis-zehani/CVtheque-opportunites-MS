package com.odix.fr.service;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.odix.fr.model.Utilisateur;

@FeignClient(name="gateway")
public interface RemoteCallCandidatService {
	
	@RequestMapping(method=RequestMethod.GET, value="/allCandidatsByOpportunite/{id}")
	// Utilisateur au lieu de Candidat afin de ne pas être obligé à créer le model Candidat dans ce Service : vu que j'ai besoin de l'ID uniquement
	public List<Utilisateur> getAllCandidatsByOpportunite(UUID idOpportunite);
	
	@RequestMapping(method=RequestMethod.DELETE, value="/deleteLinkCandidatOpportunite/{idCandidat}/{idOpportunite}")
	public void deleteLinkCandidatOpportunite(@PathVariable("idCandidat") UUID idCandidat, @PathVariable("idOpportunite") UUID idOpportunite);

}
