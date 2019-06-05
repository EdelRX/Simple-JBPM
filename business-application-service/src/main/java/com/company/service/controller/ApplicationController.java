package com.company.service.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.service.ApplicationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ApplicationController {
	
	@Autowired
	ApplicationService applicationService;
	
	 @GetMapping("/simpleProcess")
	    public void simpleTestRequest() throws IOException {
		 log.info("Starting ApplcaitionController: Simple test request");
		 applicationService.pruebaTest();
	    }
	
}
