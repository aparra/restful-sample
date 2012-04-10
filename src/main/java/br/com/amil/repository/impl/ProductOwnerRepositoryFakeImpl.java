package br.com.amil.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.amil.model.ProductOwner;
import br.com.amil.repository.ProductOwnerRepository;
import br.com.amil.repository.Sequence;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class ProductOwnerRepositoryFakeImpl implements ProductOwnerRepository {

	public ProductOwnerRepositoryFakeImpl(Sequence sequence) {
		this.sequence = sequence;
	}

	private Sequence sequence;
	
	private HashMap<Long, ProductOwner> db = new HashMap<Long, ProductOwner>();
	
	@Override
	public ProductOwner create(ProductOwner productOwner) {
		productOwner.setId(sequence.nextval());
		db.put(productOwner.getId(), productOwner);
		return productOwner;
	}

	@Override
	public ProductOwner find(Long id) {
		return db.get(id);
	}

	@Override
	public List<ProductOwner> findAll() {
		return new ArrayList<ProductOwner>(db.values());
	}
	
}	
