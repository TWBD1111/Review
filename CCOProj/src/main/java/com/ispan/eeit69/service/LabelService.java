package com.ispan.eeit69.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import com.ispan.eeit69.model.Label;

public interface LabelService {
	void resetLabelTable();
	
	void save(Label label);
	boolean existsByLabelId(Label label);
	boolean isPersist(Label label);	
	void detach(Label label);
	
	void update(Label label);
	
	void deleteById(Integer id);
	
	List<Label> findAll();
	Optional<Label> findById(Integer id);	
	Label findByLabelId(String labelId);
	
	BigDecimal getLabelPriceById(Integer id);
	
}
