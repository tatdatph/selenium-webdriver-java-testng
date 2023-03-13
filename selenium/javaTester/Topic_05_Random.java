package javaTester;

import java.util.Random;

public class Topic_05_Random {

	public static void main(String[] args) {
		Random rand = new Random();
		
		String emailAddress = "automation" + rand.nextInt(99999) + "@gmail.com";
		System.out.println(emailAddress);
						
		rand.nextInt(99999);
	}

}
