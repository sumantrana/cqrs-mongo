package com.lguplus.ococ.cqrs.organization.model;


import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Document(collection = "Org")
public class OrgEntity {

    @Id
    String orgCd;
    String orgNm;
    String acv;
    String prenOrgCd;

    @DBRef(lazy = true)
    Set<UserEntity> userEntitySet;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("OrgCode: ").append(this.orgCd).append(",");
        stringBuilder.append("OrgName: ").append(this.orgNm).append(",");
        stringBuilder.append("Active: ").append(this.acv).append(",");
        stringBuilder.append("ParentOrg: ").append(this.prenOrgCd).append(",");
        stringBuilder.append("[ ");
        String userString = userEntitySet.stream().map( usr -> "UserName: " + usr.userNm ).collect(Collectors.joining(","));
        stringBuilder.append(userString);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
