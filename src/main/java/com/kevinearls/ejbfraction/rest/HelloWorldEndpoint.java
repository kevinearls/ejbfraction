package com.kevinearls.ejbfraction.rest;

import io.opentracing.util.GlobalTracer;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import java.util.Date;
import java.util.logging.Logger;

@Path("/hello")
public class HelloWorldEndpoint {

	private static final Logger log = Logger.getLogger(HelloWorldEndpoint.class.getName());

	@Inject
	SimpleService simpleService;

	@GET
	@Produces("text/plain")
	public Response doGet() {
		if (GlobalTracer.isRegistered()) {
			log.info(">>> Found a GlobalTracer");
		} else {
			log.info(">>>>>> Did NOT find a tracer");
		}

		String simpleServiceResponse = simpleService.doSomething();
		String responseMessage = "Hello from Wildfly Swarm at "
				+ new Date().toString()
				+ "; SImpleService says "
				+ simpleServiceResponse;
		Response response = Response.ok(responseMessage)
				.build();
		return response;
	}
}