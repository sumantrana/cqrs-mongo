package com.lguplus.ococ.cqrs;

import com.lguplus.ococ.cqrs.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {

}
