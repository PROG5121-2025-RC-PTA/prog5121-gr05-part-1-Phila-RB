package chatapp;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

public class WelcomePage extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public WelcomePage(String name) {
		setLayout(null);
		
		JTextPane loggedIn = new JTextPane();
		loggedIn.setAlignmentX(CENTER_ALIGNMENT);
		loggedIn.setFont(new Font("Tahoma", Font.PLAIN, 90));
		loggedIn.setText("Congrats, " + name + " you are now logged in, The rest is coming soon");
		loggedIn.setBounds(187, 55, 655, 550);
		add(loggedIn);

	}
}
