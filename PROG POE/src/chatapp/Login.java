package chatapp;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Login extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField userName;
	public JTextField passWord;

	/**
	 * Create the panel.
	 */
	public Login() {
		setBackground(new Color(39, 39, 39));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(188, 199, 221, 36);
		add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 30));
		lblPassword.setBounds(188, 326, 221, 36);
		add(lblPassword);
		
		userName = new JTextField();
		userName.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		userName.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(userName.getText().equals("e.g Jak_1")) {
					userName.setText("");
				}
				userName.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				userName.setText(userName.getText().replaceAll(" ", ""));
				if(userName.getText().equals("")) {
					userName.setText("e.g Jak_1");
					userName.setForeground(Color.gray);
				}
			}
		});
		userName.setForeground(new Color(128, 128, 128));
		userName.setText("e.g Jak_1");
		userName.setColumns(10);
		userName.setBounds(188, 246, 334, 41);
		add(userName);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
		lblLogin.setBounds(244, 100, 221, 36);
		add(lblLogin);
		
		passWord = new JTextField();
		passWord.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		passWord.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(passWord.getText().equals("e.g Jak!super123")) {
					passWord.setText("");
				}
				passWord.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				passWord.setText(passWord.getText().replaceAll(" ", ""));
				if(passWord.getText().equals("")) {
					passWord.setText("e.g Jak!super123");
					passWord.setForeground(Color.gray);
				}
			}
		});
		passWord.setForeground(new Color(128, 128, 128));
		passWord.setText("e.g Jak!super123");
		passWord.setColumns(10);
		passWord.setBounds(188, 373, 334, 41);
		add(passWord);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userName.getText().equals("e.g Jak_1")) {
					Errors.m("Please enter your username");
					return;
				}
				if(passWord.getText().equals("e.g Jak!super123")) {
					Errors.m("Please enter your password");
					return;
				}
				loginUser();
			}
		});
		
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSubmit.setBounds(188, 495, 147, 36);
		add(btnSubmit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setForeground(new Color(128, 128, 128));
				userName.setText("e.g Jak_1");
				passWord.setForeground(new Color(128, 128, 128));
				passWord.setText("e.g Jak!super123");
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 25));
		btnReset.setBounds(375, 495, 147, 36);
		add(btnReset);

	}
	
	public void loginUser(){
		ArrayList<Object> users = Main.getUser();
		
		if(users.size() == 0) {
			Errors.m("User name or password incorrect, please try again");
			Main.loggedInStatus = UserLogin.returnLoginStatus();
			return;
		}

		for(Object user : users) {
			String name = ((UserLogin) user).userName;
			String password = ((UserLogin) user).passWord;
			
			
			if(userName.getText().equals(name) && passWord.getText().equals(password)) {
				Errors.m("Welcome " + name);
				Main.loggedInUser = (UserLogin)user;
				Main.loggedInStatus = UserLogin.returnLoginStatus();
				Main.setWelcome(name);
				
				passWord.setForeground(new Color(128, 128, 128));
				userName.setForeground(new Color(128, 128, 128));
				userName.setText("e.g Jak_1");
				passWord.setText("e.g Jak!super123");
				return;
			}
		}
//		System.out.println("user name or password incorrect");
		Main.loggedInStatus = UserLogin.returnLoginStatus();
		Errors.m("user name or password incorrect");
		return;
	}
	
	
	
	
}
