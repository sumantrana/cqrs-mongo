package com.lguplus.ococ.cqrs.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Org {
    @Id
    String orgCd;
    String orgNm;
    String acv;
    String prenOrgCd;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "orgSet")
    Set<User> userSet;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("OrgCode: ").append(this.orgCd).append(",");
        stringBuilder.append("OrgName: ").append(this.orgNm).append(",");
        stringBuilder.append("Active: ").append(this.acv).append(",");
        stringBuilder.append("ParentOrg: ").append(this.prenOrgCd).append(",");
        stringBuilder.append("[ ");
        String userString = userSet.stream().map( usr -> "UserName: " + usr.userNm ).collect(Collectors.joining(","));
        stringBuilder.append(userString);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
