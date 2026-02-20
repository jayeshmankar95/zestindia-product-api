package com.zestindia.productapi.mapper;

import com.zestindia.productapi.dto.*;
import com.zestindia.productapi.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ProductMapper {

	// ================= DTO → ENTITY =================
	public static Product toEntity(ProductRequestDTO dto) {

		Product product = new Product();
		product.setProductName(dto.getProductName());
		product.setCreatedBy(dto.getCreatedBy());

		if (dto.getItems() != null) {
			List<Item> items = dto.getItems().stream().map(itemDTO -> {
				Item item = new Item();
				item.setQuantity(itemDTO.getQuantity());
				item.setProduct(product); // VERY IMPORTANT
				return item;
			}).collect(Collectors.toList());

			product.setItems(items);
		}

		return product;
	}

	// ================= ENTITY → DTO =================
	public static ProductResponseDTO toDTO(Product product) {

		ProductResponseDTO dto = new ProductResponseDTO();

		dto.setId(product.getId());
		dto.setProductName(product.getProductName());
		dto.setCreatedBy(product.getCreatedBy());
		dto.setCreatedOn(product.getCreatedOn());
		dto.setModifiedBy(product.getModifiedBy());
		dto.setModifiedOn(product.getModifiedOn());

		if (product.getItems() != null) {
			List<ItemDTO> itemDTOs = product.getItems().stream().map(item -> {
				ItemDTO itemDTO = new ItemDTO();
				itemDTO.setId(item.getId());
				itemDTO.setQuantity(item.getQuantity());
				return itemDTO;
			}).collect(Collectors.toList());

			dto.setItems(itemDTOs);
		}

		return dto;
	}
}
