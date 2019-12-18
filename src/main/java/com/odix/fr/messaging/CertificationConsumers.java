package com.odix.fr.messaging;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Certification;
import com.odix.fr.service.CertificationService;

@Service
public class CertificationConsumers {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	CertificationService certificationService;

	
    @KafkaListener(topics = "add-certification-topic")
    public void addCertificationConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> addCertificationConsumer -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Certification certification = OBJECT_MAPPER.readValue(message, Certification.class);
        	
            this.certificationService.addCertification(certification);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @KafkaListener(topics = "edit-certification-topic")
    public void editCertificationConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> editCertificationConsumer -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Certification certification = OBJECT_MAPPER.readValue(message, Certification.class);
        	
            this.certificationService.editCertification(certification);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @KafkaListener(topics = "delete-certification-topic")
    public void deleteCertificationConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> deleteCertificationConsumer -> %s", message +"\n"));

        try{
            this.certificationService.deleteCertification(UUID.fromString(message));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
