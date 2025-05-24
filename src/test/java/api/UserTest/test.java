package api.UserTest;

import com.github.javafaker.Faker;

public class test {
	
	public static void main(String[] args) {
		
		Faker faker = new Faker();
		
		for (int i =0;i<=10;i++) {
			System.out.println(faker.phoneNumber().cellPhone());
		}
			
		}
	

}
