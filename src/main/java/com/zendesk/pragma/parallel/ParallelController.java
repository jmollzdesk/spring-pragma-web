/**
 * 
 */
package com.zendesk.pragma.parallel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jmoll
 *
 */

@RestController
@RequestMapping("/parallel")
public class ParallelController {

	private static final String SMALL_FILE = "small.txt";
	
	@Autowired
	private ParallelSearchService parallelSearchService;
	
	@Autowired
	private BasicSearchService basicSearchService;
	
	@Autowired
	private FileService fileService;
	
	// TODO: Loop through a large list and compare results between for loop and parallel stream

	@GetMapping("/basic")
	@ResponseStatus(HttpStatus.OK)
	public String basic() {
		basicSearchService.search(getInput());
		
		return "invoked basic" + System.currentTimeMillis();
	}
	
	@GetMapping("/stream")
	@ResponseStatus(HttpStatus.OK)
	public String stream() {
		parallelSearchService.search(getInput());
		
		return "invoked stream" + System.currentTimeMillis();
	}
	
	private List<String> getInput() {
		return fileService.getFileData(SMALL_FILE);
	}
}