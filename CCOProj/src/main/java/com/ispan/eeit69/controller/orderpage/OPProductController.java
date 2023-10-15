package com.ispan.eeit69.controller.orderpage;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ispan.eeit69.model.Label;
import com.ispan.eeit69.model.Product;
import com.ispan.eeit69.repository.ProductRepository;
import com.ispan.eeit69.service.ProductService;

import java.util.Set;

@Controller
public class OPProductController {
	@Autowired
	ProductRepository productRepository;
	@Autowired
	ProductService productService;

    @GetMapping("/opProduct/{productID}")
    public String getProductDetails(@PathVariable String productID, Model model) {
    	Product product = productRepository.findByProductId(productID);
    	Set<Label> labels = product.getLabels();
        model.addAttribute("ProductLabels", labels);
    	model.addAttribute("ProductDetails", productRepository.findByProductId(productID));
        return "product"; // 返回顯示產品詳細資訊的視圖名稱
    }
}