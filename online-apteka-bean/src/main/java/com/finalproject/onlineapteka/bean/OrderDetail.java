package com.finalproject.onlineapteka.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((CustomOrderId == null) ? 0 : CustomOrderId.hashCode());
		result = prime * result + ((drugId == null) ? 0 : drugId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
		result = prime
				* result
				+ ((totalPriceOfGood == null) ? 0 : totalPriceOfGood.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetail other = (OrderDetail) obj;
		if (CustomOrderId == null) {
			if (other.CustomOrderId != null)
				return false;
		} else if (!CustomOrderId.equals(other.CustomOrderId))
			return false;
		if (drugId == null) {
			if (other.drugId != null)
				return false;
		} else if (!drugId.equals(other.drugId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (quantity == null) {
			if (other.quantity != null)
				return false;
		} else if (!quantity.equals(other.quantity))
			return false;
		if (totalPriceOfGood == null) {
			if (other.totalPriceOfGood != null)
				return false;
		} else if (!totalPriceOfGood.equals(other.totalPriceOfGood))
			return false;
		return true;
	}
	
	
}	
