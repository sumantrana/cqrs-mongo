package com.lguplus.ococ.cqrs.subscriber;

import com.lguplus.ococ.cqrs.vo.OrganizationVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class OrganizationSubscriber {

    private OrganizationVO organizationVO;

    @KafkaListener(topics = "${organization.topic}")
    public void listen(ConsumerRecord<String, OrganizationVO> record) {
        System.out.println("Received Message: " + record.value());
        //this.organizationVO = record.value();
    }

}
