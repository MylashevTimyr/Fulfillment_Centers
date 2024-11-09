package com.example.service;

import com.example.model.FulfillmentCenter;
import com.example.model.Product;
import com.example.repository.FulfillmentCenterRepository;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FulfillmentCenterService {
	private final FulfillmentCenterRepository fulfillmentCenterRepository;
	private final ProductRepository productRepository;

	public FulfillmentCenterService(FulfillmentCenterRepository fulfillmentCenterRepository, ProductRepository productRepository) {
		this.fulfillmentCenterRepository = fulfillmentCenterRepository;
		this.productRepository = productRepository;
	}

	public FulfillmentCenter addFulfillmentCenter(FulfillmentCenter fulfillmentCenter) {
		return fulfillmentCenterRepository.save(fulfillmentCenter);
	}

	public List<FulfillmentCenter> getAllFulfillmentCenters() {
		return fulfillmentCenterRepository.findAll();
	}

	public Optional<FulfillmentCenter> getFulfillmentCenterById(Long id) {
		return fulfillmentCenterRepository.findById(id);
	}

	public FulfillmentCenter updateFulfillmentCenter(Long id, FulfillmentCenter fulfillmentCenterDetails) {
		return fulfillmentCenterRepository.findById(id).map(fulfillmentCenter -> {
			fulfillmentCenter.setName(fulfillmentCenterDetails.getName());
			fulfillmentCenter.setLocation(fulfillmentCenterDetails.getLocation());
			return fulfillmentCenterRepository.save(fulfillmentCenter);
		}).orElseThrow(() -> new RuntimeException("Fulfillment Center not found"));
	}

	public void deleteFulfillmentCenter(Long id) {
		List<Product> products = productRepository.findByFulfillmentCenterId(id);
		productRepository.deleteAll(products);
		fulfillmentCenterRepository.deleteById(id);
	}
}