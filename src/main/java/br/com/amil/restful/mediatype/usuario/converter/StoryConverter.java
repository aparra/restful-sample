package br.com.amil.restful.mediatype.usuario.converter;

import static br.com.amil.util.XStreamWriterUtil.writeNode;
import br.com.amil.model.Story;
import br.com.amil.restful.converter.registry.Registry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Registry(to = "vnd.amil.story.v1")
public class StoryConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(Story.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Story story = (Story) source;

		writeNode(writer, "id", story.getId());
		writeNode(writer, "points", story.getPoints());
		writeNode(writer, "title", story.getTitle());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}
}