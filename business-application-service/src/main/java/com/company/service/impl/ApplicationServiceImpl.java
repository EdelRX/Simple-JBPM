package com.company.service.impl;

import java.util.ArrayList;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeployedUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService{

	 @Autowired
	    private ProcessService processService;
	 
	 @Autowired
	    private DeploymentService deploymentService;
	
	@Override
	public void pruebaTest() {
		ArrayList<DeployedUnit> deployedUnits = new ArrayList<>(deploymentService.getDeployedUnits());
        String containerId = deployedUnits.get(0).getDeploymentUnit().getIdentifier();
        log.info("deployed unit: " + containerId);
		log.info("Starting simple test process");
		Long processInstanceId = this.processService.startProcess(containerId, "test.test");
		log.info("ProcessInstanceId: " + processInstanceId);
	}

}
