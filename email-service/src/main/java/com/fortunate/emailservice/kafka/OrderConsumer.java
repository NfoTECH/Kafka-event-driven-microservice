package com.fortunate.emailservice.kafka;

import com.fortunate.basedomains.dto.OrderEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        LOG.info(String.format("#### -> Order event received in email service -> %s", event.toString()));

        // send an email to the customer
    }
}
