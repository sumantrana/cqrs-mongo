package com.lguplus.ococ.cqrs.organization.repository;

import com.lguplus.ococ.cqrs.organization.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {

}
