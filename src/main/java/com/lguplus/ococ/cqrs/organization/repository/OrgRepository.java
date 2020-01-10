package com.lguplus.ococ.cqrs.organization.repository;

import com.lguplus.ococ.cqrs.organization.model.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrgRepository extends JpaRepository<OrgEntity, String> {

    public List<OrgEntity> findByOrgCdIn(Set<String> orgCodes);
}
