package com.odix.fr.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.odix.fr.model.Etat;
import com.odix.fr.model.Opportunite;
import com.odix.fr.model.Utilisateur;
import com.odix.fr.model.Visibilite;
import com.odix.fr.repository.OpportuniteRepository;

// import com.cvtheque.org.model.Candidat;


@Service
public class OpportuniteServiceImpl implements OpportuniteService {
	
	private final OpportuniteRepository opportuniteRepository;
	
	// Je remplace ça par un Remot Call avec Feign
	@Autowired
	// CandidatService candidatService;
	RemoteCallCandidatService loadBalancerCandidatService;
	
	/*@Autowired
	NotificationService notificationService;*/
	
	@Autowired
	// UtilisateurService utilisateurService;
	RemoteCallUtilisateurService loadBalancerUtilisateurService;
	
	
	OpportuniteServiceImpl(OpportuniteRepository opportuniteRepository) {
		super();
		this.opportuniteRepository = opportuniteRepository;
	}

	public List<Opportunite> getAllOpportunites(String etatOpportunite) {
		
		if(etatOpportunite.equals("True"))
		{
			return opportuniteRepository.findByEtatOpportunite(Etat.True);
		}
		else 
		{
			return opportuniteRepository.findByEtatOpportunite(Etat.False);
		}

	}
	
	/**
	 * Liste des opportunités favories pour un Utilisateur (Administrateur/Partenaire)
	 * @param idUtilisateur
	 * @return List<Opportunite> 
	 */
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(UUID idUtilisateur) {
		
		List<Opportunite> liste = opportuniteRepository.getAllOpportunitesFavorisForUtilisateur(idUtilisateur);
		
		return liste;
	}
	
	/**Datagrid Opportunité avec un Profil Partenaire
	 * Retourne les Opportunités Publique + les Opportunités Privée du Partenaire connecté
	 * @param idPartenaire
	 */
	public List<Opportunite> getAllOpportunitesPublicAndPrivateByPartenaire(String etat, UUID idPartenaire) {
		
		List<Opportunite> liste = opportuniteRepository.findAllOpportunitesPublicAndPrivateByPartenaire(etat, idPartenaire);
		
		return liste;
	}
	
	//INNER JOIN : JPQL : La liste des Opportunités pour un Partenaire
	public List<Opportunite> getAllOpportunitesByPartenaire(UUID idPartenaire) {
		return opportuniteRepository.findAllOpportunitesByPartenaire(idPartenaire);
	}
	
	//INNER JOIN : JPQL : La liste des Opportunités pour une Technologie
	public List<Opportunite> getAllOpportunitesByTechnologie(UUID idTechnologie) {
		return opportuniteRepository.findAllOpportunitesByTechnologie(idTechnologie);
	}
	
	//La liste des opportunites qui ont une Technologie au moins dans la liste fournie
	public List<Opportunite> getAllOpportunitesByListTechnologies(ArrayList<UUID> listTechnologies){
		
		return opportuniteRepository.findAllOpportunitesByListTechnologies(listTechnologies);
	}
	
	//INNER JOIN : JPQL : La liste des Opportunités pour une Certification
	public List<Opportunite> getAllOpportunitesByCertification(UUID idCertification){
		return opportuniteRepository.findAllOpportunitesByCertification(idCertification);
	}
	
	
	//Supprimer le lien entre une opportunité et une technologie
	public void deleteLinkOpportuniteTechnologie(UUID idOpportunite, UUID idTechnologie) {
			   opportuniteRepository.deleteLinkOpportuniteTechnologie(idOpportunite, idTechnologie);
	}
	
	//Supprimer le lien entre une opportunité et une certification
	public void deleteLinkOpportuniteCertification(UUID idOpportunite, UUID idCertification) {
			   opportuniteRepository.deleteLinkOpportuniteCertification(idOpportunite, idCertification);
	}
	
