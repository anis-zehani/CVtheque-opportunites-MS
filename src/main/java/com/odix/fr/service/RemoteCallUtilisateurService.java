package com.odix.fr.service;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.odix.fr.model.Utilisateur;

@FeignClient(name="gateway")
public interface RemoteCallUtilisateurService {
	
	@RequestMapping(method=RequestMethod.GET, value="/getUtilisateurByRole/{role}")
	public Utilisateur getUtilisateurByRole(String role);
	
	@RequestMapping(method=RequestMethod.GET, value="/getUtilisateurById/{id}")
	public Utilisateur getUtilisateurById(UUID id);

}
