package com.company.kafka_example;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Component
public class ReceiverBatch {
    public static final int COUNT = 10;

    private CountDownLatch latch = new CountDownLatch(COUNT);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(id = "batch-listener", topics = "${kafka.topic.batch}")
    public void receive(List<String> data,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        System.out.println("start of batch receive");
        for (int i = 0; i < data.size(); i++) {
            System.out.println("received message = " + data.get(i) +
                    " with partition-offset = " + partitions.get(i) + "-" + offsets.get(i));
            // handle message

            latch.countDown();
        }
        System.out.println("end of batch receive");
    }
}
