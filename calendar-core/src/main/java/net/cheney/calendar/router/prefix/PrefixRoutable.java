package net.cheney.calendar.router.prefix;

import net.cheney.motown.uri.Path;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PrefixRoutable<V> {

	private final Path prefix;
	private final V value;

	public PrefixRoutable(Path prefix, V value) {
		this.prefix = prefix;
		this.value = value;
	}
	
	public Path prefix() {
		return prefix;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SIMPLE_STYLE);
	}
	
	public V value() {
		return value;
	}
}
