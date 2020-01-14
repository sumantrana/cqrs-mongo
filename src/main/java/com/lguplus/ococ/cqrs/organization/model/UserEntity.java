package com.lguplus.ococ.cqrs.organization.model;

import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "User")
public class UserEntity {
    @Id
    String userId;
    String userNm;
    String acv;

	@DBRef(lazy = true)
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
