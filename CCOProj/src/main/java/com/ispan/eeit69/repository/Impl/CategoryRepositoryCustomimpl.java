package com.ispan.eeit69.repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.repository.CategoryRepositoryCustom;

@Repository
public class CategoryRepositoryCustomimpl implements CategoryRepositoryCustom {
	
	@PersistenceContext
	EntityManager  entityManager;
	
	@Override
	public boolean isPersist(Category category) {
		boolean ans = entityManager.contains(category);
		if(ans)
			return true;
		else
			return false;
	}

	@Override
	public void detach(Category category) {
		entityManager.detach(category);
	}
}
