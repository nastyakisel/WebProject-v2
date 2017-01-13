package com.finalproject.onlineapteka.bean;

import java.math.BigDecimal;

public class OrderDetail {
	private Integer id;
	private Float quantity;
	private BigDecimal totalPriceOfGood;
	private Integer drugId;
	private Integer CustomOrderId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Float getQuantity() {
		return quantity;
	}
	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalPriceOfGood() {
		return totalPriceOfGood;
	}
	public void setTotalPriceOfGood(BigDecimal totalPriceOfGood) {
		this.totalPriceOfGood = totalPriceOfGood;
	}
	public Integer getDrugId() {
		return drugId;
	}
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	public Integer getCustomOrderId() {
		return CustomOrderId;
	}
	public void setCustomOrderId(Integer customOrderId) {
		CustomOrderId = customOrderId;
	}
	
	
}	
