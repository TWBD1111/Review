package com.ispan.eeit69.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

//	@Query("SELECT COUNT(DISTINCT b.boardId) FROM Board b")
//	Integer countBoardNo();


}
