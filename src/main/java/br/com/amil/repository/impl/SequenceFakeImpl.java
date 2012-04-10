package br.com.amil.repository.impl;

import br.com.amil.repository.Sequence;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class SequenceFakeImpl implements Sequence {

	private static Long value = 1L;
	
	public synchronized Long nextval() {
		return value++;
	}
}
