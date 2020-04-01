package com.nikhil.krishagni.messenger.resources;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nikhil.krishagni.messenger.model.Message;
import com.nikhil.krishagni.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService=new MessageService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Message> getMessages(@QueryParam("year") int year,@QueryParam("start") int start,@QueryParam("size") int size)
	{
		if(year>0)
		{
			//Response.status(Status.CREATED).entity(messageService.getAllMessagesForYear(year)).build();
			return messageService.getAllMessagesForYear(year);
		}
		if((start>=0)&&(size>0))
		{
			//Response.status(Status.CREATED).entity(messageService.getAllMessagesPaginated(start,size)).build();
			return messageService.getAllMessagesPaginated(start, size);
		}
		
		return messageService.getAllMessages();
		//return Response.status(Status.CREATED).entity(messageService.getAllMessages()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message)
	{
		return messageService.addMessage(message);
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("id") long id,Message message)
	{
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message deleteMessage(@PathParam("id") long id)
	{
		return messageService.removeMessage(id);
	}
	
	
	
    @GET
    @Path("/{messageId}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getMessage(@PathParam("messageId") long messageId)  {
     // We can how use the profileId value to retrieve the record
    	
    	//return messageService.getMessage(messageId);
    	return Response.status(Status.CREATED).entity(messageService.getMessage(messageId)).build();
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentResource()
    {
    	return new CommentResource();
    }
  
}
