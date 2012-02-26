package br.com.amil.restful.custom;

import javax.servlet.http.HttpServletResponse;

import br.com.amil.restful.converter.registry.ConverterRegistry;
import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.RequestScoped;
import br.com.caelum.vraptor.serialization.ProxyInitializer;
import br.com.caelum.vraptor.serialization.Serializer;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilder;
import br.com.caelum.vraptor.serialization.xstream.XStreamJSONSerialization;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;

@Component
@RequestScoped
public class CustomRestfulJSONSerialization extends XStreamJSONSerialization {

	private final ConverterRegistry registry;
	private final CustomFormatResolver formatResolver;
	
	public CustomRestfulJSONSerialization(HttpServletResponse response, TypeNameExtractor extractor, ProxyInitializer initializer, XStreamBuilder builder, ConverterRegistry registry, CustomFormatResolver formatResolver) {
        super(response, extractor, initializer, builder);
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
		XStream xStream = super.builder.jsonInstance();
		
		for (Converter converter : registry.load(formatResolver.getVendor())) {
            xStream.registerConverter(converter);    
        }

		for (Converter converter : registry.load("base.converters")) {
		    xStream.registerConverter(converter);
        }
		
		return xStream;
	}

}
