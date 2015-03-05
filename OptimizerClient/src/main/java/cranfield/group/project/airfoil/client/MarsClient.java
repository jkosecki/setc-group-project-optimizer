package cranfield.group.project.airfoil.client;

import java.net.*;
import java.io.*;

public class MarsClient
{
	private Socket clientSocket;
   
	public MarsClient(String serverName, Integer port){
		try {
			clientSocket = new Socket(serverName, port);
			System.out.println("CLIENT : Just connected to "
			                   + clientSocket.getRemoteSocketAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
   
   public boolean areValidatedCredentials(String username, String password){
	   boolean areValidatedCredentials = false;
	   String credentials[]={"credentials",username,password};
	   try {
		ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.writeObject(credentials);
	   } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   ObjectInputStream in;
	   try {
			in = new ObjectInputStream(clientSocket.getInputStream());
		    areValidatedCredentials = (boolean)in.readObject();
		    System.out.println("here!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	   return areValidatedCredentials;
   }
   
   public void terminateConnection(){
	   String quitString[]={"quit"};
	   try {
		   ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
		   out.writeObject(quitString);
		   clientSocket.close();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
   }
}