package net.cheney.calendar.example.server;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import net.cheney.motown.dispatcher.Dispatcher;
import net.cheney.motown.dispatcher.ResourceFactory;
import net.cheney.motown.dispatcher.ResourceHandler;
import net.cheney.motown.dispatcher.SingletonResourceFactory;
import net.cheney.motown.dispatcher.dynamic.DynamicResourceHandler;
import net.cheney.motown.protocol.http.async.HttpServerProtocolFactory;
import net.cheney.motown.resource.api.ResourceProvidor;
import net.cheney.motown.resource.controller.ResourceController;
import net.cheney.motown.resource.file.FileResourceProvidor;
import net.cheney.rev.protocol.ServerProtocolFactory;
import net.cheney.rev.reactor.Reactor;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.BasicConfigurator;

public class Startup {

	static {
		BasicConfigurator.configure();
	}

	public static void main(String[] args) throws IOException {
		try {
			CommandLine line = parseCommandLine(args);

			final ResourceProvidor providor = createResourceProvidorFromCommandLine(line);

			ResourceController controller = new ResourceController(providor);

			ResourceFactory resourceFactory = new SingletonResourceFactory(
					controller);
			ResourceHandler resourceHandler = new DynamicResourceHandler(
					resourceFactory);

			Dispatcher dispatcher = new Dispatcher(resourceHandler);

			SocketAddress addr = createSocketAddressFromCommandLine(line);
			ServerProtocolFactory factory = new HttpServerProtocolFactory(
					dispatcher);

			System.out.println("Listening on: "+addr);
			System.out.println("Publishing root: "+getDocumentRootFromCommandLine(line));
			Reactor.open().listen(addr, factory);
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("dav", buildOptions());
		}
	}

	private static ResourceProvidor createResourceProvidorFromCommandLine(CommandLine line) {
		File root = getDocumentRootFromCommandLine(line);
		return new FileResourceProvidor(root);
	}

	private static File getDocumentRootFromCommandLine(CommandLine line) {
		return new File(line.getOptionValue('r', System.getProperty("user.dir")));
	}

	private static SocketAddress createSocketAddressFromCommandLine(
			CommandLine line) {
		String host = line.getOptionValue('h', "localhost");
		int port = Integer.parseInt(line.getOptionValue('p', "8080"));
		return new InetSocketAddress(host, port);
	}

	private static CommandLine parseCommandLine(String[] args)
			throws ParseException {
		CommandLineParser parser = new PosixParser();
		Options options = buildOptions();
		return parser.parse(options, args);
	}

	private static Options buildOptions() {
		Options options = new Options();

		options.addOption("h", "host", true, "Host name to bind to");
		options.addOption("p", "port", true, "Port to bind to");
		options.addOption("r", "root", true, "Root to serve from");

		return options;
	}
}
