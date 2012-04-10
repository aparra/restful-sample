package br.com.amil.restful.mediatype.usuario;

import java.io.Serializable;

import br.com.amil.model.ProductOwner;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

public class ProductOwnerMediaType implements HypermediaResource, Serializable {

	private static final long serialVersionUID = 8235472813564735847L;
	
	private ProductOwner productOwner;

	public ProductOwnerMediaType(ProductOwner productOwner) {
		this.productOwner = productOwner;
	}

	public ProductOwner getProductOwner() {
		return productOwner;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
	}
}
