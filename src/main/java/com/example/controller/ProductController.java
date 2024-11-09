package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "Добавить новый продукт", description = "Создает новый продукт в системе")
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		return ResponseEntity.ok(productService.addProduct(product));
	}

	@Operation(summary = "Переместить продукт в другой центр выполнения", description = "Перемещает продукт в указанный центр выполнения")
	@PutMapping("/{productId}/move")
	public ResponseEntity<Product> moveProduct(
			@Parameter(description = "ID продукта") @PathVariable Long productId,
			@Parameter(description = "ID целевого центра выполнения") @RequestParam Long targetFulfillmentCenterId) {
		return ResponseEntity.ok(productService.moveProduct(productId, targetFulfillmentCenterId));
	}

	@Operation(summary = "Обновить количество продукта", description = "Обновляет количество единиц продукта по его идентификатору")
	@PutMapping("/{productId}/update-quantity")
	public ResponseEntity<Product> updateProductQuantity(
			@Parameter(description = "ID продукта") @PathVariable Long productId,
			@Parameter(description = "Новое количество продукта") @RequestParam int newQuantity) {
		return ResponseEntity.ok(productService.updateProductQuantity(productId, newQuantity));
	}

	@Operation(summary = "Обновить статус продукта", description = "Обновляет статус продукта по его идентификатору")
	@PutMapping("/{productId}/update-status")
	public ResponseEntity<Product> updateProductStatus(
			@Parameter(description = "ID продукта") @PathVariable Long productId,
			@Parameter(description = "Новый статус продукта") @RequestParam String newStatus) {
		return ResponseEntity.ok(productService.updateProductStatus(productId, newStatus));
	}

	@Operation(summary = "Получить все продукты", description = "Возвращает список всех продуктов в системе")
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@Operation(summary = "Фильтровать продукты по статусу", description = "Возвращает список продуктов, отфильтрованных по заданному статусу")
	@GetMapping("/filter")
	public ResponseEntity<List<Product>> getProductsByStatus(@Parameter(description = "Статус продукта") @RequestParam String status) {
		return ResponseEntity.ok(productService.getProductsByStatus(status));
	}

	@Operation(summary = "Получить общую стоимость всех продуктов со статусом 'Sellable'", description = "Возвращает общую стоимость всех продуктов, которые можно продать")
	@GetMapping("/total-value/sellable")
	public ResponseEntity<Integer> getTotalValueOfSellableProducts() {
		return ResponseEntity.ok(productService.getTotalValueOfSellableProducts());
	}

	@Operation(summary = "Получить общую стоимость продуктов в центре выполнения", description = "Возвращает общую стоимость всех продуктов, находящихся в указанном центре выполнения")
	@GetMapping("/total-value/fulfillment-center/{fulfillmentCenterId}")
	public ResponseEntity<Integer> getTotalValueByFulfillmentCenter(@Parameter(description = "ID центра выполнения") @PathVariable Long fulfillmentCenterId) {
		return ResponseEntity.ok(productService.getTotalValueByFulfillmentCenter(fulfillmentCenterId));
	}
}