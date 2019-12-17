package com.odix.fr.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class OpportunitesFavoris implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2747508434173978827L;

	@Id
	@GeneratedValue
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private UUID id;

	@Column
	private UUID idUtilisateur;
	
	@Column
	private UUID idOpportunite;
	
	@Column
	private String titreOpportunite;

	public UUID getIdUtilisateur() {
		return idUtilisateur;
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setIdUtilisateur(UUID idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public UUID getIdOpportunite() {
		return idOpportunite;
	}

	public void setIdOpportunite(UUID idOpportunite) {
		this.idOpportunite = idOpportunite;
	}

	public String getTitreOpportunite() {
		return titreOpportunite;
	}

	public void setTitreOpportunite(String titreOpportunite) {
		this.titreOpportunite = titreOpportunite;
	}
}
