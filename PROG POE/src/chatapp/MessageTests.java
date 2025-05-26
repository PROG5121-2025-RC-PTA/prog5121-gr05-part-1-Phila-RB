
package chatapp;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;
import static org.mockito.Mockito.*;

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
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
		assertEquals(true, userN);
		assertTrue("Cell phone number successfully captured", userN);
	}
	
	@Test
	public void testCheckCellPhoneNumberFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
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
	
	//attempt at sentMessage() tests but to complicated due to interfaces and gui input requirements
	//mockito simulates dependency injection in unit tests
	@Test
    public void testSendMessageOption() {//tested manually
        // Arrange
        SendTheMessageAlready mockSend = mock(SendTheMessageAlready.class);

        Message service = spy(new Message());
        doReturn(0).when(service).getUserChoice(); // simulate "Send Message"

        // Act
        service.sentMessage("Hi Mike, can you join us for dinner tonight", mockSend);

        // Assert
        verify(mockSend).sending();
    }
	
	@Test
    public void testDisregardMsg() {//tested manually
        // Arrange
        SendTheMessageAlready mockSend = mock(SendTheMessageAlready.class);

        Message service = spy(new Message());
        doReturn(0).when(service).getUserChoice(); // simulate "Send Message"

        // Act
        service.sentMessage("Hi Mike, can you join us for dinner tonight", mockSend);

        // Assert
        verify(mockSend).disregarding();
    }
	
	@Test
    public void testStoreMsg() {//tested manually
        // Arrange
        SendTheMessageAlready mockSend = mock(SendTheMessageAlready.class);

        Message service = spy(new Message());
        doReturn(0).when(service).getUserChoice();

        // Act
        service.sentMessage("Hi Mike, can you join us for dinner tonight", mockSend);

        // Assert
        verify(mockSend).storing();;
    }
}
