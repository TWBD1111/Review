package com.ispan.eeit69.controller.bgsys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ispan.eeit69.model.Board;
import com.ispan.eeit69.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	BoardService bs;
		
	@GetMapping("/board")
	public String findBoardData(Model model){
		 List<Board> bd = bs.findAll();
		 model.addAttribute("boardData", bd);
		 return "board";
	}
	
//	@GetMapping("/boardNo")
//	public @ResponseBody List<Integer> countBoardNo() {
//		return bs.countBoardNo();
//	}
	
//	@GetMapping("/boardData")
//	public @ResponseBody List<Board> findBD() {
//		return bs.findAll();
//		}
	
//	@GetMapping("/board")
//	public String findBoardPage() {
//		return "board";
//	}

}
