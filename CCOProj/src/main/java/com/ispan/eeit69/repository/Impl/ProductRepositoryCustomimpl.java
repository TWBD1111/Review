package com.ispan.eeit69.repository.Impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.repository.ProductRepositoryCustom;

@Repository
public class ProductRepositoryCustomimpl implements ProductRepositoryCustom {
	
	@PersistenceContext
	EntityManager  entityManager;

	@Override
	public boolean isPersist(Product product) {
		// 判斷參數employee是否為entityManager控管的永續物件
		boolean ans = entityManager.contains(product);
		if(ans)
			return true;
		else
			return false;
	}
	@Override
	public void detach(Product product) {
		entityManager.detach(product);
	}

}
