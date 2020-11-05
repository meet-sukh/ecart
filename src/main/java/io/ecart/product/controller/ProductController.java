package io.ecart.product.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.ecart.dto.ProductFilterDto;
import io.ecart.entity.Product;
import io.ecart.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
@Api(tags = { "products" })
public class ProductController {
	
	
	@Autowired
	private ProductService productService;

	@ApiResponses(value = { 
			@ApiResponse(code = 200, response = Product.class, message = "product created")})
	@PostMapping
	public ResponseEntity<?> addProduct(
			@Valid @RequestBody @NotNull Product product){
	
		log.info("addProduct request {}",product);
		product = productService.addProduct(product);
		log.info("addProduct response {} ~", product);
		return ResponseEntity.ok(product);
	}
	
	@ApiResponses(value = { 
			@ApiResponse(code = 200, response = Product.class, message = "products list",responseContainer = "List"),
			@ApiResponse(code = 204, response = Void.class, message = "no content")})
	@GetMapping
	public ResponseEntity<?> getProducts(
			@RequestParam(value = "search-text", required = false) String searchText,
			@RequestParam(value = "categoryId", required = false) String categoryId,
			@RequestParam(value = "subCategoryId", required = false) String subCategoryId,
			@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(value = "size", required = false, defaultValue = "10") Integer size){
		
		log.info("getProducts request {}",searchText);
		ProductFilterDto productFilterDto= new ProductFilterDto();
		productFilterDto.setCategoryId(categoryId);
		productFilterDto.setSubCategoryId(subCategoryId);
		productFilterDto.setSearchText(searchText);
		final Pageable pageableRequest = PageRequest.of(page, size);
		List<Product> productList = productService.searchProduct(productFilterDto, pageableRequest);
		if(CollectionUtils.isEmpty(productList)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productList);
	}

}
