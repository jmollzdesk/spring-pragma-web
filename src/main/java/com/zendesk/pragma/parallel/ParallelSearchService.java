/**
 * 
 */
package com.zendesk.pragma.parallel;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jmoll
 *
 */

@Service
public class ParallelSearchService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ParallelSearchService.class);

	public void search(List<String> inputData) {
		List<String> parallelResults = inputData.parallelStream().filter(item -> item.equalsIgnoreCase("SomeComparison")).collect(Collectors.toList());
		
		LOG.info("Completed finding all matches!");
	}
}
