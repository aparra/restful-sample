package br.com.amil.restful.mediatype.usuario;

import java.io.Serializable;

import br.com.amil.model.Story.Task;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

public class TaskMediaType implements HypermediaResource, Serializable {

	private static final long serialVersionUID = 8235472813564735847L;
	
	private Task task;

	public TaskMediaType(Task task) {
		this.task = task;
	}

	public Task getTask() {
		return task;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
	}
}
