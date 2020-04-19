package com.odix.fr.webClients;

import java.util.List;
import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.odix.fr.model.Candidat;

@FeignClient(name = "gateway-ms", fallback = CandidatClient.CandidatClientFallback.class)
public interface CandidatClient {

	@GetMapping("/api/candidat/allCandidatsByOpportunite/{id}")
	List<Candidat> getAllCandidatsByOpportunite(@PathVariable("id") UUID id);

	@DeleteMapping("/deleteLinkCandidatOpportunite/{idCandidat}/{idOpportunite}")
	void deleteLinkCandidatOpportunite(@PathVariable("idCandidat") UUID idCandidat,
			@PathVariable("idOpportunite") UUID idOpportunite);

	@Component
	public static class CandidatClientFallback {

		public List<Candidat> getAllCandidatsByOpportunite(@PathVariable("id") UUID id) {
			return null;
		}

		public void deleteLinkCandidatOpportunite(@PathVariable("idCandidat") UUID idCandidat,
				@PathVariable("idOpportunite") UUID idOpportunite) {
			System.out.println("deleteLinkCandidatOpportunite : " + idCandidat.toString() + idOpportunite.toString());
		}
	}
}
