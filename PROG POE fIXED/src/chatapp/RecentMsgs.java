package chatapp;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import java.awt.Font;

public class RecentMsgs extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public RecentMsgs() {
		setLayout(null);
		
		JTextPane txtpnComing = new JTextPane();
		txtpnComing.setEnabled(false);
		txtpnComing.setEditable(false);
		txtpnComing.setFont(new Font("Dialog", Font.PLAIN, 75));
		txtpnComing.setText("Coming");
		txtpnComing.setBounds(197, 118, 267, 101);
		add(txtpnComing);
		
		JTextPane txtpnSoon = new JTextPane();
		txtpnSoon.setText("soon");
		txtpnSoon.setFont(new Font("Dialog", Font.PLAIN, 75));
		txtpnSoon.setEnabled(false);
		txtpnSoon.setEditable(false);
		txtpnSoon.setBounds(245, 208, 171, 101);
		add(txtpnSoon);

	}
}
