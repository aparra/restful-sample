package br.com.amil.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CollectionUtils {

	@SuppressWarnings("unchecked")
	public static <T, COLLECTION extends Collection<T>> COLLECTION filter(COLLECTION collectionSource, Acceptable<T> acceptable) {
		COLLECTION filteredCollection = null;
		try {
		    try {
		        filteredCollection = (COLLECTION) collectionSource.getClass().newInstance();    
            } catch (InstantiationException e) {
                filteredCollection = (COLLECTION) new ArrayList<T>();
            }
			
			for (T item : collectionSource) {
				if (acceptable.accept(item)) {
					filteredCollection.add(item);
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("invalid collection source");
		}
		return filteredCollection;
	}
	
	public static <T, COLLECTION extends Collection<T>> T first(COLLECTION collectionSource, Acceptable<T> acceptable) {
	    COLLECTION filteredCollection = filter(collectionSource, acceptable);
	    return filteredCollection.isEmpty()? null : filteredCollection.iterator().next();  
	}
	
    public static <T, COLLECTION extends Collection<T>> T unique(COLLECTION collectionSource, Acceptable<T> acceptable) {
        COLLECTION filteredCollection = filter(collectionSource, acceptable);
        
        if (filteredCollection.size() > 1) {
            throw new RuntimeException("unique element exception on collection");
        }
        return filteredCollection.iterator().next();
    }
    
    public static <T, COLLECTION extends Collection<T>> T findFirstMatch(COLLECTION collectionSource, List<T> candidates) {
        T result = null;
        
        if (collectionSource != null && candidates != null) {
            outter:
            for (T candidate : candidates) {
                for (T source : collectionSource) {
                    if (candidate.equals(source)) {
                        result = source;
                        break outter;
                    }
                }
            }
        }

        return result;
    }
	
    public static <T, LIST extends List<T>> T last(LIST collectionSource) {
        return collectionSource.isEmpty() ? null : collectionSource.get(collectionSource.size() - 1);
    }
    
}
