package br.com.amil.repository;

import java.util.List;

import br.com.amil.model.ProductOwner;

public interface ProductOwnerRepository {

	public ProductOwner create(ProductOwner productOwner);

	public ProductOwner find(Long productOwnerId);
	
	List<ProductOwner> findAll();
}
