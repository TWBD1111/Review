package com.ispan.eeit69.repository;

import com.ispan.eeit69.model.Category;

public interface CategoryRepositoryCustom {
	public boolean isPersist(Category category);
	
	void detach(Category category);

}
