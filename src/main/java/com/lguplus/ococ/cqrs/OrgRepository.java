package com.lguplus.ococ.cqrs;

import com.lguplus.ococ.cqrs.model.OrgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrgRepository extends JpaRepository<OrgEntity, String> {

}
