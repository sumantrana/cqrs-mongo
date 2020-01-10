package com.lguplus.ococ.cqrs.organization.service;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;
import com.lguplus.ococ.cqrs.organization.model.OrgEntity;
import com.lguplus.ococ.cqrs.organization.model.OrgEntityHelper;
import com.lguplus.ococ.cqrs.organization.repository.OrgRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrgService {

    private OrgRepository orgRepository;

    public OrgService(OrgRepository orgRepository){
        this.orgRepository = orgRepository;
    }

    public void createOrUpdateOrg(Org org) throws Exception {
        Optional<OrgEntity> orgEntityOptional = orgRepository.findById(org.getOrgCd());
        OrgEntity newEntity = OrgEntityHelper.fromDomain(org);
        if ( orgEntityOptional.isPresent()){
            newEntity.setOrgCd(orgEntityOptional.get().getOrgCd());
            orgRepository.save(newEntity);
        } else {
            orgRepository.save(newEntity);
        }
    }

    public List<Org> getAllOrgs(){
        return OrgEntityHelper.toDomains(orgRepository.findAll());
    }

    public List<Org> getOrgsForUser(User user){
        Set<Org> orgSet = user.getOrgSet();
        Set<String> orgCodeSet = orgSet.stream().map(Org::getOrgCd).collect(Collectors.toSet());
        return OrgEntityHelper.toDomains(orgRepository.findByOrgCdIn(orgCodeSet));
    }
}
