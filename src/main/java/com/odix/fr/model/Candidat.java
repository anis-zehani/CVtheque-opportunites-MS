package com.odix.fr.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
	
@Data
@Entity
@DiscriminatorValue(value="ROLE_CANDIDAT")
public class Candidat extends Utilisateur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1004341819482868284L;

	/**Les champs du candidat, hérités de la classe utilisateur :
	 * 
	 * id
	 * identite
	 * telephone
	 * email
	 * poste_occupe
	 * description_detaillee
	 * urlPhoto
	 * entreprise : @ManyToOne
	 * 
	 ***************
	 */

	// Pour regrouper les candidats par IdUtilisateur : qui a inséré ce candidat (pour le moment c'est l'Administrateur)
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
	
	@ManyToMany
	@JoinTable(name = "candidat_opportunite",
	joinColumns = { @JoinColumn(name = "id_candidat") },
	inverseJoinColumns = { @JoinColumn(name = "id_opportunite") })
	private List<Opportunite> listeOpportunites;
	
	public Candidat() {
		super();
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public List<Opportunite> getListeOpportunites() {
		return listeOpportunites;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public void setListeOpportunites(List<Opportunite> listeOpportunites) {
		this.listeOpportunites = listeOpportunites;
	}

}
