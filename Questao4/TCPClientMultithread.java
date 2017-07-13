package Questao4;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClientMultithread{

   public static void main (String [] args) {
   
      final Socket clientSocket;
      final Scanner sc = new Scanner (System.in);

      try {

        clientSocket = new Socket ("127.0.0.1", 7896);
        final DataInputStream in = new DataInputStream( clientSocket.getInputStream());
        final DataOutputStream out =new DataOutputStream( clientSocket.getOutputStream());
        
        Thread send = new Thread (new Runnable () {
             String msg;
             @Override
              public void run () {
                while (true) {
                    try {
                        
                        msg=sc.nextLine();
                        out.writeUTF(msg);
                        

                    } catch (IOException ex) {
                        Logger.getLogger(TCPClientMultithread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
             }
         });
         send.start ();
 
        Thread receive;
          receive = new Thread (new Runnable () {
              String msg;
              
              @Override
              public void run () {
                    try {

                      while(true){
                      msg=in.readUTF();
                      System.out.println(clientSocket.getInetAddress()+" disse: "+msg);
                      }
                      
                      
                  } catch (IOException e) {
                  }
              }
          });
        receive.start ();
 
      } catch (IOException e) {
      }
  }
}
