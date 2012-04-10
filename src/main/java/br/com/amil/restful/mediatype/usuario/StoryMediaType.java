package br.com.amil.restful.mediatype.usuario;

import java.io.Serializable;

import br.com.amil.model.Story;
import br.com.amil.restful.service.ProductOwnerRest;
import br.com.amil.restful.service.StoryRest;
import br.com.caelum.vraptor.restfulie.hypermedia.HypermediaResource;
import br.com.caelum.vraptor.restfulie.relation.RelationBuilder;

public class StoryMediaType implements HypermediaResource, Serializable {

	private static final long serialVersionUID = 8235472813564735847L;
	
	private Story story;

	public StoryMediaType(Story story) {
		this.story = story;
	}

	public Story getStory() {
		return story;
	}

	@Override
	public void configureRelations(RelationBuilder builder) {
		builder.relation("product-owner").uses(ProductOwnerRest.class).show(story.getProductOwner());
		builder.relation("tasks").uses(StoryRest.class).showTasks(story);
	}
}
