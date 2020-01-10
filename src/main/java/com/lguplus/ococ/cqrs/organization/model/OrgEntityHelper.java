package com.lguplus.ococ.cqrs.organization.model;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class OrgEntityHelper {

    public static OrgEntity fromDomain( Org org){
        
        OrgEntity orgEntity = OrgEntity.builder().build();
        orgEntity.setAcv(org.getAcv());
        orgEntity.setOrgCd(org.getOrgCd());
        orgEntity.setOrgNm(org.getOrgNm());
        orgEntity.setPrenOrgCd(org.getPrenOrgCd());
        if ( org.getUserSet() != null ) {
            orgEntity.setUserEntitySet(new HashSet<>());
            org.getUserSet().stream().forEach(userRecord -> {
                UserEntity userEntity = UserEntity.builder().build();
                userEntity.setAcv(userRecord.getAcv());
                userEntity.setUserId(userRecord.getUserId());
                userEntity.setUserNm(userRecord.getUserNm());
                orgEntity.getUserEntitySet().add(userEntity);
            });
        }

        return orgEntity;
    }

    public static Org toDomain( OrgEntity orgEntity){

        Org org = Org.builder().build();
        org.setAcv(orgEntity.getAcv());
        org.setOrgCd(orgEntity.getOrgCd());
        org.setOrgNm(orgEntity.getOrgNm());
        org.setPrenOrgCd(orgEntity.getPrenOrgCd());
        if ( orgEntity.getUserEntitySet() != null ) {
            org.setUserSet(new HashSet<>());
            orgEntity.getUserEntitySet().stream().forEach(userEntityRecord -> {
                User user = User.builder().build();
                user.setAcv(userEntityRecord.getAcv());
                user.setUserId(userEntityRecord.getUserId());
                user.setUserNm(userEntityRecord.getUserNm());
                org.getUserSet().add(user);
            });
        }

        return org;
    }

    public static List<OrgEntity> fromDomains(Collection<Org> orgList){
        return orgList.stream().map(OrgEntityHelper::fromDomain).collect(Collectors.toList());
    }

    public static List<Org> toDomains(Collection<OrgEntity> orgEntityList){
        return orgEntityList.stream().map(OrgEntityHelper::toDomain).collect(Collectors.toList());
    }
}
