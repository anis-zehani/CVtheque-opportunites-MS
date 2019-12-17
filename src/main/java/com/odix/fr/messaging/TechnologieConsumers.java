package com.odix.fr.messaging;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.odix.fr.model.Technologie;
import com.odix.fr.repository.TechnologieRepository;
import com.odix.fr.service.TechnologieService;

@Service
public class TechnologieConsumers {
	
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	TechnologieService technologieService;
	
	@Autowired
	TechnologieRepository technologieRepository;
	
	
    @KafkaListener(topics = "add-technologie-topic")
    public void addTechnologieConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> addTechnologieConsumer -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Technologie technologie = OBJECT_MAPPER.readValue(message, Technologie.class);
        	
            this.technologieService.addTechnologie(technologie);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @KafkaListener(topics = "edit-technologie-topic")
    public void editTechnologieConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> editTechnologieConsumer -> %s", message +"\n"));

        try{
        	OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        	Technologie technologie = OBJECT_MAPPER.readValue(message, Technologie.class);
        	
            this.technologieService.editTechnologie(technologie);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @KafkaListener(topics = "delete-technologie-topic")
    public void deleteTechnologieConsumer(String message) throws IOException {
        System.out.print(String.format("#### -> deleteTechnologieConsumer -> %s", message +"\n"));

        try{
            this.technologieService.deleteTechnologie(UUID.fromString(message));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
