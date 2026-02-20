package com.zestindia.productapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zestindia.productapi.dto.ItemDTO;
import com.zestindia.productapi.dto.ProductRequestDTO;
import com.zestindia.productapi.dto.ProductResponseDTO;

public interface ProductService {

	Page<ProductResponseDTO> getAllProducts(Pageable pageable);

	ProductResponseDTO createProduct(ProductRequestDTO dto);

	ProductResponseDTO getProductById(Integer id);

	ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto);

	void deleteProduct(Integer id);

	List<ItemDTO> getItemsByProductId(Integer id);

}
