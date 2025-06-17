package chatapp;


import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField typeMsg;
	private int msgsLeft;
	private int msgNum;
	private JPanel chatview;
	private Main main;
	private String list = "";
	
	//Arrays
	private ArrayList<JSONObject> sentMsgs = new ArrayList<>();
	private ArrayList<JSONObject> disregardedMsgs = new ArrayList<>();
	private ArrayList<JSONObject> storedMsgs = new ArrayList<>();
	private ArrayList<String> msgHashs = new ArrayList<>();
	private ArrayList<String> msgIds = new ArrayList<>();
	private JTextField searchMsgID;
	private JTextField searchMsgHash;
	private JTextField recipientNum;
	private JTextField searchRecipientMsgs;
	
	/**
	 * Create the panel.
	 */
	public Chat(int num, Main main) {
		this.main = main;
		this.chatview = new JPanel();
		this.msgsLeft = num;
		this.msgNum = 0;
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setToolTipText("Use the recipients number");
		add(panel);
		panel.setLayout(null);
		
		typeMsg = new JTextField();
		typeMsg.setFont(new Font("Tahoma", Font.PLAIN, 30));
		typeMsg.setHorizontalAlignment(SwingConstants.CENTER);
		typeMsg.setBounds(53, 291, 596, 48);
		panel.add(typeMsg);
		typeMsg.setColumns(10);
		
		try {
			File file = new File("allSentMsgs.json");
			if(!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			
		}
		
		
		JButton sendMsgButton = new JButton("Send");
		sendMsgButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				sendMessage(main);
			}
		});
		
		sendMsgButton.setBounds(319, 374, 65, 48);
		panel.add(sendMsgButton);
	    
	    searchMsgID = new JTextField();
	    searchMsgID.setForeground(Color.gray);
	    searchMsgID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(searchMsgID.getText().equals("Search message with message ID")) {
					searchMsgID.setText("");
				}
				searchMsgID.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchMsgID.getText().equals("")) {
					searchMsgID.setText("Search message with message ID");
					searchMsgID.setForeground(Color.gray);
				}
			}
	    });
	    searchMsgID.setText("Search message with message ID");
	    searchMsgID.setColumns(10);
	    searchMsgID.setBounds(10, 11, 232, 26);
	    panel.add(searchMsgID);
	    
	    JButton searchMsg = new JButton("Send");
	    searchMsg.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		searchMsg(sentMsgs, searchMsgID.getText());
	    	}
	    });

	    searchMsg.setBounds(252, 11, 65, 26);
	    panel.add(searchMsg);
	    
	    searchMsgHash = new JTextField();
	    searchMsgHash.setForeground(Color.gray);
	    searchMsgHash.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(searchMsgHash.getText().equals("Delete message with message hash")) {
					searchMsgHash.setText("");
				}
				searchMsgHash.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchMsgHash.getText().equals("")) {
					searchMsgHash.setText("Delete message with message hash");
					searchMsgHash.setForeground(Color.gray);
				}
			}
	    });
	    searchMsgHash.setText("Delete message with message hash");
	    searchMsgHash.setColumns(10);
	    searchMsgHash.setBounds(374, 11, 232, 26);
	    panel.add(searchMsgHash);
	    
	    JButton deleteMsg = new JButton("Send");
	    deleteMsg.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		
	    		if(searchMsgHash.getText().length() < 4) {
	    			JOptionPane.showInternalMessageDialog(null, "The message hash you entered is invalid", "Error", JOptionPane.WARNING_MESSAGE);
	    			return;
	    		}
	    		
	    		try {
	    			Integer.parseInt(searchMsgHash.getText().substring(0,2));
	    			Integer.parseInt(searchMsgHash.getText().substring(3,4));
	    			if(!searchMsgHash.getText().substring(2,3).equals(":")) {
	    				JOptionPane.showInternalMessageDialog(null, "The message hash you entered is invalid", "Error", JOptionPane.WARNING_MESSAGE);
	    				return;
	    			}
	    			
		    		for(int i = 0; i < sentMsgs.size(); i++) {
		    			JSONObject obj = sentMsgs.get(i);
		    			if(obj.get("Message Hash").equals(searchMsgHash.getText())) {
		    				sentMsgs.remove(i);
		    				
							try {
								//if you want then file to keep data pass true in filewriter
								String jsonString = new String(Files.readAllBytes(Paths.get("allSentMsgs.json")));
								
								JSONArray jArr;
								if(!jsonString.equals("")) {
									jArr = new JSONArray(jsonString);
								}
								else {
									jArr = new JSONArray();
								}
								
					    		for(int j = 0; j < jArr.length(); j++) {
					    			JSONObject obj2 = jArr.getJSONObject(j);
					    			if(obj2.get("Message Hash").toString().equals(searchMsgHash.getText())) {
					    				jArr.remove(i);
					    				break;
					    			}
					    		}
					    		
					    		FileWriter write = new FileWriter("allSentMsgs.json");
								write.write(jArr.toString());
								write.flush();
								write.close();
							} 
							catch (IOException err) {
								err.printStackTrace();
							}
		    			}
		    		    String stored = "Message Deleted:\n"
		    	                    + "Recipient: " + obj.get("Recipient") + "\n"
		    	                    + "Message: " + obj.get("Message") ;
		    	          
		    		    JOptionPane.showMessageDialog(null, stored);
		    		    searchMsgHash.setText("");
		    		}
	    		}
	    		catch(NumberFormatException err) {
	    			JOptionPane.showInternalMessageDialog(null, "The message hash you entered is invalid", "Error", JOptionPane.WARNING_MESSAGE);
	    			return;
	    		}
