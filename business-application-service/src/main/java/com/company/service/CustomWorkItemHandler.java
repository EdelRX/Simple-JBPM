package com.company.service;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component("CustomWorkItemHandler")
public class CustomWorkItemHandler implements WorkItemHandler{
	
	@Autowired
	JbpmService jbpmService;

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		log.info("Entering WorkItem execution method");
		Map<String, Object> map = new HashMap<>();
		jbpmService.completeWorkItem(workItem.getProcessInstanceId(),workItem.getId(), map);
		log.info("Exiting WorkItem execution method");
	}

	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		// TODO Auto-generated method stub
		
	}
	
}
