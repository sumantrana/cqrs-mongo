package com.lguplus.ococ.cqrs.subscriber;

import com.lguplus.ococ.cqrs.vo.OrganizationVO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrganizationSubscriberConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> consumerProps(){
        Map<String, Object> consumerProps = new HashMap<>();

        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return consumerProps;
    }

    @Bean
    public ConsumerFactory<String, OrganizationVO> consumerFactory(){

        return new DefaultKafkaConsumerFactory<String, OrganizationVO>(
                consumerProps(), new StringDeserializer(), new JsonDeserializer<>(OrganizationVO.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrganizationVO> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, OrganizationVO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setAckDiscarded(true);

        return factory;
    }

}
