package chatapp;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;

public class SentAndRecieved extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public SentAndRecieved(JSONArray arr) {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1061, 711);
		setLocationRelativeTo(null);
//		setLayout(null);
		JPanel main = new JPanel();
		setContentPane(main);
		main.setLayout(new BoxLayout(main, BoxLayout.X_AXIS));
		
		JPanel chatview = new JPanel();
		chatview.setLayout(new GridBagLayout());
		JScrollPane scrollPane = new JScrollPane(chatview);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		main.add(scrollPane);
		
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
        
		System.out.println(arr.toString());
		for(int i = 0; i < arr.length(); i++) {
			JSONObject obj = arr.getJSONObject(i);
			
			SwingUtilities.invokeLater(() -> {
		        JTextPane textpane = new JTextPane();
		        if(obj.get("from").toString().equals("user")) {
		        	textpane.setBackground(Color.cyan);
		        }
		        else {
		        	textpane.setBackground(Color.green);
		        }
		        textpane.setText(obj.get("Recipient").toString() + ":\n " + obj.get("Message"));
		        textpane.setMaximumSize(new Dimension(650, 40)); // Prevent stretching
		        textpane.setEditable(false);
		        
		        gbc.gridx = 0;
		        gbc.insets = new Insets(5, 5, 5, 5);
		        gbc.anchor = GridBagConstraints.WEST;
		        gbc.fill = GridBagConstraints.HORIZONTAL;
		        gbc.weightx = 0.3;
		        chatview.add(textpane, gbc);
		        
		        chatview.revalidate();
		        chatview.repaint();
			});
		}

	

	}
	
	
}
