/**
 * 
 */
package com.zendesk.pragma.concurrent;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jmoll
 *
 */

@Service
public class SingleTaskService {
	private static final Logger LOG = LoggerFactory.getLogger(ConcurrentTaskService.class);
	
	public void doWork(int min, int max) throws InterruptedException {
		LOG.info("Executing thread name - ${}", Thread.currentThread().getName());
		Thread.sleep(RandomUtils.nextInt(min, max));
		//Math.pow(2, 50);
	}
}
