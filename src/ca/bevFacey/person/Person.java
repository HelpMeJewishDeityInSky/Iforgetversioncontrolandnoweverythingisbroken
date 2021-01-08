package ca.bevFacey.person;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class Person {
	
	private String[] names;
	private String gender;
	private int[] birthDate;
	private int age;
	private String homePhone;
	private String title;
	private String mailingAddress;
	
	public Person(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress, String title){
		this.names = names;
		this.gender = gender;
		this.birthDate = birthDate;
		this.homePhone = homePhone;
		this.title = title;
		this.mailingAddress = mailingAddress;
		
		//birth date is stored in yyyy/mm/dd
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		int days = localDate.getDayOfMonth() - birthDate[2];
		int months = localDate.getMonthValue() - birthDate[1];
		int years = localDate.getYear() - birthDate[0];
		
		age = (int) Math.floor(years + (months/12) + (days/365));
	}
	
	public Person(String[] names, String homePhone, String title){
		this.names = names;
		this.homePhone = homePhone;
		this.title = title;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public String[] getNames() {
		return names;
	}
	
	public String getFirstName(){
		return names[0];
	}
	
	public String getMiddleName(){
		return names[1];
	}
	
	public String getLastName(){
		return names[2];
	}
	
	public void setFirstName(String name){
		names[0] = name;
	}
	
	public void setMiddleName(String name){
		names[1] = name;
	}
	
	public void setLastName(String name){
		names[2] = name;
	}

	public String getGender() {
		return gender;
	}

	public int[] getBirthDate() {
		return birthDate;
	}

	public int getAge() {
		return age;
	}

	public String getHomeNumber() {
		return homePhone;
	}
	
	public String getTitle() {
		return title;
	}

	public String toString(){
		return names[0] + " " + names[1] + " " + names[2] + " is a " + title + '\n'
				+ "born on: " + birthDate[0] + ' ' + birthDate[1] + ' ' + birthDate[2] + '\n'
				+ "identifies as a " + gender + '\n'
				+ "can be called at " + homePhone;
	}
	
	public boolean equals(Person person){
		return Arrays.equals(person.getBirthDate(), birthDate) 
				&& Arrays.equals(person.getNames(), names) 
				&& person.getGender().equals(gender)
				&& person.getTitle().equals(title)
				&& person.getHomeNumber().equals(homePhone);
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setBirthDate(int[] birthDate) {
		this.birthDate = birthDate;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
}
