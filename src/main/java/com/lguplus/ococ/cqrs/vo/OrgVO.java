package com.lguplus.ococ.cqrs.vo;

import com.lguplus.ococ.cqrs.domain.Org;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class OrgVO {

    String orgCd;
    String orgNm;
    String acv;
    String preOrgCd;

    public static Org toDomain(OrgVO orgVO){
        Org org = Org.builder().build();
        org.setAcv(orgVO.getAcv());
        org.setOrgCd(orgVO.getOrgCd());
        org.setOrgNm(orgVO.getOrgNm());
        org.setPrenOrgCd(orgVO.getPreOrgCd());
        return org;
    }

}
