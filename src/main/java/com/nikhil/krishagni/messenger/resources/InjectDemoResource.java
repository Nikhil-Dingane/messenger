package com.nikhil.krishagni.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/injectdemo")
public class InjectDemoResource {

	@GET
	@Path("/annotations")
	@Produces(MediaType.TEXT_PLAIN)
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customContentHeader") String header)
	{
		return "this is the test message of injectdemo:= "+ matrixParam+" header param is "+header;
	}
}
