package com.menu.model;

public class MenuVO implements java.io.Serializable {

	private Integer itemId;
	private Integer categoryId;
	private String itemName;
	private String itemDesc;
	private Double price;
	private String imageUrl;
	private Integer sortOrder;

	public MenuVO() {
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public Double getPrice() {
		return price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
}
