/**
 * 
 */
package com.zendesk.pragma.concurrent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jmoll
 *
 */
@RestController("/concurrency")
public class ConcurrentTestController {

	private static final int ROUNDS = 100;
	private static final int MIN = 1;
	private static final int MAX = 500;
	
	@Autowired
	private ConcurrentTaskService concurrentTaskService;
	
	@Autowired
	private SingleTaskService singleTaskService;
	
	@GetMapping("/concurrent")
	@ResponseStatus(HttpStatus.OK)
	public String invokeConcurrent() throws InterruptedException {
		
		for (int i=0; i < ROUNDS; i++) {
			concurrentTaskService.doWork(MIN, MAX);
		}
		
		return "invoked concurrency" + System.currentTimeMillis();
	}
	
	@GetMapping("/single")
	@ResponseStatus(HttpStatus.OK)
	public String invokeSingle() throws InterruptedException {
		
		for (int i=0; i < ROUNDS; i++) {
			singleTaskService.doWork(MIN, MAX);
		}
		
		return "invoked single" + System.currentTimeMillis();
	}
}
