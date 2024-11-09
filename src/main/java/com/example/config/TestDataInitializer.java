package com.example.config;

import com.example.model.FulfillmentCenter;
import com.example.model.Product;
import com.example.service.FulfillmentCenterService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class TestDataInitializer implements CommandLineRunner {
	private final FulfillmentCenterService fulfillmentCenterService;
	private final ProductService productService;

	@Autowired
	public TestDataInitializer(FulfillmentCenterService fulfillmentCenterService, ProductService productService) {
		this.fulfillmentCenterService = fulfillmentCenterService;
		this.productService = productService;
	}

	@Override
	public void run(String... args) throws Exception {

		FulfillmentCenter center1 = fulfillmentCenterService.addFulfillmentCenter(new FulfillmentCenter(null, "Центр №1", "Москва"));
		FulfillmentCenter center2 = fulfillmentCenterService.addFulfillmentCenter(new FulfillmentCenter(null, "Центр №2", "Санкт-Петербург"));
		FulfillmentCenter center3 = fulfillmentCenterService.addFulfillmentCenter(new FulfillmentCenter(null, "Центр №3", "Новосибирск"));

		productService.addProduct(new Product(null, "Молоко", "Коровье молоко", 20, "Sellable", 100, center1));
		productService.addProduct(new Product(null, "Хлеб", "Ржаной хлеб", 15, "Sellable", 50, center1));
		productService.addProduct(new Product(null, "Сыр", "Твердый сыр", 10, "Sellable", 150, center1));

		productService.addProduct(new Product(null, "Яблоки", "Красные яблоки", 30, "Sellable", 200, center2));
		productService.addProduct(new Product(null, "Масло", "Сливочное масло", 25, "Unfulfillable", 300, center2));
		productService.addProduct(new Product(null, "Кофе", "Зерновой кофе", 40, "Inbound", 500, center2));

		productService.addProduct(new Product(null, "Чай", "Черный чай", 50, "Sellable", 100, center3));
		productService.addProduct(new Product(null, "Шоколад", "Темный шоколад", 20, "Sellable", 250, center3));
		productService.addProduct(new Product(null, "Кефир", "Натуральный кефир", 10, "Inbound", 120, center3));
	}
}
