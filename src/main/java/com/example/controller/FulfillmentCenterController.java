package com.example.controller;

import com.example.model.FulfillmentCenter;
import com.example.service.FulfillmentCenterService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fulfillment-centers")
public class FulfillmentCenterController {
	private final FulfillmentCenterService fulfillmentCenterService;

	public FulfillmentCenterController(FulfillmentCenterService fulfillmentCenterService) {
		this.fulfillmentCenterService = fulfillmentCenterService;
	}

	@Operation(summary = "Добавить новый центр выполнения", description = "Создает новый центр выполнения в системе")
	@PostMapping
	public ResponseEntity<FulfillmentCenter> addFulfillmentCenter(@RequestBody FulfillmentCenter fulfillmentCenter) {
		return ResponseEntity.ok(fulfillmentCenterService.addFulfillmentCenter(fulfillmentCenter));
	}

	@Operation(summary = "Получить все центры выполнения", description = "Возвращает список всех доступных центров выполнения")
	@GetMapping
	public ResponseEntity<List<FulfillmentCenter>> getAllFulfillmentCenters() {
		return ResponseEntity.ok(fulfillmentCenterService.getAllFulfillmentCenters());
	}

	@Operation(summary = "Получить центр выполнения по ID", description = "Возвращает информацию о центре выполнения по заданному идентификатору")
	@GetMapping("/{id}")
	public ResponseEntity<FulfillmentCenter> getFulfillmentCenterById(@PathVariable Long id) {
		return fulfillmentCenterService.getFulfillmentCenterById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Operation(summary = "Обновить центр выполнения", description = "Обновляет информацию о центре выполнения по заданному идентификатору")
	@PutMapping("/{id}")
	public ResponseEntity<FulfillmentCenter> updateFulfillmentCenter(@PathVariable Long id, @RequestBody FulfillmentCenter fulfillmentCenterDetails) {
		return ResponseEntity.ok(fulfillmentCenterService.updateFulfillmentCenter(id, fulfillmentCenterDetails));
	}

	@Operation(summary = "Удалить центр выполнения", description = "Удаляет центр выполнения по заданному идентификатору")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFulfillmentCenter(@PathVariable Long id) {
		fulfillmentCenterService.deleteFulfillmentCenter(id);
		return ResponseEntity.noContent().build();
	}
}