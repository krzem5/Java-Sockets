package com.krzem.sockets;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.Exception;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;



public class Main{
	private int NID=0;



	public static void main(String[] args){
		new Main((args.length>0?Integer.parseInt(args[0]):8080));
	}



	public Main(int p){
		try (ServerSocket ss=new ServerSocket(p)){
			System.out.println(String.format("Server started on port %d.",p));
			while (true){
				this._accept(ss.accept());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}



	private void _accept(Socket ns){
		System.out.println(String.format("New client (id=%d).",this.NID));
		int nsid=this.NID+0;
		this.NID++;
		new Thread(new Runnable(){
			public void run(){
				try{
					BufferedReader i=new BufferedReader(new InputStreamReader(ns.getInputStream()));
					PrintWriter o=new PrintWriter(ns.getOutputStream(),true);
					o.println(Integer.toString(nsid));
					while (true){
						o.println("0192238023");
						// String txt=i.readLine();
						// System.out.println(txt);
					}
				}
				catch (SocketException e){
					if (!e.getMessage().equals("Connection reset")){
						e.printStackTrace();
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}
				System.out.println(String.format("Client#%d disconected.",nsid));
				try{
					ns.close();
				}
				catch (Exception e){
					e.printStackTrace();
				}
			}
		}).start();
	}
}