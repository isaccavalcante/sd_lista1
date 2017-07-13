package Questao2;


import java.net.*;
import java.io.*;

public class TCPCalcServer {
	public static void main (String args[]) {
		try{
			int serverPort = 7896;
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
		try {			                 
                        
                    while (true){
			String pergunta = in.readUTF();
			int res=0;
                        String operacao="";
                        
                        try {
                            
                        
                        if (pergunta.contains("+")){
                            operacao="adição";
                            String[] expr = pergunta.split("\\+");
                            for (int i=0; i<=expr.length-1; i++){
                                res+=Integer.parseInt(expr[i]);
                            }
                        }
                        
                        if (pergunta.contains("*")){
                            operacao="multiplicação";
                            String[] expr = pergunta.split("\\*");
                            res=Integer.parseInt(expr[0]);
                            for (int i=0; i<expr.length-1; i++){
                                res=res*Integer.parseInt(expr[i+1]);
                            }
                        }
                        
                        if (pergunta.contains("/")){
                            operacao="divisão";
                            String[] expr = pergunta.split("\\/");
                            res=Integer.parseInt(expr[0]);
                            for (int i=0; i<expr.length-1; i++){
                                res=res/Integer.parseInt(expr[i+1]);
                            }
                        }
                        
                        if (pergunta.contains("-")){
                            operacao= "subtração";
                            String[] expr = pergunta.split("\\-");
                            res=Integer.parseInt(expr[0]);
                            for (int i=0; i<expr.length-1; i++){
                                res=res-Integer.parseInt(expr[i+1]);
                            }
                        }
                        } catch (NumberFormatException e) {
                            System.out.println("Expressão inválida!"); //Como lança para o cliente?
                        }
                        
                        System.out.println("O cliente deseja fazer uma "+ operacao +". "+ pergunta+"?");
                        
                        
                        String resposta= String.valueOf(res);
                        System.out.println("A resposta é " + resposta);
                        out.writeUTF(resposta);
                    }
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		} catch(IOException e) {System.out.println("readline:"+e.getMessage());
		} finally{ try {clientSocket.close();}catch (IOException e){}}
		

	}
}
