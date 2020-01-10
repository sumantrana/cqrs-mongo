package com.lguplus.ococ.cqrs.organization.testproducer;

import com.lguplus.ococ.cqrs.organization.domain.Org;
import com.lguplus.ococ.cqrs.organization.domain.User;
import com.lguplus.ococ.cqrs.organization.service.OrgService;
import com.lguplus.ococ.cqrs.organization.service.UserService;
import com.lguplus.ococ.cqrs.organization.vo.OrganizationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Api(value = "OrganizationVO Management System")
public class TestProducerController {

    private TestProducer testProducer;
    private OrgService orgService;
    private UserService userService;

    public TestProducerController(TestProducer testProducer, OrgService orgService, UserService userService){
        this.testProducer = testProducer;
        this.orgService = orgService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/org")
    @ApiOperation(value="Push Organization change to Kafka", response = ResponseEntity.class )
    public ResponseEntity postOrganizationUpdate(@ApiParam(value = "OrganizationVO object", required = true) @RequestBody OrganizationVO organizationVO){
        try{
            System.out.println("Received object: " + organizationVO);
            testProducer.sendOrganizationVOMessage(organizationVO);
        } catch ( Exception exp ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/org")
    @ApiOperation(value="Get all orgs", response = Org.class)
    public List<Org> getOrgs(){
        return orgService.getAllOrgs();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user")
    @ApiOperation(value="Get all users", response = User.class)
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

}
