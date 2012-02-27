package br.com.amil.restful.custom;

import javax.servlet.http.HttpServletResponse;

import br.com.amil.restful.converter.registry.ConverterRegistry;
import br.com.caelum.vraptor.config.Configuration;
import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.restfulie.Restfulie;
import br.com.caelum.vraptor.restfulie.serialization.RestfulSerializationJSON;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;

@Component
@RequestScoped
public class CustomRestfulJSONSerialization extends RestfulSerializationJSON {

	private final ConverterRegistry registry;
	private final CustomFormatResolver formatResolver;

	public CustomRestfulJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, Restfulie restfulie, Configuration config, ProxyInitializer initializer, XStreamBuilder builder, ConverterRegistry registry, CustomFormatResolver formatResolver) {
		super(response, extractor, restfulie, config, initializer, builder);
		this.registry = registry;
		this.formatResolver = formatResolver;
	}
	
    @Override
    public <T> Serializer from(T object, String alias) {
	    Serializer serializer = super.from(object, alias);
	    response.setContentType("application/json; charset=UTF-8");
	    return serializer;
    }
	
	@Override
	protected XStream getXStream() {
		XStream xStream = super.getXStream();
		
		for (Converter converter : registry.load(formatResolver.getVendor())) {
            xStream.registerConverter(converter, XStream.PRIORITY_LOW);    
        }

		for (Converter converter : registry.load("base.converters")) {
		    xStream.registerConverter(converter, XStream.PRIORITY_LOW);
        }
		
		return xStream;
	}

}
