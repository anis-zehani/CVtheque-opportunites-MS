package com.odix.fr.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Certification implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1998921502004317093L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private UUID id;

    @NotEmpty(message="Odix - certification ne peut pas être vide")
    @Column(unique=true)
	private String nomCertification;
	
	// Pour regrouper les certifications par IdUtilisateur
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	private Utilisateur utilisateur;
    
    public Certification() {
		super();
	}

	public Certification(UUID id,
			@NotEmpty(message = "Odix - certification ne peut pas être vide") String nomCertification) {
		super();
		this.id = id;
		this.nomCertification = nomCertification;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNomCertification() {
		return nomCertification;
	}

	public void setNomCertification(String nomCertification) {
		this.nomCertification = nomCertification;
	}
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
