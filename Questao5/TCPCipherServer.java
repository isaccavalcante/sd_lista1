package Questao5;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPCipherServer {

   public static void main (String [] test) {
 
     final ServerSocket serverSocket;
     final Socket  clientSocket;
     final Scanner sc = new Scanner (System.in);
     final DataInputStream in;
     final DataOutputStream out;
     
     try {
        int serverPort=7896;
        serverSocket = new ServerSocket(serverPort);
        System.out.println("Servidor iniciado, escutando na porta "+serverPort);
        clientSocket = serverSocket.accept();
        
        in = new DataInputStream( clientSocket.getInputStream());
        out =new DataOutputStream( clientSocket.getOutputStream());
        
        
        Thread send = new Thread (new Runnable () {
          String msg;
          @Override
          public void run () {
             while (true) {
                 try {
                     
                     
                     msg=sc.nextLine();
                     out.writeUTF(Cipher.cifra(msg, 10));

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
                     
                     
                     while (true){
                         msg = in.readUTF();
                         
                         msg=Cipher.decifra(msg, 10);
                         System.out.println(clientSocket.getLocalAddress()+" disse: "+msg);
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