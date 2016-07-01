/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.chatserver.handler;

import com.leapfrog.chatserver.entity.Client;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OWNER
 */
public class ClientHandler {
    private List<Client> clients=new ArrayList<>();
    
    public void addClient(Client c){
        clients.add(c);
    }
    
    public Client getByUserName(String userName){
        for(Client c:clients){
            if(c.getUserName().equals(userName)){
                return c;
            }
        }
        return null;
    }
    
    public Client getBySocket(Socket s){
        for(Client c:clients){
            if(c.getSocket().equals(s)){
                return c;
            }
        }
        return null;
    }
    
    public List<Client> getClients(){
        return clients;
    }
    
    public void broadcastMessage(Client client,String msg)throws IOException{
        for(Client c:clients){
            if(!c.equals(client)){
                PrintStream output=new PrintStream(c.getSocket().getOutputStream());
                output.println(msg);
            }
        }
    } 
}
