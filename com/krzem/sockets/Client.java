package com.krzem.sockets;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Exception;
import java.net.Socket;



public class Client{
	private int id;
	private Socket s;



	public static void main(String[] args){
		new Client((args.length>0?args[0]:"127.0.0.1:8080"));
	}



	public Client(String addr){
		String h=addr.split(":")[0];
		int p=Integer.parseInt(addr.split(":")[1]);
		try (Socket s=new Socket(h,p)){
			this.s=s;
			BufferedReader i=new BufferedReader(new InputStreamReader(s.getInputStream()));
			PrintWriter o=new PrintWriter(s.getOutputStream(),true);
			this.id=Integer.parseInt(i.readLine());
			System.out.println(String.format("Your id: %d",this.id));
			while (true){
				String pr=i.readLine();
				System.out.println(pr);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}