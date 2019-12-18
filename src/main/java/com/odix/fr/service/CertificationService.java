package com.odix.fr.service;

import java.util.UUID;

import com.odix.fr.model.Certification;

public interface CertificationService {
	
	public Certification addCertification(Certification certification);
	
	public Certification editCertification(Certification certification);
	
	public boolean deleteCertification(UUID id);

}