	//Update le lien entre une opportunité et un partenaire : met responsableOpportunite à NULL
	public void updateLinkOpportunitePartenaire(UUID idOpportunite) {
			   opportuniteRepository.updateLinkOpportunitePartenaire(idOpportunite);
	}
	
	
	public Opportunite getOpportunite(UUID id) {
		return opportuniteRepository.findOneById(id);
	}

	//Ajouter une opportunité
	public Opportunite addOpportunite(Opportunite opportunite) {
		
			opportunite.setEtatOpportunite(Etat.True);

			opportunite.setDateAjout(LocalDate.now());
			
			//On met l'image par défaut à toutes les opportunités : elle s'affiche si l'opportunité n'est liée à aucun partenaire
			opportunite.setUrlPhotoOpportunite("");
			
			if(opportunite.getResponsableOpportunite().getId() == null)
			{
				opportunite.setResponsableOpportunite(null);
			}
			else 
			{
				// Génération d'une Notification Destinée à l'Administrateur : uniquement si Opportunité est ajoutée par un Partenaire
				// Utilisateur admin = utilisateurService.getUtilisateurByRole("ROLE_ADMINISTRATEUR");
				// Feign
				Utilisateur admin = loadBalancerUtilisateurService.getUtilisateurByRole("ROLE_ADMINISTRATEUR");
				List<Utilisateur> listeDestinatairesNotification = new ArrayList<Utilisateur>();
				listeDestinatairesNotification.add(admin);
				
				// Notification générée par le système (ou bien disons par l'Admin) vers lui même (l'Admin)
				/*notificationService.
				generateSimpleNotification(Consts.objetMsgNotificationAjoutOpportunite, 
										   Consts.corpsMsgNotificationAjoutOpportunite, 
										   listeDestinatairesNotification, */
										   /*utilisateurService.getUtilisateurById(opportunite.getResponsableOpportunite().getId()),*/
										   /*loadBalancerUtilisateurService.getUtilisateurById(opportunite.getResponsableOpportunite().getId()),
										   null,
										   null,
										   null);*/
			}

			Opportunite addedOpportunite =  opportuniteRepository.save(opportunite);
			
			return addedOpportunite;
	}

	//Modifier une opportunité
	public Opportunite editOpportunite(Opportunite opportunite) {
		
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			
			Opportunite opportuniteToUpdate = opportuniteRepository.getOne(opportunite.getId());
			
			opportuniteToUpdate.setTitreOpportunite(opportunite.getTitreOpportunite());
			opportuniteToUpdate.setDescriptionOpportunite(opportunite.getDescriptionOpportunite());
			opportuniteToUpdate.setDateAjout(opportunite.getDateAjout());
			opportuniteToUpdate.setDateDemarrageSouhaitee(opportunite.getDateDemarrageSouhaitee());
			opportuniteToUpdate.setTjmOpportunite(opportunite.getTjmOpportunite());
			opportuniteToUpdate.setVisibiliteOpportunite(opportunite.getVisibiliteOpportunite());
			
			
			//On met l'image par défaut à toutes les opportunités : elle s'affiche si l'opportunité n'est liée à aucun partenaire
			opportuniteToUpdate.setUrlPhotoOpportunite("");
			
			if(opportunite.getResponsableOpportunite().getId() == null)
			{
				opportuniteToUpdate.setResponsableOpportunite(null);
			}
			else
			{
				opportuniteToUpdate.setResponsableOpportunite(opportunite.getResponsableOpportunite());
			}

			/*
			 * listeTechnologies : @ManyToMany 
			 */
			if(opportunite.getListeTechnologies() != null)
			{
				opportuniteToUpdate.setListeTechnologies(opportunite.getListeTechnologies());
			}
			
			/*
			 * listeCertifications : @ManyToMany 
			 */
			if(opportunite.getListeCertifications() != null)
			{
				opportuniteToUpdate.setListeCertifications(opportunite.getListeCertifications());
			}
			
			Opportunite editedOpportunite =  opportuniteRepository.save(opportuniteToUpdate);
			
			// On emet la Notification seulement s'il y a un Reponsable de l'Opportuntié
			if(editedOpportunite.getResponsableOpportunite().getId() != null) {
				// Génération d'une Notification Destinée à l'Administrateur
				// Utilisateur admin = utilisateurService.getUtilisateurByRole("ROLE_ADMINISTRATEUR");
				// Feign
				Utilisateur admin = loadBalancerUtilisateurService.getUtilisateurByRole("ROLE_ADMINISTRATEUR");
				List<Utilisateur> listeDestinatairesNotification = new ArrayList<Utilisateur>();
				listeDestinatairesNotification.add(admin);
				
				// Notification générée par le système (ou bien disons par l'Admin) vers lui même (l'Admin)
				/*notificationService.
				generateSimpleNotification(Consts.objetMsgNotificationModificationOpportunite, 
										   Consts.corpsMsgNotificationModificationOpportunite, 
										   listeDestinatairesNotification, */
										   /*utilisateurService.getUtilisateurById(opportunite.getResponsableOpportunite().getId()),*/
										   /*loadBalancerUtilisateurService.getUtilisateurById(opportunite.getResponsableOpportunite().getId()),
										   null,
										   null,
										   editedOpportunite);*/
			}
			return editedOpportunite;
		}
		
