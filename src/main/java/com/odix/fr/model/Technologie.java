package com.odix.fr.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Technologie implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3422261716301379660L;

	@Id
	//Attention : ne doit pas être Generated car il doit avoir le même UUID que le MS Maître
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private UUID id;

    @NotEmpty(message="Odix - technologie ne peut pas être vide")
    @Column(unique=true)
    private String nomTechnologie;
	
	// Pour regrouper les technologies par IdUtilisateur : qui a inséré cette technologie
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
    
	public Technologie() {
		super();
	}


	public Technologie(UUID id, @NotEmpty(message = "Odix - technologie ne peut pas être vide") String nomTechnologie) {
		super();
		this.id = id;
		this.nomTechnologie = nomTechnologie;
	}


	public UUID getId() {
		return id;
	}

	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getNomTechnologie() {
		return nomTechnologie;
	}

	public void setNomTechnologie(String nomTechnologie) {
		this.nomTechnologie = nomTechnologie;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
}
