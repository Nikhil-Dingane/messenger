package com.nikhil.krishagni.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.nikhil.krishagni.messenger.exception.DataNotFoundException;
import com.nikhil.krishagni.messenger.model.Message;
import com.nikhil.krishagni.messenger.model.Profile;
import com.nikhil.krishagni.messenger.service.MessageService;

@Path("/messages")
public class MessageResource {
	
	MessageService messageService=new MessageService();
	
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.TEXT_XML})
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
	public Response addMessage(Message message,@Context UriInfo uriInfo) throws URISyntaxException
	{
		//return messageService.addMessage(message);
		Message newMessage=messageService.addMessage(message);
		return Response.created(new URI(uriInfo.getPath().toString()+newMessage.getId())).entity(newMessage).build();
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
    public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo)  {
     // We can how use the profileId value to retrieve the record
   
    	Message message=messageService.getMessage(messageId);
    	message.addLink(getUriForSelf(uriInfo,message.getId()), "self");
    	message.addLink(getUriForProfile(uriInfo,message.getAuthor()), "profile");
    	message.addLink(getUriForComment(uriInfo,message.getId()),"comment");
    	return message;
    }
    
    private String getUriForComment(UriInfo uriInfo, long id) {
		// TODO Auto-generated method stub
		return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class).resolveTemplate("messageId",id).build().toString();
	}

	@Path("/{messageId}/comments")
    public CommentResource getCommentResource()
    {
    	return new CommentResource();
    }
   
    private String getUriForProfile(UriInfo uriInfo,String author)
    {
    	return uriInfo.getBaseUriBuilder().path(ProfileResource.class).path(author).build().toString();
    }
    
    private String getUriForSelf(UriInfo uriInfo,long messageId)
    {
    	return uriInfo.getBaseUriBuilder().path(MessageResource.class).path(new Long(messageId).toString()).build().toString();
    }
}
