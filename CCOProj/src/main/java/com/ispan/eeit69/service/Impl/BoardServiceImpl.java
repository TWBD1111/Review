package com.ispan.eeit69.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Board;
import com.ispan.eeit69.repository.BoardRepository;
import com.ispan.eeit69.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardRepository br;
	
	@Override
	public List<Board> findAll() {
		return br.findAll();
	}

//	@Override
//	public List<Integer> countBoardNo() {
//		Integer bn =  br.countBoardNo();
//		List<Integer> boardNo = new ArrayList<>();
//		boardNo.add(bn);
//		return boardNo;
//	}

	

	

}
