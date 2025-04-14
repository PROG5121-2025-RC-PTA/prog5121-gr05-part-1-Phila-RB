package chatapp;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.CardLayout;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel mainPane;
	private static JPanel displayLogOrReg;
	private static Login login;
	private static Register register;
	private static WelcomePage welcomePage;
	
	public static UserLogin loggedInUser;
	public static String loggedInStatus;
	
	static ArrayList<Object> users = new ArrayList<Object>();
	
	public static void newUser(Object user) {
		users.add(user);
//		System.out.println(users.toString());
	}
	
	public static ArrayList<Object> getUser() {
		return users;
	}
	
	
	public static void setWelcome(String username) {
		if(welcomePage == null) {
			welcomePage = new WelcomePage(username);
		}
		mainPane.add(welcomePage, "welcomePage");
		CardLayout cl = (CardLayout)(mainPane.getLayout());
		cl.show(mainPane, "welcomePage");
	}
	
	public static void setLogin() {
		if(login == null) {
			login = new Login();
		}
		
		displayLogOrReg.add(login, "login");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "login");
	}
	public void setRegister() {
		if(register == null) {
			register = new Register();
		}
		displayLogOrReg.add(register, "register");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "register");
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
					
					setLogin();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 711);
		mainPane = new JPanel();
		mainPane.setBackground(new Color(255, 255, 255));
		mainPane.setForeground(new Color(0, 0, 0));
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 305, 650);
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(39, 39, 39));
//		mainPane.add(panel);
		panel.setLayout(null);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegister.setBackground(new Color(0, 0, 0));
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setRegister();
			}
		});
		btnRegister.setBounds(51, 254, 202, 47);
		panel.add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLogin();
			}
		});
		mainPane.setLayout(new CardLayout(0, 0));
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setBounds(51, 354, 202, 47);
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 305, 145);
		//
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Better");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 23, 258, 48);
		panel_1.add(lblNewLabel);
		
		JLabel lblBoard = new JLabel("Communication");
		lblBoard.setForeground(new Color(255, 255, 255));
		lblBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoard.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblBoard.setBounds(10, 68, 285, 48);
		panel_1.add(lblBoard);
		
		displayLogOrReg = new JPanel();
		displayLogOrReg.setBounds(325, 11, 698, 650);
		displayLogOrReg.setForeground(Color.WHITE);
		displayLogOrReg.setBackground(new Color(39, 39, 39));
		displayLogOrReg.setLayout(new CardLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.add(panel);
		panel_2.add(displayLogOrReg);
		mainPane.add(panel_2, "name_29668996412100");
		panel.add(panel_1);
		panel_2.setLayout(null);
	}
}
