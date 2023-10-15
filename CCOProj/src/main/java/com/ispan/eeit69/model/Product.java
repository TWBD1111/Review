package com.ispan.eeit69.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ispan.eeit69.utils.SystemService;

@Entity
@Table(name="Product_Table")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/** 商品ID (0)*/
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	/** 商品編號 (1)*/
	private String productId;
    /** 商品名稱 (2)*/
    private String productName;
    
    /** 商品圖片 (3) */
    @JsonIgnore
    private Clob picture;    
    @Transient
    private String image;
    @Transient
    private String fileName;
    
    /** 類別名稱  (4)*/
//    Lazy（懶加載）：僅在需要時加載相關實體。當獲取主實體時，相關實體只有在需要訪問時才會被加載
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("hibernateLazyInitializer")
    private Category category;
    
    /** 商品介紹  (5)*/
    private String productDescription;
    /** 商品價格 (6)*/
    private BigDecimal productPrice;    
    /** 商品份量 (7)*/
    private String productPortion;
    /** 商品庫存 (8)*/
    private Integer productStock;    
    /** 商品上架時間 (9)*/
    private Timestamp created_at;
    
    /** 商品標籤 (10)*/
//    Eager（急加載）：立即加載相關實體。當獲取主實體時，相關實體也會被立即加載。
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "product_labels",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "label_id")
    )
    private Set<Label> labels = new HashSet<>();

	public Product() {
		super();
	}
	public Product(Integer id, String productId, String productName, Clob picture, String image, String fileName,
			Category category, String productDescription, BigDecimal productPrice, String productPortion,
			Integer productStock, Timestamp created_at, Set<Label> labels) {
		super();
		this.id = id;
		this.productId = productId;
		this.productName = productName;
		this.picture = picture;
		this.image = image;
		this.fileName = fileName;
		this.category = category;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productPortion = productPortion;
		this.productStock = productStock;
		this.created_at = created_at;
		this.labels = labels;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", productId=" + productId + ", productName=" + productName + ", picture="
				+ picture + ", image=" + image + ", fileName=" + fileName + ", category=" + category
				+ ", productDescription=" + productDescription + ", productPrice=" + productPrice + ", productPortion="
				+ productPortion + ", productStock=" + productStock + ", created_at=" + created_at + ", labels="
				+ labels + "]";
	}
	public String getDataUri() throws Exception {
		return SystemService.clobToString(picture);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Clob getPicture() {
		return picture;
	}

	public void setPicture(Clob picture) {
		this.picture = picture;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductPortion() {
		return productPortion;
	}

	public void setProductPortion(String productPortion) {
		this.productPortion = productPortion;
	}

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}