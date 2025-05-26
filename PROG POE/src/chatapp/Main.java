package chatapp;

<<<<<<< HEAD
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONObject;


public class Main extends JFrame {

	private final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JPanel panel_2;
	private JPanel panel;
	private JPanel displayLogOrReg;
	private JPanel holdLogReg;
	private Login login;
	private Chat chat;
	private Register register;
	private WelcomePage welcomePage;
	private ExitAndMore quitPage;
	private RecentMsgs recentMsgs;
	private Main main;
	private Component content;
	private Component logRegBtns;
	public UserLogin loggedInUser = null;
	public String loggedInStatus = null;
	private Client client;
	
	static ArrayList<Object> users = new ArrayList<Object>();
	
	public void newUser(Object user) {
		users.add(user);
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public ArrayList<Object> getUser() {
		return users;
	}
	
	public void setLogin() {
		if(login == null) {
			login = new Login(main);
		}
		
		displayLogOrReg.add(login, "login");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "login");
	}
	
	public void setWelcome(String username) {
		if(welcomePage == null) {
			welcomePage = new WelcomePage(username, main);
		}
		mainPane.add(welcomePage, "welcomePage");
		CardLayout cl = (CardLayout)(mainPane.getLayout());
		cl.show(mainPane, "welcomePage");
	}
	
	public void setContent() {
		if(content == null) {
			content = content();
		}
		
		mainPane.add(content, "main");
		CardLayout cl = (CardLayout)(mainPane.getLayout());
		cl.show(mainPane, "main");
	}
	
	public void setChat(int num) {
		if(chat == null) {
			chat = new Chat(client, num, main);
		}
		displayLogOrReg.add(chat, "chat");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "chat");
	}
	
	public void setRegister() {
		
		if(register == null) {
			register = new Register(main);
		}
		displayLogOrReg.add(register, "register");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "register");
	}
	
	public void setLogRegButs() {
		if(logRegBtns == null) {
			logRegBtns = logRegBtns();
		}
		panel.add(logRegBtns, "logRegBtns");
		CardLayout cl = (CardLayout)(panel.getLayout());
		cl.show(panel, "logRegBtns");
	}
	
	public void setQuitPage() {
		if(quitPage == null) {
			quitPage = new ExitAndMore(main, chat);
		}
		panel.add(quitPage, "quitPage");
		CardLayout cl = (CardLayout)(panel.getLayout());
		cl.show(panel, "quitPage");
	}
	
	public void setRecentMsgs() {
		if(recentMsgs == null) {
			recentMsgs = new RecentMsgs();
		}
		displayLogOrReg.add(recentMsgs, "recentMsgs");
		CardLayout cl = (CardLayout)(displayLogOrReg.getLayout());
		cl.show(displayLogOrReg, "recentMsgs");
	}
	
	public void quit() {
		System.exit(EXIT_ON_CLOSE);
	}
	/**
	 * Launch the application.
	 */
	
	//run server first
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main main = new Main();
					
					main.setVisible(true);
					main.setLogin();
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
		this.main = this;
		frame();
	}
	
	public JPanel frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 711);
		mainPane = new JPanel();
		setLocationRelativeTo(null);
		mainPane.setBackground(new Color(255, 255, 255));
		mainPane.setForeground(new Color(0, 0, 0));
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainPane);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 305, 650);
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(39, 39, 39));
//		mainPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		mainPane.setLayout(new CardLayout(0, 0));
		setContent();
		
		return mainPane;
	}
	
	public Component content() {
		setLogRegButs();
		displayLogOrReg = new JPanel();
		displayLogOrReg.setBounds(325, 11, 698, 650);
		displayLogOrReg.setForeground(Color.WHITE);
		displayLogOrReg.setBackground(new Color(39, 39, 39));
		displayLogOrReg.setLayout(new CardLayout(0, 0));
		
		panel_2 = new JPanel();
		panel_2.add(panel);
		panel_2.add(displayLogOrReg);
		mainPane.add(panel_2, "name_29668996412100");
		
		panel_2.setLayout(null);
		return panel_2;
	}
	
	public Component logRegBtns() {
		holdLogReg = new JPanel(null);
		holdLogReg.setForeground(new Color(255, 255, 255));
		holdLogReg.setBackground(new Color(39, 39, 39));
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
		holdLogReg.add(btnRegister);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLogin();
			}
		});
		
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setBounds(51, 354, 202, 47);
		holdLogReg.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(0, 0, 305, 145);
		//
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quick");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(23, 23, 258, 48);
		panel_1.add(lblNewLabel);
		
		JLabel lblBoard = new JLabel("Chat");
		lblBoard.setForeground(new Color(255, 255, 255));
		lblBoard.setHorizontalAlignment(SwingConstants.CENTER);
		lblBoard.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 35));
		lblBoard.setBounds(10, 68, 285, 48);
		panel_1.add(lblBoard);
		holdLogReg.add(panel_1);
		
		return holdLogReg;
=======
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
>>>>>>> branch 'master' of https://github.com/PROG5121-2025-RC-PTA/prog5121-gr05-part-1-Phila-RB
	}
}
