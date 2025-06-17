package chatapp;



public class UserLogin{
    String userName;
    String passWord;
    String cellNum;

    public UserLogin (String userName, String passWord, String cellNum){
        this.userName = userName;
        this.passWord = passWord;
        this.cellNum = cellNum;
    }
    
	public String toString() {
		String output = userName + ", " + passWord + ", " + cellNum;
		return output;
	}
	

    public boolean checkUserName(String userName){
        if(userName.contains("_") && userName.length() <= 5){
            System.out.println("Username successfully captured");
            return true;
        }
        System.out.println("Username is not correctly formatted, please ensure the username contains an underscore and is no more than 5 characters in length");
        return false;
    }
    
    public boolean checkPasswordComplexity(String password){
        int hasEnoughChar = 0;
        int hasCapitalChar = 0;
        int hasNumChar = 0;
        int hasSpecialChar = 0;

        if(password.length() >= 8){
            hasEnoughChar = 1;
        }
        
        for(int i = 0; i <= password.length() - 1; i++){
            if((int) password.charAt(i) >= 48 && (int) password.charAt(i) <= 57){
                hasNumChar = 1;
            }
            
            if(((int) password.charAt(i) >= 65 && (int) password.charAt(i) <= 90) || ((int) password.charAt(i) >= 97 && (int) password.charAt(i) <= 122)){
                if(String.valueOf(password.charAt(i)).equals(String.valueOf(password.charAt(i)).toUpperCase())){
                    hasCapitalChar = 1;
                }
            }

            if(((int)password.charAt(i) >= 33 && (int)password.charAt(i) <= 47 ) ||  ((int)password.charAt(i) >= 58 && (int)password.charAt(i) <= 64 ) ||
                ((int)password.charAt(i) >= 91 && (int)password.charAt(i) <= 96 ) ||  ((int)password.charAt(i) >= 123 && (int)password.charAt(i) <= 126 )){

                    hasSpecialChar = 1;
            }
        }
//        System.out.println(hasEnoughChar + " " + hasCapitalChar + " " + hasNumChar + " " + hasSpecialChar);
        if(hasEnoughChar == 1 && hasCapitalChar == 1 && hasNumChar == 1 && hasSpecialChar == 1){
            System.out.println("password successfully captured");
            return true;
        }

        System.out.println("Password is not correctly formatted correctly, please ensure the password contains atleast 8 characters," +
        "a capital letter, a number and a special character.");

        return false;
    }
    
    public static boolean checkCellPhoneNumber(String number) {
    	String test = number.replace("+27", "");
    	System.out.println(test.length());
        try {
        	Long.parseLong(test);
        }
        catch(NumberFormatException e) {
        	System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        	return false;
        }
    	
        if(test.length() < 9 || test.length() > 10){
        	System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        	return false;
        }
    	
    	String code = number.substring(0, 3);
    	
    	if(!code.equals("+27")) {
    		System.out.println("Cell phone number incorrectly formatted or does not contain international code");
    		return false;
    	}
        
        System.out.println("Cell phone number successfully added");
        return true;
    }
    
    public String returnLoginStatus(Main main) {
    	if(main.loggedInUser == null) {
    		System.out.println("Failed Login");
    		return "Failed Login";
    	}
    	System.out.println("A successful login");
    	return "A successful login";
    }
}
