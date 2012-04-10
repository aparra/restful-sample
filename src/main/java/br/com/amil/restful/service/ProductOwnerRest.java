package br.com.amil.restful.service;

import static br.com.caelum.vraptor.view.Results.representation;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.model.ProductOwner;
import br.com.amil.repository.ProductOwnerRepository;
import br.com.amil.restful.mediatype.usuario.ProductOwnerMediaType;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Status;

@Resource
@Path("/servicos/product-owner")
public class ProductOwnerRest {

	private Result result;

	private Status status;
	
	private ProductOwnerRepository productOwnerRepository;
	
	public ProductOwnerRest(Result result, Status status, ProductOwnerRepository productOwnerRepository) {
		this.result = result;
		this.status = status;
		this.productOwnerRepository = productOwnerRepository;
	}
	
	@Get("")
	public void list() {
		List<ProductOwnerMediaType> owners = new ArrayList<ProductOwnerMediaType>();
		for (ProductOwner productOwner : productOwnerRepository.findAll()) {
			owners.add(new ProductOwnerMediaType(productOwner));	
		}
		
		result.use(representation()).from(owners, "productOwners").recursive().serialize();
	}
	
	@Post("")
	public void create(ProductOwner productOwner) {
		result.use(representation()).from(new ProductOwnerMediaType(productOwnerRepository.create(productOwner))).recursive().serialize();
		status.created();
	}
	
	@Get("/{productOwner.id}")
	public void show(ProductOwner productOwner) {
		ProductOwner existingProductOwner = productOwnerRepository.find(productOwner.getId());
		if (existingProductOwner == null) {
			status.notFound();
		} else {
			result.use(representation()).from(new ProductOwnerMediaType(existingProductOwner)).recursive().serialize();
			status.ok();
		}
	}
}
