package com.ispan.eeit69.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ispan.eeit69.model.Product;

//Spring Data JPA將自動生成用於基本CRUD操作的方法，開發者還可以在接口中定義自己的查詢方法
public interface ProductRepository extends JpaRepository<Product, Integer>{
	Product findByProductId(String productId) ;
	Product findByProductName(String productName);
	Product findByProductPrice(BigDecimal productPrcie);
	

	@Override
	boolean existsById(Integer id) ;
		
	@Query("SELECT p FROM Product p WHERE p.productName LIKE CONCAT('%', :keyword, '%') "
			+ "OR p.productId LIKE CONCAT('%', :keyword, '%')"
			+ "OR p.category.categoryName LIKE CONCAT('%', :keyword, '%')")
//			+ "OR p.labels LIKE CONCAT('%', :keyword, '%')")
	List<Product> findByKeywordContaining(@Param("keyword") String keyword);

	@Query("SELECT p FROM Product p JOIN FETCH p.category JOIN FETCH p.labels")
	List<Product> findAllWithCategoryAndLabels();

	
	
}


//org.springframework.data.jpa.repository.JpaRepository：這是Spring Data JPA提供的通用接口，它封裝了對數據庫的常見操作，包括查詢、插入、更新和刪除。
//com.model.Product：這是與數據庫表格映射的實體類型。在這個例子中，它表示了一個產品（Product）實體，該實體與數據庫中的一個表相關聯。
//ProductDao接口：這是一個自定義的數據訪問層接口，它繼承了JpaRepository接口，並指定了兩個泛型參數：Product（實體類型）和Integer（主鍵類型）。
//這意味著ProductDao接口將繼承JpaRepository的所有方法，包括CRUD操作。
//Product findByProductId(String productId)方法：這是一個自定義查詢方法，根據產品ID（productId）查找產品。
//Spring Data JPA會根據方法名稱自動生成SQL查詢，不需要額外的實現。
//existsById(Integer id)方法：這是JpaRepository接口中的一個方法，默認實現返回false。開發者可以根據需要重寫這個方法。
//總之，這個代碼示例展示了如何使用Spring Data JPA創建一個數據訪問層接口（ProductDao），這個接口可以輕鬆執行數據庫操作，
//包括查詢和CRUD操作，同時也支持自定義查詢方法。






