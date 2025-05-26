package chatapp;

import java.net.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
public class Client{

		private Socket sock;
		private BufferedReader buffRead;
		private BufferedWriter buffWrite;
		private String userName;
		private String cellNum;
		private final ExecutorService executor = Executors.newSingleThreadExecutor();
		
		public Client(Socket sock, String userName, String cellNum) {
			try {
				this.sock = sock;
				buffRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				buffWrite = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
				this.userName = userName;
				this.cellNum = cellNum;
			}
			catch(IOException e) {
				try {
					sock.close();
					buffRead.close();
					buffWrite.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		public String getUserName() {
			return userName;
		}
		public String getCellNum() {
			return cellNum;
		}
		
		public void sendMsg(String msg, SendMsg send) {
			 executor.submit(() -> {
					try {
						buffWrite.write(msg);
						buffWrite.newLine();
						buffWrite.flush();
						
						send.msgToSend(msg);
					} catch (IOException e) {
						try {
							sock.close();
							buffRead.close();
							buffWrite.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				
			});
		}
		
		public void readMsg(ListenForMsg msg) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String chatMsgs;
						while(sock.isConnected()) {
							chatMsgs = buffRead.readLine();
		                    if (chatMsgs != null && msg != null) {
		                        msg.msgReceived(chatMsgs);
		                    }
						}
					} catch (IOException e) {
						try {
							sock.close();
							buffRead.close();
							buffWrite.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
			}).start();;
		}
	
}
