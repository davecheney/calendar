package net.cheney.calendar.router.prefix;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.base.Predicate;

import net.cheney.motown.uri.Path;

public class PrefixRouter<V> {
	
	
	/**
	 * Return a {@link List} of possible candidates that startWith the supplied {@link Path}
	 * @param prefix
	 * @param list
	 * @return
	 */
	protected List<PrefixRoutable> bestMatchForPrefix(final Path prefix, List<PrefixRoutable> list) {
		return newArrayList(filter(list, new Predicate<PrefixRoutable>() {

			@Override
			public boolean apply(PrefixRoutable input) {
				return input.prefix().first(prefix.size()).equals(prefix);
			}
			
		}));
	}
	
	public class Worker {
		
		
	}
}
