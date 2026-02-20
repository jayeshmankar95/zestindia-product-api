package com.zestindia.productapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zestindia.productapi.dto.ApiResponse;
import com.zestindia.productapi.dto.ItemDTO;
import com.zestindia.productapi.dto.ProductRequestDTO;
import com.zestindia.productapi.dto.ProductResponseDTO;
import com.zestindia.productapi.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO dto) {

		return ResponseEntity.ok(productService.createProduct(dto));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<ProductResponseDTO>>> getAllProducts(
			@PageableDefault(size = 10) Pageable pageable) {

		Page<ProductResponseDTO> products = productService.getAllProducts(pageable);

		return ResponseEntity.ok(new ApiResponse<>(true, "Products fetched successfully", products));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>> getProductById(@PathVariable Integer id) {

		return ResponseEntity
				.ok(new ApiResponse<>(true, "Product fetched successfully", productService.getProductById(id)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Integer id,
			@RequestBody ProductRequestDTO dto) {

		return ResponseEntity.ok(productService.updateProduct(id, dto));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {

		productService.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}

	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	@GetMapping("/{id}/items")
	public ResponseEntity<List<ItemDTO>> getItemsByProductId(@PathVariable Integer id) {

		return ResponseEntity.ok(productService.getItemsByProductId(id));
	}
}
