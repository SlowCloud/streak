package com.slowcloud.streak.streakBoard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.slowcloud.streak.streakBoard.model.dto.StreakBoard;

@RestController
@RequestMapping("/board")
public class StreakBoardController {
	
	@GetMapping
	public List<StreakBoard> getBoards() {
		return null;
	}
	
	@PostMapping
	public void createBoard(String name) {
		
	}
	
	@PutMapping
	public void modifyBoard(int id, String name) {
		
	}
	
	@DeleteMapping
	public void deleteBoard(int id) {
		
	}

}
