package com.example.controller;

import com.example.model.FulfillmentCenter;
import com.example.model.Product;
import com.example.service.FulfillmentCenterService;
import com.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({FulfillmentCenterController.class, ProductController.class})
public class ControllerUnitTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FulfillmentCenterService fulfillmentCenterService;

	@MockBean
	private ProductService productService;

	@Test
	public void shouldAddFulfillmentCenter() throws Exception {
		FulfillmentCenter center = new FulfillmentCenter(1L, "Center 1", "Location 1");
		given(fulfillmentCenterService.addFulfillmentCenter(any(FulfillmentCenter.class))).willReturn(center);

		mockMvc.perform(post("/api/fulfillment-centers")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Center 1\",\"location\":\"Location 1\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Center 1"))
				.andExpect(jsonPath("$.location").value("Location 1"))
				.andDo(print());
	}

	@Test
	public void shouldGetAllFulfillmentCenters() throws Exception {
		FulfillmentCenter center = new FulfillmentCenter(1L, "Center 1", "Location 1");
		given(fulfillmentCenterService.getAllFulfillmentCenters()).willReturn(Collections.singletonList(center));

		mockMvc.perform(get("/api/fulfillment-centers"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name").value("Center 1"))
				.andDo(print());
	}

	@Test
	public void shouldUpdateFulfillmentCenter() throws Exception {
		FulfillmentCenter updatedCenter = new FulfillmentCenter(1L, "Updated Center", "Updated Location");
		given(fulfillmentCenterService.updateFulfillmentCenter(eq(1L), any(FulfillmentCenter.class))).willReturn(updatedCenter);

		mockMvc.perform(put("/api/fulfillment-centers/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Updated Center\",\"location\":\"Updated Location\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Updated Center"))
				.andExpect(jsonPath("$.location").value("Updated Location"))
				.andDo(print());
	}

	@Test
	public void shouldDeleteFulfillmentCenter() throws Exception {
		Mockito.doNothing().when(fulfillmentCenterService).deleteFulfillmentCenter(1L);

		mockMvc.perform(delete("/api/fulfillment-centers/1"))
				.andExpect(status().isNoContent())
				.andDo(print());
	}

	@Test
	public void shouldAddProduct() throws Exception {
		Product product = new Product(1L, "Milk", "Fresh milk", 20, "Sellable", 100, null);
		given(productService.addProduct(any(Product.class))).willReturn(product);

		mockMvc.perform(post("/api/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"name\":\"Milk\",\"description\":\"Fresh milk\",\"quantity\":20,\"status\":\"Sellable\",\"value\":100}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Milk"))
				.andExpect(jsonPath("$.quantity").value(20))
				.andDo(print());
	}

	@Test
	public void shouldUpdateProductQuantity() throws Exception {
		Product updatedProduct = new Product(1L, "Milk", "Fresh milk", 25, "Sellable", 100, null);
		given(productService.updateProductQuantity(eq(1L), eq(25))).willReturn(updatedProduct);

		mockMvc.perform(put("/api/products/1/update-quantity")
						.param("newQuantity", "25"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.quantity").value(25))
				.andDo(print());
	}

	@Test
	public void shouldGetProductsByStatus() throws Exception {
		Product product = new Product(1L, "Milk", "Fresh milk", 20, "Sellable", 100, null);
		given(productService.getProductsByStatus("Sellable")).willReturn(Collections.singletonList(product));

		mockMvc.perform(get("/api/products/filter").param("status", "Sellable"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].status").value("Sellable"))
				.andDo(print());
	}
}
