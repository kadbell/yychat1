package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.yychat.model.Message;

public class ServerReceiverThread extends Thread{
	public ServerReceiverThread(Socket s){
		ObjectInputStream ois;
		try {
			ois=new ObjectInputStream(s.getInputStream());
			Message mess=(Message)ois.readObject();
			System.out.println(mess.getSender()+"¶Ô"+mess.getReceiver()+"Ëµ:"+mess.getContent());
			
			if(mess.getMessageType().equals(Message.message_Common)){
				Socket s1=(Socket)StartServer.hmSocket.get(mess.getReceiver());
				ObjectOutputStream oos=new ObjectOutputStream(s1.getOutputStream());
				oos.writeObject(mess);
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
