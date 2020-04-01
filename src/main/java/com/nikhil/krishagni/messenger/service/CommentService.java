package com.nikhil.krishagni.messenger.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nikhil.krishagni.messenger.database.*;
import com.nikhil.krishagni.messenger.model.Comment;
import com.nikhil.krishagni.messenger.model.Message;

public class CommentService {

	private Map<Long,Message> messages=DatabaseClass.getMessages();
	
	public CommentService()
	{
		this.addComment(1, new Comment(1L,"this is the comment","nikil"));
		this.addComment(2, new Comment(1L,"this is the comment for 2","nikhil"));
	}
	
	public List<Comment> getAllComments(long messageId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId,long commentId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId,Comment comment)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0)
		{
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId,long commentId)
	{
		Map<Long,Comment> comments=messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}
