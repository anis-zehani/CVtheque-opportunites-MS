package com.odix.fr.webClients;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.odix.fr.model.Candidat;

@FeignClient("gateway-MS")
public interface CandidatClient {

	@GetMapping("/api/candidat/allCandidatsByOpportunite/{id}")
	List<Candidat> getAllCandidatsByOpportunite(@PathVariable("id") UUID id);
	
	@DeleteMapping("/deleteLinkCandidatOpportunite/{idCandidat}/{idOpportunite}")
	void deleteLinkCandidatOpportunite(@PathVariable("idCandidat") UUID idCandidat, @PathVariable("idOpportunite") UUID idOpportunite);
}
