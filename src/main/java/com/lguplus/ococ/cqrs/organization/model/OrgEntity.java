package com.lguplus.ococ.cqrs.organization.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="TB_ORG")
public class OrgEntity {
    @Id
    String orgCd;
    String orgNm;
    String acv;
    String prenOrgCd;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orgEntitySet")
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
