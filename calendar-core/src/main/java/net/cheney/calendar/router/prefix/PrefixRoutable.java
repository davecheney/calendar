package net.cheney.calendar.router.prefix;

import net.cheney.motown.uri.Path;

public class PrefixRoutable {

	private final Path prefix;

	public PrefixRoutable(Path prefix) {
		this.prefix = prefix;
	}
	
	public Path prefix() {
		return prefix;
	}

}
