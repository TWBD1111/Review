package com.ispan.eeit69.service.Impl;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ispan.eeit69.model.Category;
import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.repository.CategoryRepository;
import com.ispan.eeit69.repository.ProductRepository;
import com.ispan.eeit69.repository.ProductRepositoryCustom;
import com.ispan.eeit69.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductRepositoryCustom productRepositoryCustom;
	@Autowired
	CategoryRepository categoryRepository;
		
	public ProductServiceImpl(ProductRepository productRepository, ProductRepositoryCustom productRepositoryCustom) {
		super();
		this.productRepository = productRepository;
		this.productRepositoryCustom = productRepositoryCustom;
	}

	@Override
	public void resetProductTable() {
		throw new RuntimeException("本系統未提供此功能");		
	}

	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public boolean existsByProductId(Product product) {
		if (productRepositoryCustom.isPersist(product)) {
			productRepositoryCustom.detach(product);
		}
		Product emp = findByProductId(product.getProductId());
		return emp!= null;
	}

	@Override
	public void update(Product product) {
		productRepository.save(product);		
	}

	@Override
	public void deleteById(Integer id) {
		productRepository.deleteById(id);		
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findById(Integer id) {
		return productRepository.findById(id);
	}

	@Override
	public Product findByProductId(String productId) {
		Product emp = productRepository.findByProductId(productId);
		return emp;
	}

	@Override
	public boolean isPersist(Product product) {
		boolean ans = productRepository.existsById(product.getId());
		return ans;
	}

	@Override
	public void detach(Product product) {
		productRepositoryCustom.detach(product);	
	}

	@Override
	public List<Category> getCategoryList() {
		return null;
	}

	@Override
	public List<Label> getLabelList() {
		return null;
	}

	@Override
	public Page<Product> findAll(PageRequest of) {
		return productRepository.findAll(of);
	}
	public List<Product> getNonEmptyProducts(List<Product> products) {
	    List<Product> nonEmptyProducts = new ArrayList<>();
	    for (Product product : products) {
	        if (product != null) {
	            nonEmptyProducts.add(product);
	        }
	    }
	    return nonEmptyProducts;
	}

    @Override  
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByKeywordContaining(keyword);
    }
    
    @Override
	public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
	public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Override
   	public void saveProduct(Product product) {
           productRepository.save(product);
       }
    
    @Override
   	public void deleteProduct(Integer id) {
           productRepository.deleteById(id);
       }
    
    @Override
	public List<Product> getAllProductsWithCategoryAndLabels() {
        return productRepository.findAllWithCategoryAndLabels();
    }
    
    @Override
	public List<Product> getAllProductsWithCategory() {
        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            try {
                String imageDataUrl = clobToImageDataUrl(product.getPicture());
                product.setImage(imageDataUrl); // 設置image屬性為圖像數據URI
            } catch (Exception e) {
                // 處理異常
                e.printStackTrace();
            }

            Integer categoryId = product.getCategory().getId();
            Category category = categoryRepository.findById(categoryId).orElse(null);
            if (category != null) {
                product.getCategory().setCategoryName(category.getCategoryName());
            }
        }
        return products;
    }
    
  //圖片處理邏輯
    private String clobToImageDataUrl(Clob clob) throws SQLException, IOException {
        if (clob != null) {
            try (Reader reader = clob.getCharacterStream()) {
                if (reader != null) {
                    StringBuilder imageData = new StringBuilder();
                    char[] buffer = new char[1024];
                    int bytesRead;
                    while ((bytesRead = reader.read(buffer)) != -1) {
                        imageData.append(buffer, 0, bytesRead);
                    }
                    // 
                    return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageData.toString().getBytes());
                }
            }
        }
        return null;
    }
    
    //根據產品ID查詢產品價格
    @Override
	public BigDecimal  getProductPriceById(Integer id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            return product.getProductPrice();
        } else {
            return null;
        }
    }
    
    public Set<Label> getLabelsByProductId(String productId) {
    	Product product = productRepository.findByProductId(productId);

    	if (product != null) {
            return product.getLabels();
        }

        return Collections.emptySet();
    }
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
