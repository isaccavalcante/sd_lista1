package Questao6;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPCipherClient{

   public static void main (String [] args) {
   
      final Socket clientSocket;
      final Scanner sc = new Scanner (System.in);
      

      try {

        clientSocket = new Socket ("127.0.0.1", 7896);
        final DataInputStream in = new DataInputStream( clientSocket.getInputStream());
        final DataOutputStream out =new DataOutputStream( clientSocket.getOutputStream());
        
        Thread send;
          send = new Thread (new Runnable () {
              String msg;
              @Override
              public void run () {
                  while (true) {
                      try {
                          
                          msg=sc.nextLine();
                          
                          out.writeUTF(SingletonCipher.cifra(msg, 10));
                          
                          
                      } catch (IOException ex) {
                          Logger.getLogger(TCPCipherClient.class.getName()).log(Level.SEVERE, null, ex);
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
                          
                          msg = SingletonCipher.decifra(msg, 10);
                          System.out.println(clientSocket.getLocalAddress() + " disse: " + msg);
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
