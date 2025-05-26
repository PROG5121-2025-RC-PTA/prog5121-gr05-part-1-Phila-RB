package chatapp;

import javax.swing.JOptionPane;
import javax.swing.JOptionPane;

public class Message {
	private long msgID;
	private String msgHash;
	private String recipient;
	private String msg;
	private int msgNum;
	
	public long createID() {
		long id = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		boolean verdict = new Message().checkMessageId(String.valueOf(id));
		if(verdict == false) {
			System.out.println("for some reason the id isnt 10 digits?");
			createID();
		}
		return id;
	}
	
	public boolean checkMessageId(String msgID) {
		if(msgID.length() != 10) {
			System.out.println(msgID.length());
			return false;
		}
		return true;
	}
	
	public String createMessageHash(long msgId,int msgNum, String msg) {
		int num = Integer.parseInt(String.valueOf(msgId).substring(0,2));
		String[] split = msg.split(" ");
		String hash = num + ":" + msgNum + ":" + split[0].toUpperCase() + split[split.length - 1].toUpperCase();
		return hash;
	}
	
	
	public String sentMessage(String msg, SendTheMessageAlready send) {
		int output = getUserChoice();
		
		if(output == 0) {
			send.sending();
		}
		
		if(output == 1) {
			System.out.println("disregarding");
			send.disregarding();
		}
		
		if(output == 2) {
			System.out.println("storing msg");
			storeMessage(send);
		}
		
		return "";
	}
    protected int getUserChoice() {
        String[] options = {"Send Message", "Disregard Message", "Store Message"};
        return JOptionPane.showOptionDialog(null,
                "Please confirm what to do with the message.",
                "Info",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }
    
	
	public String printMessage(String list) {
		return list;
	}
	
	public int returnTotalMessages(int msgsSent) {
		JOptionPane.showInternalMessageDialog(null, msgsSent, "Info", JOptionPane.INFORMATION_MESSAGE);
		return msgsSent;
	}
	
	public void storeMessage(SendTheMessageAlready send) {
		send.storing();
	}
}

