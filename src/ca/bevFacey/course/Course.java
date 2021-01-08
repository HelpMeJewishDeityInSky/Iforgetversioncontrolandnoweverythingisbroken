package ca.bevFacey.course;

import java.util.ArrayList;

import ca.bevFacey.person.Student;
import ca.bevFacey.person.Teacher;

public class Course {

	private Teacher teacher;
	private ArrayList<Student> students = new ArrayList<Student>();
	private int numStudents;
	private String name;
	private int room;
	
	public Course(Teacher teacher, ArrayList<Student> students, String name, int room){
		this.teacher = teacher;
		this.students = students;
		this.name = name;
		this.room = room;
		
		this.numStudents = students.size();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}
	
	public void addStudent(Student student){
		students.add(student);
	}

	public int classSize() {
		return numStudents;
	}

	public String getName() {
		return name;
	}

	public int getRoom() {
		return room;
	}
	
	public String toString(){
		return name + " is taught by " + teacher.getFirstName() + ' ' + teacher.getLastName() + " in room " + room + '\n'
				+ "class size: " + numStudents;
	}
	
	public boolean equals(Course course){
		return (course.getName().equals(name) && course.getTeacher().equals(teacher) && course.getRoom() == room);
	}
	
}
