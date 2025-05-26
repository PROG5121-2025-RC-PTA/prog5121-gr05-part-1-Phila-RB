package chatapp;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitAndMore extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ExitAndMore(Main main, Chat chat) {
		setBackground(new Color(39, 39, 39));
		setLayout(null);
		
		JButton sendMsg = new JButton("Send Messages");
		sendMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setChat(0);
			}
		});
		sendMsg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		sendMsg.setBackground(new Color(0, 0, 0));
		sendMsg.setForeground(new Color(255, 255, 255));
		sendMsg.setBounds(51, 207, 202, 47);
		add(sendMsg);
		
		JButton recentMsg = new JButton("Recent Sent Messages");
		recentMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				main.setRecentMsgs();
			}
		});
		recentMsg.setFont(new Font("Tahoma", Font.PLAIN, 15));
		recentMsg.setBackground(new Color(0, 0, 0));
		recentMsg.setForeground(new Color(255, 255, 255));
		recentMsg.setBounds(51, 315, 202, 47);
		add(recentMsg);
		
		JButton quit = new JButton("Quit");
		quit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showInternalMessageDialog(null, "You have sent " + chat.getMsgNum() + " messages.", "Info", JOptionPane.INFORMATION_MESSAGE);
				main.quit();
			}
		});
		quit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quit.setBackground(new Color(0, 0, 0));
		quit.setForeground(new Color(255, 255, 255));
		quit.setBounds(51, 418, 202, 47);
		add(quit);

	}
}
