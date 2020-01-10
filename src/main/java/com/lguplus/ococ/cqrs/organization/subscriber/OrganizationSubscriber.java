package com.lguplus.ococ.cqrs.organization.subscriber;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;
import com.lguplus.ococ.cqrs.organization.service.OrgService;
import com.lguplus.ococ.cqrs.organization.service.UserService;
import com.lguplus.ococ.cqrs.organization.vo.OrgVO;
import com.lguplus.ococ.cqrs.organization.vo.OrganizationVO;
import com.lguplus.ococ.cqrs.organization.vo.OrganizationVOHelper;
import com.lguplus.ococ.cqrs.organization.vo.UserVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EnableKafka
@Component
public class OrganizationSubscriber {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationSubscriber.class.getName());

    private OrganizationVO organizationVO;
    private OrgService orgService;
    private UserService userService;

    public OrganizationSubscriber(OrgService orgService, UserService userService) {
        this.orgService = orgService;
        this.userService = userService;
    }

    @KafkaListener(topics = "${organization.topic}")
    public void listen(ConsumerRecord<String, OrganizationVO> record) {
        System.out.println("Received Message: " + record.value());
        if ( record.value().getVoType().equals(OrganizationVOHelper.VO_TYPE_ORG)) {
            Org org = OrgVO.toDomain(record.value().getOrgVO());
            try {
                orgService.createOrUpdateOrg(org);
            } catch ( Exception exp ){
                LOGGER.warn("Exception while processing org update. ", exp);
            }
        } else if ( record.value().getVoType().equals(OrganizationVOHelper.VO_TYPE_USER)) {
            User user = UserVO.toDomain(record.value().getUserVO());
            try {
                List<Org> orgList = orgService.getOrgsForUser(user);
                user.setOrgSet(new HashSet<>(orgList));
                userService.createOrUpdateUser(user);
            } catch ( Exception exp ){
                LOGGER.warn("Exception while processing user update. ", exp);
            }
        }
    }

}
