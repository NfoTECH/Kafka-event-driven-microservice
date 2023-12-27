package com.fortunate.stockservice.kafka;

import com.fortunate.stockservice.OrderEventRepository;
import com.fortunate.stockservice.entity.OrderEventEntity;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fortunate.basedomains.dto.OrderEvent;

import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(OrderConsumer.class);
    private final OrderEventRepository orderEventRepository;

    @KafkaListener(
            topics = "${kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(OrderEvent event) {
        LOG.info(String.format("#### -> Order event received in stock service -> %s", event.toString()));

        // save the order event into the database
        OrderEventEntity orderEventEntity = new OrderEventEntity();
        orderEventEntity.setMessage(event.getMessage());
        orderEventEntity.setStatus(event.getStatus());
        orderEventRepository.save(orderEventEntity);
    }
}
