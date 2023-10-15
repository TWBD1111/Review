package com.ispan.eeit69.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ispan.eeit69.model.Label;

public interface LabelRepository extends JpaRepository<Label, Integer>{
	Label findByLabelId(String labelId);
	
	Label findByLabelName(String labelName);
	
	@Override
	boolean existsById(Integer id) ;
}
