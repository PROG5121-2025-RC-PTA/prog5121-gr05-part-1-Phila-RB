package chatapp;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToolBar;
<<<<<<< HEAD
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Register extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField cellNum;
	public JTextField passWord;
	public JTextField userName;

	/**
	 * Create the panel.
	 */
	public Register(Main main) {
		setBackground(new Color(39, 39, 39));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cellphone Number");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(188, 387, 334, 36);
		add(lblNewLabel);
		
		cellNum = new JTextField();
		cellNum.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		cellNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(cellNum.getText().equals("e.g +27 983046340")) {
					cellNum.setText("");
				}
				cellNum.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				cellNum.setText(cellNum.getText().replaceAll(" ", ""));
				if(cellNum.getText().equals("")) {
					cellNum.setText("e.g +27 983046340");
					cellNum.setForeground(Color.gray);
				}
			}
		});
		cellNum.setText("e.g +27 983046340");
		cellNum.setForeground(Color.gray);
		cellNum.setColumns(10);
		cellNum.setBounds(188, 434, 334, 41);
		add(cellNum);
		
		JLabel lblLogin = new JLabel("Register");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
		lblLogin.setBounds(253, 75, 221, 47);
		add(lblLogin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerUser(main);
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSubmit.setBounds(188, 536, 147, 36);
		add(btnSubmit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setForeground(new Color(128, 128, 128));
				userName.setText("e.g Jak_1");
				passWord.setForeground(new Color(128, 128, 128));
				passWord.setText("e.g Jak!super123");
				cellNum.setText("e.g +27 983046340");
				cellNum.setForeground(Color.gray);
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 25));
		btnReset.setBounds(375, 536, 147, 36);
		add(btnReset);
		
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
		passWord.setBounds(188, 335, 334, 41);
		add(passWord);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setForeground(new Color(255, 255, 255));
		lblPassword_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblPassword_1.setBounds(188, 288, 221, 36);
		add(lblPassword_1);
		
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
		userName.setToolTipText("Please enter your Username that is no longer than 5 characters and contains an underscore");
		userName.createToolTip();
		userName.setColumns(10);
		userName.setBounds(188, 236, 334, 41);
		add(userName);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(188, 189, 221, 36);
		add(lblNewLabel_1);
	}
	
	public void registerUser(Main main) {
		UserLogin userLog = new UserLogin(userName.getText(), passWord.getText(), cellNum.getText());
		//all text fields filled in
		if(userName.getText().equals("e.g Jak_1")) {
			JOptionPane.showInternalMessageDialog(null, "Please enter your username", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(passWord.getText().equals("e.g Jak!super123")) {
			JOptionPane.showInternalMessageDialog(null, "Please enter your password", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(cellNum.getText().equals("e.g +27 983046340")) {
			JOptionPane.showInternalMessageDialog(null, "Please enter Your phone number", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//username, password and phone number meet requirements
		if(userLog.checkUserName(userName.getText()) == true) {
			JOptionPane.showInternalMessageDialog(null, "Username successfully captured", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Username is not correctly formatted, please ensure the username contains an underscore and is no more than 5 characters in length", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(userLog.checkPasswordComplexity(passWord.getText()) == true) {
			JOptionPane.showInternalMessageDialog(null, "password successfully captured", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Password is not correctly formatted correctly, please ensure the password contains atleast 8 characters," +
	                "a capital letter, a number and a special character.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(userLog.checkCellPhoneNumber(cellNum.getText()) == true) {
			JOptionPane.showInternalMessageDialog(null, "Cell phone number successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
		}
		else {
			JOptionPane.showInternalMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code","Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		
		userName.setForeground(new Color(128, 128, 128));
		passWord.setForeground(new Color(128, 128, 128));
		cellNum.setForeground(new Color(128, 128, 128));
		userName.setText("e.g Jak_1");
		passWord.setText("e.g Jak!super123");
		cellNum.setText("e.g +27 983046340");
//		System.out.println(userLog);
		main.newUser(userLog);
		main.setLogin();
=======
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextPane;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Register extends JPanel {

	private static final long serialVersionUID = 1L;
	public JTextField cellNum;
	public JTextField passWord;
	public JTextField userName;

	/**
	 * Create the panel.
	 */
	public Register() {
		setBackground(new Color(39, 39, 39));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cellphone Number");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setBounds(188, 387, 334, 36);
		add(lblNewLabel);
		
		cellNum = new JTextField();
		cellNum.setFont(new Font("Arial Narrow", Font.PLAIN, 20));
		cellNum.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(cellNum.getText().equals("e.g +27 983046340")) {
					cellNum.setText("");
				}
				cellNum.setForeground(Color.black);
		
			}
			@Override
			public void focusLost(FocusEvent e) {
				cellNum.setText(cellNum.getText().replaceAll(" ", ""));
				if(cellNum.getText().equals("")) {
					cellNum.setText("e.g +27 983046340");
					cellNum.setForeground(Color.gray);
				}
			}
		});
		cellNum.setText("e.g +27 983046340");
		cellNum.setForeground(Color.gray);
		cellNum.setColumns(10);
		cellNum.setBounds(188, 434, 334, 41);
		add(cellNum);
		
		JLabel lblLogin = new JLabel("Register");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 40));
		lblLogin.setBounds(253, 75, 221, 47);
		add(lblLogin);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setBackground(new Color(255, 255, 255));
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerUser();
			}
		});
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 25));
		btnSubmit.setBounds(188, 536, 147, 36);
		add(btnSubmit);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setForeground(new Color(128, 128, 128));
				userName.setText("e.g Jak_1");
				passWord.setForeground(new Color(128, 128, 128));
				passWord.setText("e.g Jak!super123");
				cellNum.setText("e.g +27 983046340");
				cellNum.setForeground(Color.gray);
			}
		});
		btnReset.setFont(new Font("Arial", Font.PLAIN, 25));
		btnReset.setBounds(375, 536, 147, 36);
		add(btnReset);
		
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
		passWord.setBounds(188, 335, 334, 41);
		add(passWord);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setForeground(new Color(255, 255, 255));
		lblPassword_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblPassword_1.setBounds(188, 288, 221, 36);
		add(lblPassword_1);
		
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
		userName.setToolTipText("Please enter your Username that is no longer than 5 characters and contains an underscore");
		userName.createToolTip();
		userName.setColumns(10);
		userName.setBounds(188, 236, 334, 41);
		add(userName);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel_1.setBounds(188, 189, 221, 36);
		add(lblNewLabel_1);
	}
	
	public void registerUser() {
		//all text fields filled in
		if(userName.getText().equals("e.g Jak_1")) {
			Errors.m("Please enter your username");
			return;
		}
		if(passWord.getText().equals("e.g Jak!super123")) {
			Errors.m("Please enter your password");
			return;
		}
		if(cellNum.getText().equals("e.g +27 983046340")) {
			Errors.m("Please enter Your phone number");
			return;
		}
		
		//username, password and phone number meet requirements
		if(UserLogin.checkUserName(userName.getText()) == true) {
			Errors.m("Username successfully captured");
		}
		else {
			Errors.m("Username is not correctly formatted, please ensure the username contains an underscore and is no more than 5 characters in length");
			return;
		}
		
		if(UserLogin.checkPasswordComplexity(passWord.getText()) == true) {
			Errors.m("password successfully captured");
		}
		else {
			Errors.m("Password is not correctly formatted correctly, please ensure the password contains atleast 8 characters," +
	                "a capital letter, a number and a special character.");
			return;
		}
		
		if(UserLogin.checkCellPhoneNumber(cellNum.getText()) == true) {
			Errors.m("Cell phone number successfully added");
		}
		else {
			Errors.m("Cell phone number incorrectly formatted or does not contain international code");
			return;
		}
		UserLogin newUser = new UserLogin(userName.getText(), passWord.getText(), cellNum.getText());
		
		userName.setForeground(new Color(128, 128, 128));
		passWord.setForeground(new Color(128, 128, 128));
		cellNum.setForeground(new Color(128, 128, 128));
		userName.setText("e.g Jak_1");
		passWord.setText("e.g Jak!super123");
		cellNum.setText("e.g +27 983046340");
		
		Main.newUser(newUser);
		Main.setLogin();
>>>>>>> branch 'master' of https://github.com/PROG5121-2025-RC-PTA/prog5121-gr05-part-1-Phila-RB
	}
}
