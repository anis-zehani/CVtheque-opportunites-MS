package com.odix.fr.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.odix.fr.model.Certification;

@Repository
public interface CertificationRepository extends JpaRepository<Certification, UUID> {

}
