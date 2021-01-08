package ca.bevFacey.person;

import java.util.ArrayList;

import ca.bevFacey.course.Course;

public class TeachingAssistant extends Teacher {

	public TeachingAssistant(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress,
			EmergencyContact[] emergencyContacts, ArrayList<Course> courses) {
		super(names, gender, birthDate, homePhone, mailingAddress, emergencyContacts, courses, "Teaching Assistant");
	}

}
