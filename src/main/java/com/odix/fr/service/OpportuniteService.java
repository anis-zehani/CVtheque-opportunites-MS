package com.odix.fr.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.odix.fr.model.Opportunite;


public interface OpportuniteService {
	
	public List<Opportunite> getAllOpportunites(String etat);
	
	public List<Opportunite> getAllOpportunitesFavorisForUtilisateur(UUID idUtilisateur);
	
	public List<Opportunite> getAllOpportunitesPublicAndPrivateByPartenaire(String etat, UUID idPartenaire);
	
	public List<Opportunite> getAllOpportunitesByPartenaire(UUID idPartenaire);
	
	public List<Opportunite> getAllOpportunitesByTechnologie(UUID idTechnologie);
	
	public List<Opportunite> getAllOpportunitesByListTechnologies(ArrayList<UUID> listTechnologies);
	
	public List<Opportunite> getAllOpportunitesByCertification(UUID idCertification);
	
	public Opportunite getOpportunite(UUID id);
	
	public Long getCountOpportunites();
	
	
	public Opportunite addOpportunite(Opportunite opportunite);
	
	public Opportunite editOpportunite(Opportunite opportunite);
	
	public Opportunite editEtatOpportunite(Opportunite opportunite);
	
	
	public void deleteOpportunite(UUID idOpportunite);
	
	public void deleteLinkOpportuniteTechnologie(UUID idOpportunite, UUID idTechnologie);
	
	public void deleteLinkOpportuniteCertification(UUID idOpportunite, UUID idCertification);
	
	public void updateLinkOpportunitePartenaire(UUID idOpportunite);

}
