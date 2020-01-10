package com.lguplus.ococ.cqrs.organization.service;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;
import com.lguplus.ococ.cqrs.organization.model.UserEntity;
import com.lguplus.ococ.cqrs.organization.model.UserEntityHelper;
import com.lguplus.ococ.cqrs.organization.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return UserEntityHelper.toDomains(userRepository.findAll());
    }

    public void createOrUpdateUser(User user) throws Exception {
        Optional<UserEntity> userEntityOptional = userRepository.findById(user.getUserId());
        UserEntity newEntity = UserEntityHelper.fromDomain(user);
        if ( userEntityOptional.isPresent()){
            newEntity.setUserId(userEntityOptional.get().getUserId());
            userRepository.save(newEntity);
        } else {
            userRepository.save(newEntity);
        }
    }


}
