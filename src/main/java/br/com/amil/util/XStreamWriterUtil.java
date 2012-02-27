package br.com.amil.util;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonWriter;

public class XStreamWriterUtil {

    public static void writeNode(HierarchicalStreamWriter writer, String nodeName, String nodeValue) {
    	writer.startNode(nodeName);
        writer.setValue(StringUtils.defaultIfEmpty(nodeValue, ""));
        writer.endNode();
    }

    public static void writeNode(HierarchicalStreamWriter writer, String nodeName, Object nodeValue) {
        writeNode(writer, nodeName, nodeValue != null? nodeValue.toString() : null);
    }
    
    public static void writeCollection(HierarchicalStreamWriter writer, MarshallingContext context, String nodeName, Collection<?> collection) {
        if (writer instanceof JsonWriter) {
            writeJsonCollection((JsonWriter) writer, context, nodeName, collection);
        } else {
            context.convertAnother(collection);            
        }
    }

    private static void writeJsonCollection(JsonWriter writer, MarshallingContext context, String nodeName, Collection<?> collection) {
        writer.startNode(nodeName, collection.getClass());
        context.convertAnother(collection);
        writer.endNode();
    }

}
