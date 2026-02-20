package com.zestindia.productapi.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ProductResponseDTO {

	private Integer id;
	private String productName;
	private String createdBy;
	private LocalDateTime createdOn;
	private String modifiedBy;
	private LocalDateTime modifiedOn;
	private List<ItemDTO> items;
}
