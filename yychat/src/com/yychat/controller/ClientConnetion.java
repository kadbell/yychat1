package com.yychat.controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychat.model.User;

public class ClientConnetion {
	public static Socket s;//静态成员变量
	public ClientConnetion(){
		try {//异常处理结构
			s=new Socket("127.0.0.1",3456);//本机地址，回测地址
			System.out.println("客户端Socket"+s);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public Message loginValidate(User user){
		//对象的输入输出流
		ObjectOutputStream oos;
		Message mess=null;
		try {//把字节输出流对象 包装成 对象输出流对象
			 oos=new ObjectOutputStream(s.getOutputStream());
			 oos.writeObject(user);//
			 
			 ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			 mess=(Message)ois.readObject();
			 
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return mess;
	}
}
