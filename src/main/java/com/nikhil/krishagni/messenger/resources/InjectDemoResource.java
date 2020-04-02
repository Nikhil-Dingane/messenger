package com.nikhil.krishagni.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

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
	
	@GET
	@Path("context")
	public String getParamsUsingContex(@Context UriInfo uriInfo,@Context HttpHeaders httpHeaders)
	{
		return uriInfo.getAbsolutePath().toString()+" \t cookie:  "+httpHeaders.getCookies();
		
	}
}
