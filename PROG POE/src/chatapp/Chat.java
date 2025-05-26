package chatapp;

import javax.swing.JPanel;
import java.awt.BorderLayout;
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

import org.json.JSONObject;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Chat extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField typeMsg;
	private Client client;
	private int msgsLeft;
	private int msgNum;
	private JPanel chatview;
	private Main main;
	private String list = "";
	/**
	 * Create the panel.
	 */
	public Chat(Client client, int num, Main main) {
		this.main= main;
		this.chatview = new JPanel();
		this.client = client;
		this.msgsLeft = num;
		this.msgNum = 0;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		typeMsg = new JTextField();
		typeMsg.setBounds(10, 440, 596, 48);
		panel.add(typeMsg);
		typeMsg.setColumns(10);
		
		JButton sendMsgButton = new JButton("Send");
		
		sendMsgButton.setBounds(616, 440, 65, 48);
		panel.add(sendMsgButton);
		
	    // Your chatview panel holds messages
	    chatview = new JPanel();
	    chatview.setLayout(new GridBagLayout());//gridbaglayout to place our msgs were we want
	    GridBagConstraints gbc = new GridBagConstraints();//dictates were our element placements
	    
        GridBagConstraints fillerGbc = new GridBagConstraints();//purely to have bottom align our msgs
        fillerGbc.gridx = 0;
        fillerGbc.gridy = 0;
        fillerGbc.weighty = 1.0;
        fillerGbc.fill = GridBagConstraints.VERTICAL;
        chatview.add(Box.createVerticalGlue(), fillerGbc);
        
        GridBagConstraints filler2Gbc = new GridBagConstraints();//purely to have bottom align our msgs
        fillerGbc.gridx = 0;
        fillerGbc.gridy = 0;
        fillerGbc.weightx = 1.0;
        fillerGbc.fill = GridBagConstraints.HORIZONTAL	;
        fillerGbc.anchor = GridBagConstraints.EAST;
        chatview.add(Box.createHorizontalGlue(), filler2Gbc);
        
		client.sendMsg(client.getUserName(), new SendMsg() {
			@Override
			public void msgToSend(String msg) {
				SwingUtilities.invokeLater(() -> {
			        JTextPane textpane = new JTextPane();
			        textpane.setText(client.getUserName());
			        textpane.setMaximumSize(new Dimension(650, 40)); // Prevent stretching
			        textpane.setEditable(false);
    			
	                GridBagConstraints gbc = new GridBagConstraints();
			        gbc.gridx = 0;
			        gbc.insets = new Insets(5, 5, 5, 5);
			        gbc.anchor = GridBagConstraints.WEST;
			        gbc.fill = GridBagConstraints.HORIZONTAL;
			        gbc.weightx = 0.3;
			        chatview.add(textpane, gbc);
			        
			        chatview.revalidate();
			        chatview.repaint();
			     
			        msgsLeft--;
			        typeMsg.setText("");
				});
			}}
		);
        
		sendMsgButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Message message = new Message();
				long id = message.createID();
				String hash = message.createMessageHash(id, msgNum, typeMsg.getText());
					message.sentMessage(typeMsg.getText(), new SendTheMessageAlready() {
						@Override
						public void sending() {
							if(typeMsg.getText().length() <= 250) {
								sendTheMessage(id, hash);
								list += typeMsg.getText();
							}
							else {
								JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
							}
						}

						@Override
						public void disregarding() {
							typeMsg.setText("");
						}

						@Override
						public void storing() {
							if(typeMsg.getText().length() <= 250) {
								File jFile = new File(client.getUserName()+"JsonFile.txt");
								
								JSONObject obj = new JSONObject();
								obj.put("MessageID", id);
								obj.put("Message Hash", hash);
								obj.put("Recipient", client.getCellNum());
								obj.put("Message", typeMsg.getText());
								
								if(!jFile.exists()) {
									try {
										jFile.createNewFile();
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
								
								try {
									//if you want the text file to keep data pass true in filewriter
									FileWriter write = new FileWriter(client.getUserName()+"JsonFile.txt");
									write.write(obj.toString());
									write.close();
								} 
								catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								typeMsg.setText("");
							}
							else {
								JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
							}
						}
					});
				}
		});
//		
//        
        client.readMsg(new ListenForMsg() {
			@Override
			public void msgReceived(String msg) {
				SwingUtilities.invokeLater(() -> {
			        JTextPane testpane = new JTextPane();
			        testpane.setText(msg);
			        testpane.setMaximumSize(new Dimension(650, 40)); // Prevent stretching
			        testpane.setEditable(false);
			        
			        gbc.gridx = 0;
			        gbc.insets = new Insets(5, 5, 5, 5);
			        gbc.anchor = GridBagConstraints.WEST;
			        gbc.fill = GridBagConstraints.HORIZONTAL;
			        gbc.weightx = 0.3;
			        chatview.add(testpane, gbc);
			        
			        chatview.revalidate();
			        chatview.repaint();
				});
			}
        });

	    // Wrap chatview in a JScrollPane
	    JScrollPane sc = new JScrollPane(chatview);

	    sc.setBounds(10, 11, 671, 418); // Set bounds instead of chatview
	    sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    panel.add(sc); // Add the scroll pane, not chatview directly
	}
	
	public void sendTheMessage(long id, String hash) {
		client.sendMsg(client.getUserName() + ": " + typeMsg.getText(), new SendMsg() {
			@Override
			public void msgToSend(String msg) {
		        if(msgsLeft < 1) {
		        	JOptionPane.showConfirmDialog(null, "You have sent all your messages");
		        	new Message().returnTotalMessages(msgNum);
		        	return;
		        }
				SwingUtilities.invokeLater(() -> {
			        JTextPane textpane = new JTextPane();
			        textpane.setText(client.getUserName() + ": "+ typeMsg.getText());
			        textpane.setMaximumSize(new Dimension(650, 40)); // Prevent stretching
			        textpane.setEditable(false);
			        
	                String stored = "Stored Information:\n"
	                  + "MessageID: " + id + "\n"
                      + "MessageHash" + hash + "\n"
                      + "Recipent" + client.getCellNum() + "\n"
                      + "Message" + msg;
          
	                textpane.addMouseListener(new MouseAdapter() {
	              	@Override
		    			public void mouseClicked(MouseEvent e) {
		    				JOptionPane.showMessageDialog(null, stored);
		    			}
	  				});
    			
	                GridBagConstraints gbc = new GridBagConstraints();
			        gbc.gridx = 0;
			        gbc.insets = new Insets(5, 5, 5, 5);
			        gbc.anchor = GridBagConstraints.WEST;
			        gbc.fill = GridBagConstraints.HORIZONTAL;
			        gbc.weightx = 0.3;
			        chatview.add(textpane, gbc);
			        
			        chatview.revalidate();
			        chatview.repaint();
			     
			        msgsLeft--;
			        msgNum ++;
			        typeMsg.setText("");
				});
			}}
		);
	}
	
	public int getMsgNum() {
		return msgNum;
	}
}
