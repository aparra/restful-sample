package br.com.amil.restful.converter.registry;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.ClassUtils;

import br.com.amil.restful.util.ClassLoaderUtils;
import br.com.caelum.vraptor.ioc.Component;

import com.thoughtworks.xstream.converters.Converter;

@Component
public class ConverterRegistry {

    private Map<String, Set<Converter>> map = new HashMap<String, Set<Converter>>();

	@PostConstruct
    @SuppressWarnings("unused")
    private void registry() {
    	for (Class<?> clazz : ClassLoaderUtils.getClassesForPackage("br.com.amil.restful.converter")) {
    		if (ClassUtils.isAssignable(clazz, Converter.class)) {
    			Registry registry = clazz.getAnnotation(Registry.class);

    			if (registry == null) {
    				throw new RuntimeException("No @Registry present for: " + clazz.getName());
    			}
    			
    			try {
					this.addConverter(registry.to(), (Converter) clazz.newInstance());
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
    		}
		}
    }

    private void addConverter(String key, Converter converter) {
    	Set<Converter> converters = map.get(key);
    	
    	if (converters == null) {
    		converters = Collections.emptySet();
    		map.put(key, converters);
    	}
    	
    	converters.add(converter);
    }
    
    public Set<Converter> load(String key) {
        Set<Converter> converters = map.get(key);

        if (converters == null) {
            return Collections.emptySet();
        }

        return converters;
    }

}
