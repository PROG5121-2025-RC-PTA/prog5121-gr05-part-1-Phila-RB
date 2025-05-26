package chatapp;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserLoginTestTrue {

	@Test
	public void testCheckUserNameTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userN = test.checkUserName(test.userName);
		assertEquals(true, userN);
		assertTrue("Username correctly formatted", userN);
	}

	@Test
	public void testCheckPasswordComplexityTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userP = test.checkPasswordComplexity(test.passWord);
		assertTrue("Password meets complexity requirements", userP);
	}

	@Test
	public void testCheckCellPhoneNumberTrue() {
		UserLogin test = new UserLogin("kyl_1","Ch&&sec@ke99","+27838968976");
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
		assertTrue("Cell phone number correctly formatted", userN);
	}
	
	@Test
	public void testReturnLoginStatusTrue() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		Main main = new Main();
		main.loggedInUser = test;
		String userN = test.returnLoginStatus(main);
		boolean toBool;
		
		if(userN.equals("A successful login")) {
			toBool = true;
		}
		else {
			toBool = false;
		}
		
		assertTrue("A successful login", toBool);
	}
	
	@Test
	public void testCheckUserNameFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = test.checkUserName(test.userName);
		assertFalse("Username incorrectly formatted", userN);
	}

	@Test
	public void testCheckPasswordComplexityFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userP = test.checkPasswordComplexity(test.passWord);
		assertFalse("Password does not meet complexity requirements", userP);
	}

	@Test
	public void testCheckCellPhoneNumberFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08966553");
		boolean userN = test.checkCellPhoneNumber(test.cellNum);
		assertFalse("Cell phone number incorrectly formatted", userN);
	}
	
	@Test
	public void testReturnLoginStatusFalse() {
		UserLogin test = new UserLogin("kyle!!!!!!!","password","08575975889");
		Main main = new Main();
		main.loggedInUser = null;
		String userN = test.returnLoginStatus(main);
		boolean toBool;
		
		if(userN.equals("Failed Login")) {
			toBool = false;
		}
		else {
			toBool = true;
		}
		
		assertFalse("Failed Login", toBool);
	}

}
