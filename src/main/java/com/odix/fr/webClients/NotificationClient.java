package com.odix.fr.webClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.odix.fr.model.Candidat;
import com.odix.fr.model.Opportunite;
import com.odix.fr.model.Utilisateur;

@FeignClient("notification-MS")
public interface NotificationClient {

	@PostMapping("/generateSimpleNotification")
	List<Candidat> generateSimpleNotification
	(
			   @RequestBody String objetNotification, 
			   @RequestBody String corpsNotification,
			   @RequestBody List<Utilisateur> listeDestinatairesNotification, 
			   @RequestBody Utilisateur generateurNotification,
			   @RequestBody Utilisateur candidatNotification,
			   @RequestBody Utilisateur partenaireNotification,
			   @RequestBody Opportunite opportuniteNotification
	);
}
