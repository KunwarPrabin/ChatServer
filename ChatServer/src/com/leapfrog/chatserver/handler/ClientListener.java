/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatserver.handler;

import com.leapfrog.chatserver.dao.UserDAO;
import com.leapfrog.chatserver.dao.impl.UserDAOImpl;
import com.leapfrog.chatserver.entity.Client;
import com.leapfrog.chatserver.entity.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author OWNER
 */
public class ClientListener extends Thread{
   private Socket socket;
   private ClientHandler handler;
   PrintStream pstream;
   BufferedReader reader;
   private Client client;
   
   private UserDAO userDAO=new UserDAOImpl();
   public ClientListener(Socket socket,ClientHandler handler)throws IOException{
       this.socket=socket;
       this.handler=handler;
       pstream=new PrintStream(socket.getOutputStream());
       reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
   }

    @Override
    public void run() {
        try{
            
             pstream.println("Welcome to the Himalayan Bank ATM Machine");
             while(!doLogin()){
                 pstream.println("Invalid username/password");
             }
            
             doChat();
            
        }catch(IOException ioe){
            
        }
    }
    
    private boolean doLogin()throws IOException{
        
            pstream.println("Enter your username:");
            String userName=reader.readLine();
            pstream.println("Enter your password:");
            String password=reader.readLine();
            User user=userDAO.login(userName, password);
            if(user!=null){
                client=new Client(socket, userName);
                handler.addClient(client);
                return true;
            }
            return false;
            
    }
    
    private void doChat()throws IOException
    {
        while(true){
            pstream.print(">");
            String line=reader.readLine();
            handler.broadcastMessage(client, client.getUserName()+ " says >"+line);
        }
    }
   
   
    
}
