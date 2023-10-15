package com.ispan.eeit69.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.model.Product;

public interface ProductService {
	void resetProductTable();                        //重製
	
	//Interface CrudRepository<T,ID>
	void save(Product product);                      //create
	boolean existsByProductId(Product product);  //檢查資料是否存在
	boolean isPersist(Product product);	
	void detach(Product product);
	
	void update(Product product);                   //update
	
	void deleteById(Integer id);                    //delete
	
	List<Product> findAll();                          //read	
	Optional<Product>findById(Integer id);        //read
	Product findByProductId(String productId);
	
	List<Category> getCategoryList();
	List<Label> getLabelList();

	Page<Product> findAll(PageRequest of);
	List<Product> getNonEmptyProducts(List<Product> products);

	List<Product> searchProducts(String keyword);
	
	List<Product> getAllProducts();

	Product getProductById(Integer id);

	void saveProduct(Product product);

	void deleteProduct(Integer id);

	List<Product> getAllProductsWithCategoryAndLabels();

	List<Product> getAllProductsWithCategory();
	//根據產品ID查詢產品價格
	BigDecimal getProductPriceById(Integer id);
	public List<Category> getAllCategory();
	public Set<Label> getLabelsByProductId(String productId);
	

}
