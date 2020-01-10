package com.lguplus.ococ.cqrs.organization.repository;

import com.lguplus.ococ.cqrs.organization.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
