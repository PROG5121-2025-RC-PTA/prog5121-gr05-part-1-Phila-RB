package chatapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserLoginTest {

	@Test
	public void testCheckUserNameTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userN = test.checkUserName(test.userName);
		assertEquals(true, userN);
	}

	@Test
	public void testCheckPasswordComplexityTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userP = test.checkPasswordComplexity(test.passWord);
		assertEquals(true, userP);
	}

	@Test
	public void testCheckCellPhoneNumberTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
		assertEquals(true, userN);
	}
	
	@Test
	public void testReturnLoginStatusTrue() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		Main main = new Main();
		main.loggedInUser = test;
		String userN = test.returnLoginStatus(main);
		assertEquals("A successful login", userN);
	}
	
	@Test
	public void testCheckUserNameFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = test.checkUserName(test.userName);
		assertEquals(false, userN);
	}

	@Test
	public void testCheckPasswordComplexityFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userP = test.checkPasswordComplexity(test.passWord);
		assertEquals(false, userP);
	}

	@Test
	public void testCheckCellPhoneNumberFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
		assertEquals(false, userN);
	}
	
	@Test
	public void testReturnLoginStatusFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		Main main = new Main();
		main.loggedInUser = null;
		String userN = test.returnLoginStatus(main);
		assertEquals("Failed Login", userN);
	}

}
