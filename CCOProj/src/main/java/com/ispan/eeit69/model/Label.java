package com.ispan.eeit69.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Label_Table")
public class Label implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//標籤ID
    private Integer Id;
	//標籤編號
	private String labelId;
	//標籤名稱
    private String labelName;
    //標籤價格
    private BigDecimal labelPrice;
     
	public Label() {
		super();
	}

	@Override
	public String toString() {
		return "Label [Id=" + Id + ", labelId=" + labelId + ", labelName=" + labelName + ", labelPrice=" + labelPrice
				+ "]";
	}

	public Label(Integer id, String labelId, String labelName, BigDecimal labelPrice) {
		super();
		Id = id;
		this.labelId = labelId;
		this.labelName = labelName;
		this.labelPrice = labelPrice;
	}


	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getLabelPrice() {
		return labelPrice;
	}

	public void setLabelPrice(BigDecimal labelPrice) {
		this.labelPrice = labelPrice;
	}
	
}
