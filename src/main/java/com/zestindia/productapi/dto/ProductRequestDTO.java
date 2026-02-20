package com.zestindia.productapi.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProductRequestDTO {

	private String productName;
	private String createdBy;
	private List<ItemDTO> items;
}
