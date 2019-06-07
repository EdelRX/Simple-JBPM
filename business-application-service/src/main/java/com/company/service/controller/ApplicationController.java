package com.company.service.controller;

import java.io.IOException;

import com.company.service.JbpmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApplicationController {
	
	@Autowired
	JbpmService jbpmService;
	
	 @GetMapping("/simpleProcess")
	    public void simpleTestRequest() throws IOException {
		 log.info("Starting ApplicationController: Simple test request");
		 jbpmService.initProcess();
	    }
	
}