//	    		if() {
//	    			JOptionPane.showInternalMessageDialog(null, "The message hash you entered is invalid", "Error", JOptionPane.WARNING_MESSAGE);
//	    			return;
//	    		}

	    	}
	    });
	    deleteMsg.setBounds(616, 11, 65, 26);
	    panel.add(deleteMsg);
	    
	    recipientNum = new JTextField();
	    recipientNum.setHorizontalAlignment(SwingConstants.CENTER);
	    recipientNum.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    recipientNum.setColumns(10);
	    recipientNum.setBounds(216, 173, 270, 48);
	    panel.add(recipientNum);
	    
	    JLabel lblNewLabel = new JLabel("Recipient");
	    lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 35));
	    lblNewLabel.setBounds(285, 141, 132, 32);
	    panel.add(lblNewLabel);
	    
	    JLabel lblMessage = new JLabel("Message");
	    lblMessage.setFont(new Font("Times New Roman", Font.PLAIN, 35));
	    lblMessage.setBounds(290, 253, 122, 42);
	    panel.add(lblMessage);
	    
	    JButton viewLongestMessage = new JButton("View longest message");
	    viewLongestMessage.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		viewLongMsg(sentMsgs);
	    	}
	    });
	    viewLongestMessage.setBounds(53, 374, 214, 48);
	    panel.add(viewLongestMessage);
	    
	    JButton viewAllSent = new JButton("View all sent and recived messages");
	    viewAllSent.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		JSONArray  arr = new JSONArray();
	    		for(JSONObject obj : sentMsgs) {
	    			obj.put("from", "user");
	    			arr.put(obj);
	    		}
	    		
				//reading from stored json file
				try {
					String jsonString = new String(Files.readAllBytes(Paths.get("allSentMsgs.json")));
					if(!jsonString.equals("")) {
						JSONArray jArr = new JSONArray(jsonString);
						
			    		for(int i = 0; i < jArr.length(); i++) {
			    			JSONObject obj = jArr.getJSONObject(i);
			    			if(obj.get("Recipient").toString().equals(main.loggedInUser.cellNum)) {
			    				obj.put("from", "other");
			    				arr.put(obj);
			    			}
			    		}
					}
				} 
				catch (IOException err) {
					err.printStackTrace();
				}
				
				if(arr.isEmpty()) {
	    			JOptionPane.showInternalMessageDialog(null, "You have not sent or recieved and messages", "Error", JOptionPane.WARNING_MESSAGE);
	    			return;
	    		}
	    		SentAndRecieved m = new SentAndRecieved(arr);
	    		m.setVisible(true);
	    		System.out.println(AllMessagesSent.get().toString());
	    	}
	    });
	    viewAllSent.setBounds(435, 374, 214, 48);
	    panel.add(viewAllSent);
	    
	    searchRecipientMsgs = new JTextField();
	    searchRecipientMsgs.setForeground(Color.gray);
	    searchRecipientMsgs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(searchRecipientMsgs.getText().equals("Search recipient specific messages")) {
					searchRecipientMsgs.setText("");
				}
				searchRecipientMsgs.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(searchRecipientMsgs.getText().equals("")) {
					searchRecipientMsgs.setText("Search recipient specific messages");
					searchRecipientMsgs.setForeground(Color.gray);
				}
			}
	    });
	    searchRecipientMsgs.setText("Search recipient specific messages");
	    searchRecipientMsgs.setColumns(10);
	    searchRecipientMsgs.setBounds(374, 54, 232, 26);
	    panel.add(searchRecipientMsgs);
	    
	    JButton searchRec = new JButton("Send");
	    searchRec.addMouseListener(new MouseAdapter() {
	    	@Override
	    	public void mouseClicked(MouseEvent e) {
	    		JSONArray arr = new JSONArray();
	    		for(JSONObject obj : sentMsgs) {
	    			if(obj.get("Recipient").toString().equals(searchRecipientMsgs.getText())) {
	    				obj.put("from", "user");
		    			arr.put(obj);
	    			}
	    		}
	    		
	    		if(arr.isEmpty()) {
	    			JOptionPane.showInternalMessageDialog(null, "The recipient you entered is invalid", "Error", JOptionPane.WARNING_MESSAGE);
	    			return;
	    		}
	    		
	    		SentAndRecieved m = new SentAndRecieved(arr);
	    		m.setVisible(true);
	    		searchRec.setText("");
	    	}
	    });
	    searchRec.setBounds(616, 54, 65, 26);
	    panel.add(searchRec);
	}
	
	public void msgDetails(JSONObject obj) {
        String stored = "Stored Information:\n"
                  + "MessageID: " + obj.get("MessageID").toString() + "\n"
                  + "MessageHash: " + obj.get("Message Hash") + "\n"
                  + "Recipient: " + obj.get("Recipient") + "\n"
                  + "Message: " + obj.get("Message") ;
        
	    JOptionPane.showMessageDialog(null, stored);
	    	
	}
	
	public void sendMessage(Main main) {
		Message message = new Message();
		long id = message.createID();
		String hash = message.createMessageHash(id, msgNum, typeMsg.getText());
		
		boolean checkCell = UserLogin.checkCellPhoneNumber(recipientNum.getText());
		if(checkCell == false) {
			JOptionPane.showInternalMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code2", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		JSONObject obj = new JSONObject();
		obj.put("MessageID", id);
		obj.put("Message Hash", hash);
		obj.put("Recipient", recipientNum.getText());
		obj.put("Message", typeMsg.getText());
	
		message.sentMessage(typeMsg.getText(), new MessageFate() {
			@Override
			public void sending() {
				if(typeMsg.getText().length() <= 250 && typeMsg.getText().length() > 0) {				
					try {
						list += typeMsg.getText();
						String jsonString = new String(Files.readAllBytes(Paths.get("allSentMsgs.json")));
						JSONArray jArr;
						
						if(!jsonString.equals("")) {
							jArr = new JSONArray(jsonString);
						}
						else {
							jArr = new JSONArray();
						}
						
						jArr.put(obj);
						FileWriter write = new FileWriter("allSentMsgs.json");
						write.write(jArr.toString());
						write.close();
						sentMsgs.add(obj);
						msgDetails(obj);
						msgNum ++;
						msgsLeft --;
						typeMsg.setText("");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}

			@Override
			public void disregarding() {
				disregardedMsgs.add(obj);
				typeMsg.setText("");
				System.out.println(disregardedMsgs.toString());
			}

			@Override
			public void storing() {
				if(typeMsg.getText().length() <= 250) {
					File jFile = new File(main.loggedInUser.cellNum+"JsonFile.json");
					
					if(!jFile.exists()) {
						try {
							jFile.createNewFile();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					
					try {
						//if you want then file to keep data pass true in filewriter
						String jsonString = new String(Files.readAllBytes(Paths.get(main.loggedInUser.cellNum+"JsonFile.json")));
						JSONArray jArr;
						if(!jsonString.equals("")) {
							jArr = new JSONArray(jsonString);
						}
						else {
							jArr = new JSONArray();
						}
						
						FileWriter write = new FileWriter(main.loggedInUser.cellNum+"JsonFile.json");
						jArr.put(obj);
						write.write(jArr.toString());
						write.flush();
						write.close();
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					
					//reading from stored json file
					try {
						String jsonString = new String(Files.readAllBytes(Paths.get(main.loggedInUser.cellNum+"JsonFile.json")));
						if(!jsonString.equals("")) {
							JSONArray jArr = new JSONArray(jsonString);
							storedMsgs.add(jArr.getJSONObject(jArr.length()-1));
						}
						System.out.println(storedMsgs.toString());
					} 
					catch (IOException e) {
						e.printStackTrace();
					}
					
					typeMsg.setText("");
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}, -1);
	}
	
	public String viewLongMsg(ArrayList<JSONObject> arrMsgs) {
		String msg = "";
		int msgSize = 0;
		for(int i = 0; i < arrMsgs.size(); i++) {
			int size = arrMsgs.get(i).get("Message").toString().length();
			if(size > msgSize) {
				msg = arrMsgs.get(i).get("Message").toString();
				msgSize = size;
			}
		}
		
		if(msg.isEmpty()) {
			JOptionPane.showInternalMessageDialog(null, "You have not sent any messages", "Error", JOptionPane.WARNING_MESSAGE);
			return "";
		}
        String stored = "The longest message you have sent is:\n"
        				+ msg;
      
        JOptionPane.showMessageDialog(null, stored);
        return msg;
	}
	
	public JSONObject searchMsg(ArrayList<JSONObject> arrMsgs, String id) {
		try {
			Long.parseLong(id);
			
    		for(int i = 0; i < arrMsgs.size(); i++) {
    			JSONObject obj = arrMsgs.get(i);
    			
    			if(arrMsgs.get(i).get("MessageID").equals(Long.parseLong(id))) {
    	            String stored = "Message:\n"
                    + "Recipient: " + obj.get("Recipient") + "\n"
                    + "Message: " + obj.get("Message") ;
          
    	            JOptionPane.showMessageDialog(null, stored);
    	            searchMsgID.setText("");
    	            System.out.println(obj.toString());
    	            return obj;
    			}
    		}
    		JOptionPane.showInternalMessageDialog(null, "A message with that ID does not exist", "Error", JOptionPane.WARNING_MESSAGE);
    	}
		catch(NumberFormatException err) {
			JOptionPane.showInternalMessageDialog(null, "Please enter a valid ID number", "Error", JOptionPane.WARNING_MESSAGE);
		}
		return new JSONObject();
	}
	
	public int getMsgNum() {
		return msgNum;
	}
}
