package com.zestindia.productapi.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zestindia.productapi.dto.ItemDTO;
import com.zestindia.productapi.dto.ProductRequestDTO;
import com.zestindia.productapi.dto.ProductResponseDTO;
import com.zestindia.productapi.entity.Item;
import com.zestindia.productapi.entity.Product;
import com.zestindia.productapi.exception.ResourceNotFoundException;
import com.zestindia.productapi.mapper.ProductMapper;
import com.zestindia.productapi.repository.ProductRepository;
import com.zestindia.productapi.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponseDTO createProduct(ProductRequestDTO dto) {

		Product product = ProductMapper.toEntity(dto);
		product.setCreatedOn(LocalDateTime.now());

		Product saved = productRepository.save(product);

		return ProductMapper.toDTO(saved);
	}

	@Override
	public ProductResponseDTO getProductById(Integer id) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + id));
		return ProductMapper.toDTO(product);
	}

	@Override
	public Page<ProductResponseDTO> getAllProducts(Pageable pageable) {

		Pageable safePageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by("id").ascending());

		return productRepository.findAll(safePageable).map(ProductMapper::toDTO);
	}

	@Override
	public ProductResponseDTO updateProduct(Integer id, ProductRequestDTO dto) {

		Product existingProduct = productRepository.findById(id)
				.orElseThrow(()  -> new ResourceNotFoundException(
	                    "Product not found with ID: " + id));

		existingProduct.setProductName(dto.getProductName());
		existingProduct.setModifiedBy(dto.getCreatedBy()); // using same field for simplicity
		existingProduct.setModifiedOn(LocalDateTime.now());

		existingProduct.getItems().clear();

		if (dto.getItems() != null) {

			List<Item> updatedItems = dto.getItems().stream().map(itemDTO -> {
				Item item = new Item();
				item.setQuantity(itemDTO.getQuantity());
				item.setProduct(existingProduct); // VERY IMPORTANT
				return item;
			}).toList();

			existingProduct.getItems().addAll(updatedItems);
		}

		Product savedProduct = productRepository.save(existingProduct);

		return ProductMapper.toDTO(savedProduct);
	}

	@Override
	public void deleteProduct(Integer id) {

		if (!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Product not found with ID: " + id);
		}

		productRepository.deleteById(id);
	}

	@Override
	public List<ItemDTO> getItemsByProductId(Integer id) {

		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		return product.getItems().stream().map(item -> {
			ItemDTO dto = new ItemDTO();
			dto.setId(item.getId());
			dto.setQuantity(item.getQuantity());
			return dto;
		}).toList();
	}

}