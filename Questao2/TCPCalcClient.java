package Questao2;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPCalcClient {
	public static void main (String args[]) {
            
		Socket s = null;
		try{
                    
                    while (true){
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			
                        DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			
                        System.out.print("Digite uma mensagem para o servidor: ");
                        Scanner sc = new Scanner(System.in);
                        String mensagem =sc.nextLine();
                        out.writeUTF(mensagem);      	
			
                        String resposta = in.readUTF();
			System.out.println("Servidor respondeu: "+ resposta);
                    }
                    
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}
