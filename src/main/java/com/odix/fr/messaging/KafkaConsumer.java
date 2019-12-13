package com.odix.fr.messaging;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
    @KafkaListener(topics = "advice-topic", groupId = "group_id1")
    public void consume(String message) throws IOException {
        System.out.print(String.format("#### -> Consumed message : opportunite-service-> %s", message +"\n"));
    }

    @KafkaListener(topics = "administrateurAdded-topic", groupId = "group_id2")
    public void consumeAdministrateurAdded(String message) throws IOException {
        System.out.print(String.format("#### -> administrateurAdded Consumed message : opportunite-service -> %s", message +"\n"));
    }
}
