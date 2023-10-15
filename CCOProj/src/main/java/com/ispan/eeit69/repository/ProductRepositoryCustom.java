package com.ispan.eeit69.repository;

import com.ispan.eeit69.model.Product;

public interface ProductRepositoryCustom {
	public boolean isPersist(Product product);
	
	void detach(Product product);

}
