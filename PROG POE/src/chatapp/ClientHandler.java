package chatapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler extends Thread{
	static ArrayList<ClientHandler> clients = new ArrayList<>();
	Socket sock;
	BufferedReader buffR;
	BufferedWriter buffW;
	String userName;
	
	public ClientHandler(Socket sock) {
		try {
			this.sock = sock;
			buffR = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			buffW = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			userName = buffR.readLine();
			clients.add(this);
			relayMsg("Server: " + userName + " Has joined the conversation");
		} catch (IOException e) {
			closeEverything(buffR, buffW, sock);
		}
	}
	
	public void closeEverything(BufferedReader buffR, BufferedWriter buffW, Socket sock) {
		try {
			buffR.close();
			buffW.close();
			sock.close();
		} catch (IOException e) {
			closeEverything(buffR, buffW, sock);
		}
	}
	
	public void relayMsg(String msg) {
		for(ClientHandler client: clients) {
			if(!client.userName.equals(userName)) {
				try {
					client.buffW.write(msg);
					client.buffW.newLine();
					client.buffW.flush();
				} catch (IOException e) {
					closeEverything(buffR, buffW, sock);
				}
			}
		}
	}
	
	@Override
	public void run() {
		String msg;
		while(sock.isConnected()) {
			try {
				msg = buffR.readLine();
				relayMsg(msg);
			} catch (IOException e) {
				relayMsg("Server: " + this.userName + " has left the conversation");
				closeEverything(buffR, buffW, sock);
				clients.remove(this);
				break;
			}
					
		}
	}
}
