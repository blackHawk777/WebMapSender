/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.daniel.websocket;


import java.io.IOException;

import java.net.Socket;

/**
 *
 * @author Admin
 */
public class ClientSocket {
    
   
   
    private Socket clientSocket;
    
    
    
  
    
    
    public void connectToServer() throws IOException
    {
        // написать имя удалённого хоста
        try 
        {
       // remoteHost = InetAddress.getByName("localhost");
            setClientSocket(new Socket("localhost", 4445));
        if( getClientSocket()==null)
        {
                getClientSocket().connect(getClientSocket().getLocalSocketAddress());
        }
        }
        catch(Exception e)
        {
            e.getMessage();
        }
    }

    /**
     * @return the clientSocket
     */
    public Socket getClientSocket() {
        return clientSocket;
    }

    /**
     * @param clientSocket the clientSocket to set
     */
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    
  
    
}
