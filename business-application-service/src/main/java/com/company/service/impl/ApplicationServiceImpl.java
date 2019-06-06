package com.company.service.impl;

import java.util.ArrayList;

import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeployedUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.service.ApplicationService;
import com.company.service.JbpmService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApplicationServiceImpl implements ApplicationService{

	 @Autowired
	    private ProcessService processService;
	 
	 @Autowired
	    private DeploymentService deploymentService;
	 
	 @Autowired
	    private JbpmService jbpmService;
	
	@Override
	public void pruebaTest() {
		Long returnCode = jbpmService.initProcess();
		log.info("Return code process instance id: " + returnCode);
	}

}
