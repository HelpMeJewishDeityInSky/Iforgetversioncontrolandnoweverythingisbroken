package ca.bevFacey.person;

import java.util.ArrayList;

import ca.bevFacey.course.Course;

public class Teacher extends Staff {

	private ArrayList<Course> courses;
	
	public Teacher(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress,
			EmergencyContact[] emergencyContacts, ArrayList<Course> courses) {
		super(names, gender, birthDate, homePhone, mailingAddress, emergencyContacts, "Teacher");
		
		this.courses = courses;
	}
	
	//constructor for subclasses
	public Teacher(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress,
			EmergencyContact[] emergencyContacts, ArrayList<Course> courses, String title) {
		super(names, gender, birthDate, homePhone, mailingAddress, emergencyContacts, title);
		
		this.courses = courses;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}
	
	public void addCourse(Course course){
		courses.add(course);
	}

	@Override
	public String toString(){
		return super.toString() + '\n'
				+ "Courses: " + courses;
	}
	
}
