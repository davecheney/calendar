package net.cheney.calendar.router.prefix;

import net.cheney.motown.uri.Path;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class PrefixRoutable<V> {

	private final Path prefix;

	public PrefixRoutable(Path prefix) {
		this.prefix = prefix;
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
}
