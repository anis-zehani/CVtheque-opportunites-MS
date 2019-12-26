package com.odix.fr.model;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	
	public Candidat() {
		super();
	}
}
