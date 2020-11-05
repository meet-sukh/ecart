package io.ecart.product.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import io.ecart.dto.ProductFilterDto;
import io.ecart.entity.Product;
import io.ecart.entity.ProductStock;

@Service
public class ProductService {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ProductStockService productStockService;
	
	@Transactional
	public Product addProduct(Product product) {
		ProductStock productStock = productStockService.add(0, product.getQuantity());
		product.setProductId(productStock.getProductId());
		
		return mongoTemplate.save(product);
	}
	
	public List<Product> searchProduct(ProductFilterDto productFilterDto,Pageable pageableRequest) {

		Query q = new Query();
		
		if(!StringUtils.isEmpty(productFilterDto.getCategoryId())) {
			q.addCriteria(Criteria.where("productCategoryId").is(productFilterDto.getCategoryId()));
		}
		if(!StringUtils.isEmpty(productFilterDto.getSubCategoryId())) {
			q.addCriteria(Criteria.where("productSubCategoryId").is(productFilterDto.getSubCategoryId()));
		}
		if(!StringUtils.isEmpty(productFilterDto.getMinPrice())) {
			q.addCriteria(Criteria.where("cost").gte(productFilterDto.getMinPrice()));
		}
		if(!StringUtils.isEmpty(productFilterDto.getMaxPrice())) {
			q.addCriteria(Criteria.where("cost").lte(productFilterDto.getMaxPrice()));
		}
		if(!StringUtils.isEmpty(productFilterDto.getSearchText())) {
			q.addCriteria(Criteria.where("name").regex(productFilterDto.getSearchText(),"i")
					.orOperator(Criteria.where("description").regex(productFilterDto.getSearchText(),"i")));
		}
		
		q.with(pageableRequest);
		q.with(Sort.by(Direction.DESC, "create_date"));
		
		List<Product> productList = mongoTemplate.find(q, Product.class);
		return productList;
	}
}
