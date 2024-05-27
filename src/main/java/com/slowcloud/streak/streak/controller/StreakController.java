package com.slowcloud.streak.streak.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/streak")
public class StreakController {
	
	@PostMapping("/{id}")
	void streakUp(@PathVariable("id") int streakBoardId) {
		
	}

}
