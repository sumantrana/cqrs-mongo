package com.lguplus.ococ.cqrs.organization.model;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityHelper {

    public static UserEntity fromDomain( User user){
        
        UserEntity userEntity = UserEntity.builder().build();
        userEntity.setAcv(user.getAcv());
        userEntity.setUserId(user.getUserId());
        userEntity.setUserNm(user.getUserNm());
        userEntity.setOrgEntitySet(new HashSet<>());
        if ( user.getOrgSet() != null ) {
            user.getOrgSet().stream().forEach(org -> {
                OrgEntity orgEntity = OrgEntity.builder().build();
                orgEntity.setAcv(org.getAcv());
                orgEntity.setOrgCd(org.getOrgCd());
                orgEntity.setOrgNm(org.getOrgNm());
                orgEntity.setPrenOrgCd(org.getPrenOrgCd());
                userEntity.getOrgEntitySet().add(orgEntity);
            });
        }

        return userEntity;
    }

    public static User toDomain( UserEntity userEntity){

        User user = User.builder().build();
        user.setAcv(userEntity.getAcv());
        user.setUserNm(userEntity.getUserNm());
        user.setUserId(userEntity.getUserId());
        user.setOrgSet(new HashSet<>());
        if ( userEntity.getOrgEntitySet() != null ) {
            userEntity.getOrgEntitySet().stream().forEach(orgEntity -> {
                Org org = Org.builder().build();
                org.setAcv(orgEntity.getAcv());
                org.setOrgCd(orgEntity.getOrgCd());
                org.setOrgNm(orgEntity.getOrgNm());
                org.setPrenOrgCd(orgEntity.getPrenOrgCd());
                user.getOrgSet().add(org);
            });
        }

        return user;
    }

    public static List<UserEntity> fromDomains(List<User> userList){
        return userList.stream().map(UserEntityHelper::fromDomain).collect(Collectors.toList());
    }

    public static List<User> toDomains(List<UserEntity> userEntityList){
        return userEntityList.stream().map(UserEntityHelper::toDomain).collect(Collectors.toList());
    }
}
