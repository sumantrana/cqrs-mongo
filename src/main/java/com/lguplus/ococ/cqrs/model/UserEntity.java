package com.lguplus.ococ.cqrs.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name="TB_USER")
public class UserEntity {
    @Id
    String userId;
    String userNm;
    String acv;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TB_ORG_USERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORG_CD"))
    Set<OrgEntity> orgEntitySet;

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("UserId: ").append(this.userId).append(",");
        stringBuilder.append("UserName: ").append(this.userNm).append(",");
        stringBuilder.append("Active: ").append(this.acv).append(",");
        stringBuilder.append("[ ");
        String orgString = orgEntitySet.stream().map(orgEntity -> "OrgName: " + orgEntity.getOrgNm() ).collect(Collectors.joining(","));
        stringBuilder.append(orgString);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}
