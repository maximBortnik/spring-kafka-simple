package com.company.kafka_example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;
@Component
public class Receiver {

    // For testing convenience, we added a CountDownLatch.
    // This allows the POJO to signal that a message is received.
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.helloworld}")
    public void receive(String payload) {
        System.out.println("received payload = " + payload);
        latch.countDown();
    }
}
