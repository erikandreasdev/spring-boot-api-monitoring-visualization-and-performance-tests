package com.erikandreas.metricsdemo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Value("${kafka.topic}")
    private String topic;

    @Value("${kafka.group-id}")
    private String groupId;

    @KafkaListener(topics = "#{@environment.getProperty('kafka.topic')}", groupId = "#{@environment.getProperty('kafka.group-id')}")
    public void listen(ConsumerRecord<String, String> record) {
        System.out.println("Received Message: " + record.value());
    }
}