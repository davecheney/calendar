package net.cheney.calendar.router.prefix;

import java.util.List;

import net.cheney.motown.dispatcher.ResourceHandler;

public class PrefixDispatchableRouter extends PrefixRouter<ResourceHandler> {

	private PrefixDispatchableRouter(List<PrefixRoutable<ResourceHandler>> routables) {
		super(routables);
	}

}
