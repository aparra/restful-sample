package br.com.amil.restful.mediatype.usuario.converter;

import static br.com.amil.util.XStreamWriterUtil.writeNode;
import br.com.amil.model.ProductOwner;
import br.com.amil.restful.converter.registry.Registry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Registry(to = "vnd.amil.productowner.v1")
public class ProductOwnerConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(ProductOwner.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		ProductOwner productOwner = (ProductOwner) source;

		writeNode(writer, "id", productOwner.getId());
		writeNode(writer, "name", productOwner.getName());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}
}