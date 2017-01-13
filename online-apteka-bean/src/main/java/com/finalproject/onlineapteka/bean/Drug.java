package com.finalproject.onlineapteka.bean;

import java.math.BigDecimal;

public class Drug {
	private Integer id;
	private String drugName;
	private String description;
	private String dosage;
	private String instruction;
	private BigDecimal price;
	private Float quantity;
	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	private Integer needRecipe;
	private String imagePath;
	private Integer categoryId;
	private Integer recipeId;

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getId() {
		return id;
	}

	public Integer getNeedRecipe() {
		return needRecipe;
	}

	public void setNeedRecipe(Integer needRecipe) {
		this.needRecipe = needRecipe;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}
}
