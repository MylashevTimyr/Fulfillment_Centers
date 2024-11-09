package com.example.service;

import com.example.model.FulfillmentCenter;
import com.example.model.Product;
import com.example.repository.FulfillmentCenterRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final FulfillmentCenterRepository fulfillmentCenterRepository;

	@Autowired
	public ProductService(ProductRepository productRepository, FulfillmentCenterRepository fulfillmentCenterRepository) {
		this.productRepository = productRepository;
		this.fulfillmentCenterRepository = fulfillmentCenterRepository;
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product moveProduct(Long productId, Long targetFulfillmentCenterId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		FulfillmentCenter targetCenter = fulfillmentCenterRepository.findById(targetFulfillmentCenterId)
				.orElseThrow(() -> new RuntimeException("Fulfillment center not found"));
		product.setFulfillmentCenter(targetCenter);
		return productRepository.save(product);
	}

	public Product updateProductQuantity(Long productId, int newQuantity) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		product.setQuantity(newQuantity);
		return productRepository.save(product);
	}

	public Product updateProductStatus(Long productId, String newStatus) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found"));
		product.setStatus(newStatus);
		return productRepository.save(product);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> getProductsByStatus(String status) {
		return productRepository.findByStatus(status);
	}

	public Integer getTotalValueOfSellableProducts() {
		return productRepository.findByStatus("Sellable").stream()
				.mapToInt(Product::getValue)
				.sum();
	}

	public Integer getTotalValueByFulfillmentCenter(Long fulfillmentCenterId) {
		return productRepository.findByFulfillmentCenterId(fulfillmentCenterId).stream()
				.mapToInt(Product::getValue)
				.sum();
	}
}