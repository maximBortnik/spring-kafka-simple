package com.company.kafka_example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        System.out.println("sending payload = " + payload + " to topic = " + topic);
        kafkaTemplate.send(topic, payload);
    }

}
