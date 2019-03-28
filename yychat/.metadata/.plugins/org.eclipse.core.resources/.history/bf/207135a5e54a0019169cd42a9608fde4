package controller;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.yychat.model.Message;
import com.yychat.model.User;

public class StartServer {
	ServerSocket ss;
	Socket s;
	
	public StartServer(){
		try {
	        ss=new ServerSocket(3456);//服务器端口监听3456
	        System.out.println("服务器已经启动，监听3456端口...");
	        while(true){
	        	s=ss.accept();//等待客户端建立连接
	            System.out.println(s);//输出连接对象
	        
	            //字节输入流 包装成 对象输入流
	            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
	            User user=(User)ois.readObject();
	            System.out.println(user.getUserName());
	            System.out.println(user.getPassWord());
	            //Server端验证密码是否“123456”
	            Message mess=new Message();
	            mess.setSender("Server");
        	    mess.setReceiver(user.getUserName());
	            if(user.getPassWord().equals("123456")){//不能用“==”，对象比较
	        	    //消息传递，创建Message对象
	        	    mess.setMessageType("1");//验证通过
	            }
	            else{
	        	    mess.setMessageType("0");//验证不通过
	            }
	            ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
	            oos.writeObject(mess);
	        }

		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		

	}

}
