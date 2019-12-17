/**
 * 
 */
package com.zendesk.pragma.parallel;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author jmoll
 *
 */

@Service
public class BasicSearchService {
	
	private static final Logger LOG = LoggerFactory.getLogger(BasicSearchService.class);

	public void search(List<String> inputData) {
		List<String> singleThreadedResults = new ArrayList<String>();
		inputData.forEach(item -> {
			if (item.equalsIgnoreCase(new String(Helper.getTargetArr()))) {
				singleThreadedResults.add(item);
			}
		});
		
		LOG.info("Completed finding all matches!");
	}
}
