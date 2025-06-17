package chatapp;


import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import java.awt.Font;
import java.text.NumberFormat;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WelcomePage extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField numF;

	/**
	 * Create the panel.
	 */
	public WelcomePage(String name, Main main) {
		setBackground(new Color(0, 0, 0));
		setLayout(null);
		
		JTextPane loggedIn = new JTextPane();
		loggedIn.setBackground(new Color(0, 0, 0));
		loggedIn.setForeground(new Color(255, 255, 255));
		
        StyledDocument doc = loggedIn.getStyledDocument();

        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        
		loggedIn.setEditable(false);
		loggedIn.setFont(new Font("Tahoma", Font.PLAIN, 65));
		loggedIn.setText("Congrats, " + name + " you are now logged in \nWelcome to Quick Chat");
		loggedIn.setBounds(165, 40, 699, 279);
		add(loggedIn);
		
		JTextPane txtpnHowManyMessages = new JTextPane();
		txtpnHowManyMessages.setBackground(new Color(0, 0, 0));
		txtpnHowManyMessages.setForeground(new Color(255, 255, 255));
		txtpnHowManyMessages.setFont(new Font("Tahoma", Font.PLAIN, 40));
		txtpnHowManyMessages.setText("How many messages would you like to send?");
		txtpnHowManyMessages.setEditable(false);
		txtpnHowManyMessages.setBounds(106, 373, 818, 55);
		add(txtpnHowManyMessages);
		
		numF = new JTextField();
		numF.setHorizontalAlignment(SwingConstants.CENTER);
		numF.setFont(new Font("Tahoma", Font.PLAIN, 30));
		numF.setText("1213");
		numF.setBounds(319, 447, 163, 55);
		add(numF);
		numF.setColumns(10);
		
		JButton btnNewButton = new JButton("Proceed");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					int realNum = Integer.parseInt(numF.getText());
					if(realNum > 0) {
						main.setContent();
						main.setChat(realNum);
						main.setQuitPage();
					}
					else {
						JOptionPane.showInternalMessageDialog(null, "Please enter a real number", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(NumberFormatException err) {
					JOptionPane.showInternalMessageDialog(null, "Please enter a real number", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNewButton.setBounds(527, 447, 89, 55);
		add(btnNewButton);
	}
}