		return null;
	}
	
	//Modifier l'état d'une Opportunité : Active/Inactive
	public Opportunite editEtatOpportunite(Opportunite opportunite) {
		
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			Opportunite opportuniteToUpdate = opportuniteRepository.getOne(opportunite.getId());
			
			if(opportuniteToUpdate.getEtatOpportunite().equals(Etat.True))
			{
				opportuniteToUpdate.setEtatOpportunite(Etat.False);
			}
			else 
			{
				opportuniteToUpdate.setEtatOpportunite(Etat.True);
			}
			
			return opportuniteRepository.save(opportuniteToUpdate);
		}
		return null;
	}
	
	//Modifier l'état d'une Opportunité : Active/Inactive
	public Opportunite editVisibiliteOpportunite(Opportunite opportunite) {
			
		if(opportuniteRepository.existsById(opportunite.getId()))
		{
			Opportunite opportuniteToUpdate = opportuniteRepository.getOne(opportunite.getId());
				
			if(opportuniteToUpdate.getVisibiliteOpportunite().equals(Visibilite.Public))
			{
				opportuniteToUpdate.setVisibiliteOpportunite(Visibilite.Private);
			}
			else 
			{
				opportuniteToUpdate.setVisibiliteOpportunite(Visibilite.Public);
			}
				
			return opportuniteRepository.save(opportuniteToUpdate);
		}
		return null;
	}

	//Supprimer une opportunité
	public void deleteOpportunite(UUID idOpportunite) {
		
		if(opportuniteRepository.existsById(idOpportunite))
		{
			//On récupére les Candidats liés à cette Opportunités 
			// List<Candidat> listeCandidats = candidatService.getAllCandidatsByOpportunite(idOpportunite);
			// Feign 
			List<Utilisateur> listeCandidats = loadBalancerCandidatService.getAllCandidatsByOpportunite(idOpportunite);
			
			//On supprime les liens clés étrangères dans la table jointure
			if(!listeCandidats.isEmpty())
			{
				for(int i=0;i<listeCandidats.size();i++)
				{
					// candidatService.deleteLinkCandidatOpportunite(listeCandidats.get(i).getId(), idOpportunite);
					// Feign 
					loadBalancerCandidatService.deleteLinkCandidatOpportunite(listeCandidats.get(i).getId(), idOpportunite);
				}
			}
			
			opportuniteRepository.deleteById(idOpportunite);
		}
	}
}
