package com.lguplus.ococ.cqrs.organization.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    String userId;
    String userNm;
    String acv;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TB_ORG_USERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORG_CD"))
    Set<Org> orgSet;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UserId: ").append(this.userId).append(",");
        stringBuilder.append("UserName: ").append(this.userNm).append(",");
        stringBuilder.append("Active: ").append(this.acv).append(",");
        stringBuilder.append("[ ");
        String orgString = orgSet.stream().map( org -> "OrgName: " + org.getOrgNm() ).collect(Collectors.joining(","));
        stringBuilder.append(orgString);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
