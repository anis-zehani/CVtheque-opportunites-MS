package com.odix.fr.webClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.odix.fr.model.Candidat;
import com.odix.fr.model.Opportunite;
import com.odix.fr.model.Utilisateur;

@FeignClient("notifications-MS")
public interface NotificationClient {

	@PostMapping("/generateSimpleNotification")
	List<Candidat> generateSimpleNotification
	(
			   @PathVariable ("objetNotification") String objetNotification, 
			   @PathVariable ("corpsNotification") String corpsNotification,
			   @PathVariable ("listeDestinatairesNotification") List<Utilisateur> listeDestinatairesNotification, 
			   @PathVariable ("generateurNotification") Utilisateur generateurNotification,
			   @PathVariable ("candidatNotification") Utilisateur candidatNotification,
			   @PathVariable ("partenaireNotification") Utilisateur partenaireNotification,
			   @PathVariable ("opportuniteNotification") Opportunite opportuniteNotification
	);
}
