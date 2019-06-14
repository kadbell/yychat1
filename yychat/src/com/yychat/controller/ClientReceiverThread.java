package com.yychat.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Date;

import javax.swing.JOptionPane;

import com.yychat.model.Message;
import com.yychat.view.ClientLogin;
import com.yychat.view.FriendChat1;
import com.yychat.view.FriendList;

public class ClientReceiverThread extends Thread{
	Socket s;
	
	public ClientReceiverThread(Socket s){
		this.s=s;
	}
	public void run(){
		ObjectInputStream ois;
		Message mess;
		while(true){
			try {
				//接受服务器转发过来的Message
				ois = new ObjectInputStream(s.getInputStream());
				mess=(Message)ois.readObject();//等待Server发送Message，阻塞
				String chatMessageString=((mess.getSender()+"对"+mess.getReceiver()+"说:"+mess.getContent()+"\r\n"));
				System.out.println(chatMessageString);
				
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_NoUser)){
					JOptionPane.showMessageDialog(null, "添加好友失败！用户名不存在");
				}
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_AlreadyFriend)){
					JOptionPane.showMessageDialog(null, "添加好友失败！不能重复添加好友");
				}
				if(mess.getMessageType().equals(Message.message_AddFriendSuccess)){
					String allFriendName=mess.getContent();
					FriendList friendList=(FriendList)ClientLogin.hmFriendList.get(mess.getSender());
					friendList.updateFriendIcon(allFriendName);
					friendList.revalidate();

				}
				
				if(mess.getMessageType().equals(Message.message_Common)){
					//希望聊天信息在好友的聊天界面上显示出来，该怎么实现的问题？
					//1.拿到要显示聊天信息的friendChat对象
					FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
					//2.把聊天信息在JTextArea上显示
					Date date=mess.getSendTime();
					friendChat1.appendJta(date.toString()+"\r\n"+chatMessageString);
				}
			
				
				//第3步接收到服务器发送来的在线好友信息，激活对应图标
				if(mess.getMessageType().equals(Message.message_OnlineFriend)){
					System.out.println("在线好友："+mess.getContent());
					//怎么去激活对应的图标？
					//首先要拿到FriendList对象?
					FriendList friendList=(FriendList)ClientLogin.hmFriendList.get(mess.getReceiver());
					friendList.setEnabledOnlineFriend(mess.getContent());
				}
				//激活新上线好友的图标步骤2、其他用户利用收到的该消息更新图标
				if(mess.getMessageType().equals(Message.message_NewOnlineFriend)){
					System.out.println("新上线用户的名字是："+mess.getContent());
					FriendList friendList=(FriendList)ClientLogin.hmFriendList.get(mess.getReceiver());
					//friendList.setEnabledOnlineFriend(mess.getContent());
					friendList.setEnabledNewOnlineFriend(mess.getContent());
				}
				
				
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
