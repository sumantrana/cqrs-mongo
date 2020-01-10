package com.lguplus.ococ.cqrs.testproducer;

import com.lguplus.ococ.cqrs.vo.OrganizationVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/org")
@Api(value = "OrganizationVO Management System")
public class TestProducerController {

    private TestProducer testProducer;

    public TestProducerController(TestProducer testProducer){
        this.testProducer = testProducer;
    }

    @PostMapping
    @ApiOperation(value="Push Organization change to Kafka", response = ResponseEntity.class)
    public ResponseEntity postOrganizationUpdate(@RequestBody OrganizationVO organizationVO){
        try{
            testProducer.sendOrganizationVOMessage(organizationVO);
        } catch ( Exception exp ){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
