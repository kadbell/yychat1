package com.yychat.model;

public interface MessageType {
	String message_LoginFailure="0";//字符串常量
	String message_LoginSuccess="1";
	String message_Common="2";
	String message_RequestOnlineFriend="3";//客户端请求获得在线好友信息 
	String message_OnlineFriend="4";//服务器返回在线好友信息
	String message_NewOnlineFriend="5";
	//注册新用户步骤6：添加Message对象的新类型
	String message_RegisterSuccess="6";//注册成功的消息类型
	String message_RegisterFailure="7";//注册失败的消息类型
	//添加新的好友，步骤3.增加消息类型
	String message_AddFriend="8";
	String message_AddFriendSuccess="9";
	String message_AddFriendFailure_NoUser="10";
	String message_AddFriendFailure_AlreadyFriend="11";

}
