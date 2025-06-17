package chatapp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.json.JSONObject;
import org.junit.Test;

public class Part3Test {

	@Test
	public void testArrPop() {
		String str1 = "Did you get the cake?";
		String str2 = "It is dinner time !";
		ArrayList<String> sentMsgs = new ArrayList<>();
		Message msg = new Message();
		msg.sentMessage(str1, new MessageFate() {
			@Override
			public void sending() {
				if(str1.length() <= 250 && str1.length() > 0) {	
					sentMsgs.add(str1);
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}

			@Override
			public void disregarding() {
				
			}

			@Override
			public void storing() {
			
			}
		}, 0);
		msg.sentMessage(str2, new MessageFate() {
			@Override
			public void sending() {
				if(str2.length() <= 250 && str2.length() > 0) {	
					sentMsgs.add(str2);
				}
				else {
					JOptionPane.showInternalMessageDialog(null, "Please enter a message of less than 250 characters", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}

			@Override
			public void disregarding() {
				
			}

			@Override
			public void storing() {
			
			}
		}, 0);
		assertEquals("[Did you get the cake?, It is dinner time !]", sentMsgs.toString());
	}
	
	@Test
	public void testLongMsg() {
		Main main = new Main();
		Chat chat = new Chat(1,main);
		JSONObject msg1 = new JSONObject("{\"Message\":\"Did you get Cake\"}");
		JSONObject msg2 = new JSONObject("{\"Message\":\"Where are you? Your are late! I asked you to be home on time.\"}");
		JSONObject msg3 = new JSONObject("{\"Message\":\"Yohooo, I am at your gate.\"}");
		JSONObject msg4 = new JSONObject("{\"Message\":\"It is dinner time\"}");
		ArrayList<JSONObject> obj = new ArrayList<>(Arrays.asList(msg1, msg2, msg3, msg4));
		String str = chat.viewLongMsg(obj);
		assertEquals("Where are you? Your are late! I asked you to be home on time.", str);
	}
	
	@Test
	public void testMsgSearch() {
		JSONObject msg4 = new JSONObject("{\"Message\":\"It is dinner time\",\"MessageID\":\"0838884567\"}");
		ArrayList<JSONObject> obj = new ArrayList<>(Arrays.asList(msg4));
		Main main = new Main();
		Chat chat = new Chat(1,main);
		JSONObject out = chat.searchMsg(obj, "0838884567");
		assertEquals("It is dinner time.", out.get("MessageID").toString());
	}
	
	@Test
	public void testSearchAll() {
		
	}
	
	@Test
	public void testdelMsg() {
		
	}
	
	@Test
	public void displayReport() {
		
	}

}
