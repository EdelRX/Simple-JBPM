package com.company.service;

import lombok.extern.slf4j.Slf4j;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeployedUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Slf4j
@Service
public class JbpmService {

    @Autowired
    private ProcessService processService;

    @Autowired
    private DeploymentService deploymentService;
    
    public Long initProcess() {
    	ArrayList<DeployedUnit> deployedUnits = new ArrayList<>(deploymentService.getDeployedUnits());
        String containerId = deployedUnits.get(0).getDeploymentUnit().getIdentifier();
        log.info("deployed unit: " + containerId);
		log.info("Starting simple test process");
		Long processInstanceId = this.processService.startProcess(containerId, "test.test");
		return processInstanceId;
    }

    public void completeWorkItem(Long processInstanceId, Long workItemId, Map<String, Object> result) {

        ArrayList<DeployedUnit> deployedUnits = new ArrayList<>(deploymentService.getDeployedUnits());
        String containerId = deployedUnits.get(0).getDeploymentUnit().getIdentifier();
        log.info("deployed unit: " + containerId);
        this.processService.completeWorkItem(containerId, processInstanceId, workItemId, result);
        log.info("Completed WorkItem: " + workItemId);
    }
}