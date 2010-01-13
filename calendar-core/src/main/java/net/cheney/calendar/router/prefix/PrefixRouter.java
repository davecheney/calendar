package net.cheney.calendar.router.prefix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import net.cheney.motown.uri.Path;

public class PrefixRouter<V> {
	
	private final List<PrefixRoutable<V>> routables;

	public PrefixRouter(List<PrefixRoutable<V>> routables) {
		this.routables = routables;
	}
	
	public List<PrefixRoutable<V>> bestMatchForPrefix(Path path) {
		return bestMatchForPrefix(path, 1, routables);
	}
	
	/**
	 * Return a {@link List} of possible candidates that startWith the supplied {@link Path}
	 * @param prefix
	 * @param list
	 * @return
	 */
	private List<PrefixRoutable<V>> bestMatchForPrefix(final Path path, int prefixCount, Iterable<PrefixRoutable<V>> list) {
		Path prefix = path.first(prefixCount);
		List<PrefixRoutable<V>> l = new ArrayList<PrefixRoutable<V>>();
		for(PrefixRoutable<V> r : list) {
			if(r.prefix().first(prefix.size()).equals(prefix)) {
				l.add(r);
			}
		}
		switch(l.size()) {
		case 0:
			return Collections.emptyList();
			
		case 1:
			return l;
			
		default:
			return bestMatchForPrefix(path, ++prefixCount, l);
		}
	}

}
