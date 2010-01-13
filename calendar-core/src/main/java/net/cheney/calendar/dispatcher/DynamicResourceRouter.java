package net.cheney.calendar.dispatcher;

import java.io.IOException;

import net.cheney.calendar.router.prefix.PrefixDispatchableRouter;
import net.cheney.calendar.router.prefix.PrefixRoutable;
import net.cheney.motown.api.Request;
import net.cheney.motown.api.Response;
import net.cheney.motown.dispatcher.ResourceHandler;
import net.cheney.motown.uri.Path;

public class DynamicResourceRouter implements ResourceHandler {

	private final PrefixDispatchableRouter router;

	public DynamicResourceRouter(PrefixDispatchableRouter router) {
		this.router = router;
	}
	
	@Override
	public Response dispatch(Request request) throws IOException {
		Path path = Path.fromUri(request.uri());
		PrefixRoutable<ResourceHandler> dispatcher = router.bestMatchForPrefix(path);
		return dispatcher.value().dispatch(request);
	}


}
