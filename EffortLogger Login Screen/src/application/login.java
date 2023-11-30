package application;

import java.util.HashMap;

public class login {
	private HashMap<String, String> users;
	private int count;
	
	public login() {
		this.users = new HashMap<String, String>();
		this.count = 0;
	}
	public login(HashMap<String, String> user) {
		this.users = user;
		this.count = 0;
	}
	
	public int checkLogin(String user, String password) {
		if(users.containsKey(user)) System.out.println("CONTAINS KEY");
		if(user.isEmpty() || password.isEmpty()) {
			return 0;
		}
		else if(count > 12) {
			return 2;
		}
		else if(!users.containsKey(user) || !users.get(user).equals(password)) {
			System.out.println("Count before is " + count);
			count++;
			System.out.println("Count after is " + count);
			return 1;
		}
		else if(users.get(user).equals(password)) return 3;
		else return 4;
	}
	public void addLogin(String user, String password) {
		users.put(user, password);
	}
	public boolean userExists(String user) {
		return users.containsKey(user);
	}
}
