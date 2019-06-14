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
				//���ܷ�����ת��������Message
				ois = new ObjectInputStream(s.getInputStream());
				mess=(Message)ois.readObject();//�ȴ�Server����Message������
				String chatMessageString=((mess.getSender()+"��"+mess.getReceiver()+"˵:"+mess.getContent()+"\r\n"));
				System.out.println(chatMessageString);
				
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_NoUser)){
					JOptionPane.showMessageDialog(null, "��Ӻ���ʧ�ܣ��û���������");
				}
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_AlreadyFriend)){
					JOptionPane.showMessageDialog(null, "��Ӻ���ʧ�ܣ������ظ���Ӻ���");
				}
				if(mess.getMessageType().equals(Message.message_AddFriendSuccess)){
					String allFriendName=mess.getContent();
					FriendList friendList=(FriendList)ClientLogin.hmFriendList.get(mess.getSender());
					friendList.updateFriendIcon(allFriendName);
					friendList.revalidate();

				}
				
				if(mess.getMessageType().equals(Message.message_Common)){
					//ϣ��������Ϣ�ں��ѵ������������ʾ����������ôʵ�ֵ����⣿
					//1.�õ�Ҫ��ʾ������Ϣ��friendChat����
					FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
					//2.��������Ϣ��JTextArea����ʾ
					Date date=mess.getSendTime();
					friendChat1.appendJta(date.toString()+"\r\n"+chatMessageString);
				}
			
				
				//��3�����յ������������������ߺ�����Ϣ�������Ӧͼ��
				if(mess.getMessageType().equals(Message.message_OnlineFriend)){
					System.out.println("���ߺ��ѣ�"+mess.getContent());
					//��ôȥ�����Ӧ��ͼ�ꣿ
					//����Ҫ�õ�FriendList����?
					FriendList friendList=(FriendList)ClientLogin.hmFriendList.get(mess.getReceiver());
					friendList.setEnabledOnlineFriend(mess.getContent());
				}
				//���������ߺ��ѵ�ͼ�경��2�������û������յ��ĸ���Ϣ����ͼ��
				if(mess.getMessageType().equals(Message.message_NewOnlineFriend)){
					System.out.println("�������û��������ǣ�"+mess.getContent());
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
