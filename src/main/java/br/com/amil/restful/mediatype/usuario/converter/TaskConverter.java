package br.com.amil.restful.mediatype.usuario.converter;

import static br.com.amil.util.XStreamWriterUtil.writeNode;
import br.com.amil.model.Story.Task;
import br.com.amil.restful.converter.registry.Registry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Registry(to = "vnd.amil.task.v1")
public class TaskConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(Task.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Task task = (Task) source;

		writeNode(writer, "id", task.getId());
		writeNode(writer, "description", task.getDescription());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}
}