package com.zendesk.pragma.parallel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jmoll
 *
 */

@RestController("parallel")
public class ParallelController {

    @Autowired
    private ParallelSearchService parallelSearchService;

    @Autowired
    private BasicSearchService basicSearchService;

    // TODO: Loop through a large list and compare results between for loop and parallel stream

	@GetMapping("/basic")
	@ResponseStatus(HttpStatus.OK)
	public void basic() {
		basicSearchService.search(getInput());
	}
	
	@GetMapping("/stream")
	@ResponseStatus(HttpStatus.OK)
	public void stream() {
		parallelSearchService.search(getInput());
	}
	
	private List<String> getInput() {
		// I think we can use the chrome inspect to compare the time
		// can change the size value to coordinate the data size
		return Helper.getLargeList(5000000);
	}
}