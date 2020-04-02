package com.nikhil.krishagni.messenger.resources.beans;

import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;

public class MessageFilterBean {

	@MatrixParam("param") String matrixParam,
	@HeaderParam("customContentHeader") String header 	
}
