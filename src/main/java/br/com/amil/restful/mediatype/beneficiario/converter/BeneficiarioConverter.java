package br.com.amil.restful.mediatype.beneficiario.converter;

import static br.com.amil.util.XStreamWriterUtil.writeNode;

import java.text.SimpleDateFormat;

import br.com.amil.model.Beneficiario;
import br.com.amil.restful.converter.registry.Registry;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Registry(to = "vnd.amil.beneficiario.v1")
public class BeneficiarioConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(Beneficiario.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Beneficiario beneficiario = (Beneficiario) source;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		writeNode(writer, "id", beneficiario.getId());
		writeNode(writer, "nome", beneficiario.getNome());
		writeNode(writer, "cpf", beneficiario.getCpf());
		writeNode(writer, "dataNascimento", dateFormat.format(beneficiario.getDataNascimento()));
		writeNode(writer, "modalidade", beneficiario.getModalidade());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}

}
