package ca.bevFacey.person;

import java.util.ArrayList;
import java.util.UUID;

import ca.bevFacey.course.Course;

public class Student extends Person {

	private UUID studentId;
	private int lockerNum;
	private int[] lockerComb;
	private int grade;
	private ArrayList<Course> courses;
	private Parent[] parents;
	private EmergencyContact[] emergencyContacts;
	
	public Student(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress, int lockerNum, 
			int[] lockerComb, int grade, Parent[] parents, EmergencyContact[] emergencyContacts, ArrayList<Course> courses) {
		
		super(names, gender, birthDate, homePhone, mailingAddress, "Student");
		
		//this is completely overkill, as this creates a 128bit random universally unique ID, but it is *very* expandable
		this.studentId = UUID.randomUUID();
		
		this.lockerNum = lockerNum;
		this.lockerComb = lockerComb;
		this.grade = grade;
		this.parents = parents;
		this.emergencyContacts = emergencyContacts;
		this.courses = courses;
		
	}

	public UUID getStudentId() {
		return studentId;
	}

	public int getLockerNum() {
		return lockerNum;
	}

	public int[] getLockerComb() {
		return lockerComb;
	}

	public int getGrade() {
		return grade;
	}

	public Parent[] getParents() {
		return parents;
	}

	public EmergencyContact[] getEmergencyContacts() {
		return emergencyContacts;
	}
	
	public void changeLocker(int locker, int[] lockerComb){
		lockerNum = locker;
		this.lockerComb = lockerComb;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}
	
	public void addCourse(Course course){
		courses.add(course);
	}

	public void setParents(Parent[] parents) {
		this.parents = parents;
	}

	public void setEmergencyContacts(EmergencyContact[] emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
	@Override
	public String toString(){
		return super.toString() + '\n'
				+ "Student ID " + studentId + '\n'
				+ "Emergency Contacts " + emergencyContacts[0].getNames()[0] + ' '
						+ emergencyContacts[0].getNames()[1] + ' '
						+ emergencyContacts[0].getNames()[2]
						+ " and " + emergencyContacts[1].getNames()[0] + ' '
						+ emergencyContacts[1].getNames()[1] + ' '
						+ emergencyContacts[1].getNames()[2] + '\n'
				+ "Parents " + parents[0].getNames()[0] + ' '
						+ parents[0].getNames()[1] + ' '
						+ parents[0].getNames()[2]
						+ " and " + parents[1].getNames()[0] + ' '
						+ parents[1].getNames()[1] + ' '
						+ parents[1].getNames()[2] + '\n'
				+ "Locker #: " + lockerNum + '\n'
				+ "Locker Combination: " + lockerComb[0] + ' ' + lockerComb[1] + ' ' + lockerComb[2] + '\n'
				+ "Grade " + grade + '\n'
				+ "Courses: " + courses;
	}

}
