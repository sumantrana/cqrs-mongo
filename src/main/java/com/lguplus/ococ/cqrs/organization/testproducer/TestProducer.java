package com.lguplus.ococ.cqrs.organization.testproducer;

import com.lguplus.ococ.cqrs.organization.vo.OrganizationVO;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class TestProducer {

    @Value("${organization.topic}")
    public String organizationTopic;

    private KafkaTemplate<String, OrganizationVO> kafkaTemplate;

    private BlockingQueue<OrganizationVO> failedMessageQueue = new ArrayBlockingQueue<OrganizationVO>(5);

    TestProducer(KafkaTemplate<String, OrganizationVO> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrganizationVOMessage(OrganizationVO organizationVO){
        ProducerRecord<String, OrganizationVO> producerRecord = new ProducerRecord<String, OrganizationVO>(organizationTopic, organizationVO);

        kafkaTemplate.send(producerRecord);
        //kafkaTemplate.flush();
    }    
}
