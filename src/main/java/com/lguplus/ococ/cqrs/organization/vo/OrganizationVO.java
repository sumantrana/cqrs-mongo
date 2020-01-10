package com.lguplus.ococ.cqrs.organization.vo;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrganizationVO {

    private String voType;
    private UserVO userVO;
    private OrgVO orgVO;

    public String toString(){
        return voType;
    }

}
