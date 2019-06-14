package com.yychat.model;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable,MessageType{
	private String sender;
	private String receiver;
	private String content;
	private String messageType;
    private Date sendTime;
	
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/*public Message(String sender){//构造方法
		this.sender=sender;
	}*/
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	

}
