package com.odix.fr.webClients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.odix.fr.model.Utilisateur;

@FeignClient("gateway-MS")
public interface UtilisateurClient {

	@GetMapping("/api/utilisateur/getUtilisateurByRole/{role}")
	Utilisateur getUtilisateurByRole(@PathVariable("role") String role);
	
	@GetMapping("/api/utilisateur/getUtilisateurById/{id}")
	Utilisateur getUtilisateurById(@PathVariable("id") UUID id);
}
