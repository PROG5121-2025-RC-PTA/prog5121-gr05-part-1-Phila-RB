package chatapp;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextPane;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Point;

public class Errors extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void m(String text) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Errors dialog = new Errors(text);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 * @return 
	 */
	public Errors(String errMsg) {
		setModal(true);
		setLocation(new Point(500, 500));
		setAlwaysOnTop(true);
		setBounds(100, 100, 372, 160);
		
		JTextPane txtpnRet = new JTextPane();
		txtpnRet.setEditable(false);
		txtpnRet.setText(errMsg);
		txtpnRet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		getContentPane().add(txtpnRet, BorderLayout.CENTER);
	}

}
