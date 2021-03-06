package Questao3;

import java.net.*;
import java.io.*;
import java.util.Scanner;
public class TCPServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896; // the server port
			ServerSocket listenSocket = new ServerSocket(serverPort);
                        System.out.println("Servidor iniciado, escutando na porta "+serverPort);
			while(true) {
				Socket clientSocket = listenSocket.accept();
				Connection c = new Connection(clientSocket);
                                
			}
		} catch(IOException e) {System.out.println("Listen socket:"+e.getMessage());}
	}
}
class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
	public Connection (Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			in = new DataInputStream( clientSocket.getInputStream());
			out =new DataOutputStream( clientSocket.getOutputStream());
			this.start();
		} catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
	}
        @Override
	public void run(){
		try {			                 // an echo server
                        
                    while (true){
			String resposta = in.readUTF();	                  // read a line of data from the stream
			System.out.print("O cliente disse: "+ resposta+"\nDiga algo ao cliente: ");
                        
                        Scanner sc= new Scanner((System.in));
                        String mensagem = sc.nextLine();
                        
                        out.writeUTF(mensagem);
                    }
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
		

	}
}
