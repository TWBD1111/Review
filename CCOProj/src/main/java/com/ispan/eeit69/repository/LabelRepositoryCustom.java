package com.ispan.eeit69.repository;

import com.ispan.eeit69.model.Label;

public interface LabelRepositoryCustom {
	public boolean isPersist(Label label);
	
	void detach(Label label);
}
