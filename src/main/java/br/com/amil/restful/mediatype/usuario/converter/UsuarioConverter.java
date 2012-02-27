package br.com.amil.restful.mediatype.usuario.converter;

import static br.com.amil.util.XStreamWriterUtil.writeNode;

import java.text.SimpleDateFormat;

import br.com.amil.model.Usuario;
import br.com.amil.restful.converter.registry.Registry;
import br.com.amil.util.CryptUtils;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

@Registry(to = "vnd.amil.usuario.v1")
public class UsuarioConverter implements Converter {

	@Override
	public boolean canConvert(@SuppressWarnings("rawtypes") Class type) {
		return type.equals(Usuario.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Usuario usuario = (Usuario) source;

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		writeNode(writer, "login", usuario.getLogin());
		writeNode(writer, "ultimoAcesso", dateFormat.format(usuario.getUltimoAcesso()));
		writeNode(writer, "senha", CryptUtils.encrypt(usuario.getSenha()));
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}

}
