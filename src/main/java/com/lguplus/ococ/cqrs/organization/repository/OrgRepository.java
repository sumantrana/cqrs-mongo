package com.lguplus.ococ.cqrs.organization.repository;

import com.lguplus.ococ.cqrs.organization.model.OrgEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

public interface OrgRepository extends MongoRepository<OrgEntity, String> {

    public List<OrgEntity> findByOrgCdIn(Set<String> orgCodes);
}
