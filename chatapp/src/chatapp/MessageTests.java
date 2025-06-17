
package chatapp;

import static org.junit.Assert.*;

import org.junit.Test;
public class MessageTests {
	
	@Test
    public void testMaxCHarTrue() {
		int msgL = 200;
        int max = 250;
        assertEquals(msgL < max, msgL < max);
		assertTrue("Message ready to send", msgL < max); 
    }
	
	@Test
    public void testMaxCHarFalse() {
		int msgL = 300;
        int max = 250;
        assertEquals(msgL > max, msgL > max);
		assertFalse("Message ready to send", msgL < max); 
    }
	
	@Test
	public void testCheckCellPhoneNumberTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27718693002");
		boolean userN = UserLogin.checkCellPhoneNumber(test.cellNum);
		assertEquals(true, userN);
		assertTrue("Cell phone number successfully captured", userN);
	}
	
	@Test
	public void testCheckCellPhoneNumberFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = UserLogin.checkCellPhoneNumber(test.cellNum);
		assertEquals(false, userN);
		assertFalse("Cell phone number is incorreectly formatted or does not contain an internation code. Please correct the number and try again", userN);
	}
	
	@Test
	public void testMessageHash() {//no video to refer to
		Message test = new Message();
		long id = test.createID();
		String hash = test.createMessageHash(id, 2, "Hi Mike, can you join us for dinner tonight");
		assertEquals("HITONIGHT", hash.substring(5, hash.length()));
	}
	
	
	@Test
	public void testGeneratedMsgId() {
		Message test = new Message();
		long id = test.createID();
		System.out.println("Message ID generated: " + id);
	}
	
	@Test
    public void testSendMessageOption() {
		
    }
	
	@Test
    public void testDisregardMsg() {
		
    }
	
	@Test
    public void testStoreMsg() {
		
    }
}
