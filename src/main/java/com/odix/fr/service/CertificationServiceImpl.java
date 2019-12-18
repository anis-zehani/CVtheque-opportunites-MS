package com.odix.fr.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.odix.fr.model.Certification;
import com.odix.fr.repository.CertificationRepository;


@Service
public class CertificationServiceImpl implements CertificationService{
	
	private final CertificationRepository certificationRepository;

	CertificationServiceImpl(CertificationRepository certificationRepository) {
		super();
		this.certificationRepository = certificationRepository;
	}

	//Ajouter une certification
	public Certification addCertification(Certification certification) 
	{
		return certificationRepository.save(certification);
	}
	
	//Modifier une certification
	public Certification editCertification(Certification certification) 
	{
		if(certificationRepository.existsById(certification.getId()))
		{
			return certificationRepository.save(certification);
		}
		return null;
	}
	
	//Supprimer une certification
	public boolean deleteCertification(UUID id) 
	{
		if(certificationRepository.existsById(id))
		{
			try 
			{
				certificationRepository.deleteById(id);
				return true;
			}
			catch(Exception e) 
			{
				System.out.print("Erreur durant deleteCertification :"+e);
			}
		}
		return false;
	}

}