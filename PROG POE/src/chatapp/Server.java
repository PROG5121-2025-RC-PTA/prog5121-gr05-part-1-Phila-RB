package chatapp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(12345);
			System.out.println("Server has started");
			while(!server.isClosed()) {
				Socket sock = server.accept();
				System.out.println("A new client has joined the server");
				ClientHandler client = new ClientHandler(sock);
				client.start();
			}
		} catch (IOException e) {
			try {
				server.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
