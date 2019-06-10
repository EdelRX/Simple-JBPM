package com.company.service;

import lombok.extern.slf4j.Slf4j;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.model.DeployedUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Service
public class JbpmService {

    private Lock processServicelock = new ReentrantLock();

    @Autowired
    private ProcessService processService;

    @Autowired
    private DeploymentService deploymentService;
    
    public Long initProcess() {
        Long processInstanceId = 0L;
        this.processServicelock.lock();
        try {
            ArrayList<DeployedUnit> deployedUnits = new ArrayList<>(deploymentService.getDeployedUnits());
            String containerId = deployedUnits.get(0).getDeploymentUnit().getIdentifier();
            log.info("Starting simple test process in [{}]", containerId);
            
            boolean mustFail = (Math.random() > 0.5)? true:false;
            log.info("mustFail: " + mustFail);
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("mustFail", mustFail);
            
            processInstanceId = this.processService.startProcess(containerId, "src.main.resources.SimpleErrorProcess",params);
        } finally {
            this.processServicelock.unlock();
        }
        log.info("Started simple test process with id [{}]", processInstanceId);
        return processInstanceId;
    }

    public void completeWorkItem(Long processInstanceId, Long workItemId, Map<String, Object> result) {

        this.processServicelock.lock();
        try {
            ArrayList<DeployedUnit> deployedUnits = new ArrayList<>(deploymentService.getDeployedUnits());
            String containerId = deployedUnits.get(0).getDeploymentUnit().getIdentifier();
            log.info("completing work item [{}] on [{}] of [{}]", workItemId, processInstanceId, containerId);
            this.processService.completeWorkItem(containerId, processInstanceId, workItemId, result);
        } finally {
            this.processServicelock.unlock();
        }
        log.info("Completed WorkItem: " + workItemId);
    }
}