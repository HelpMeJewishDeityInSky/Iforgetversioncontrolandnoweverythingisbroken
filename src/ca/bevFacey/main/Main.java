package ca.bevFacey.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import ca.bevFacey.course.Course;
import ca.bevFacey.course.CourseHeap;
import ca.bevFacey.person.Person;
import ca.bevFacey.person.PersonHeap;
import ca.bevFacey.person.Teacher;

public class Main {

	private static PersonHeap ph;
	private static CourseHeap ch;
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		ph = new PersonHeap();
		ch = new CourseHeap();

		//insert test cases into person heap
		String[] names = {"Aidan","Sigmund","Undheim"};
		int[] birthdate = {2005,6,12};
		
		ph.insert(new Person(names, "Male", birthdate, "5875991903", "T8A5E8","Admin"));
		
		ph.insert(new Person(new String[]{"Idk","A","Name"}, "agender", new int[]{2005,0,0}, "02496","b9A2O0", "person"));
		ph.insert(new Person(new String[]{"Person","Mc","Personsen"}, "agender", new int[]{2005,0,0}, "12345", "6789", "person"));
		ph.insert(new Person(new String[]{"Person","Mc","Personsensen"}, "agender", new int[]{2005,0,0}, "phone", "address", "person"));
		
		long startTime = new Date().getTime();
		/*for(int i=0;i<1000;i++) {
			Object[] ans = Generator.generatePersonVariables();
			Person person = new Person((String[])ans[0], (String)ans[1], (int[])ans[2], (String)ans[3], (String)ans[4], (String)ans[5]);
			ph.insert(person);
		}*/
		
		//arraylists temporarily store teachers as to randomly generate and initiate courses and teachers
		ArrayList<Course> courses = new ArrayList<Course>();
		ArrayList<Teacher> teachers = new ArrayList<Teacher>();
		
		//puts a bunch of random people in for testing
		for(int i=0;i<5;i++){
			teachers.add(Generator.generateTeacher());
			ph.insert(teachers.get(teachers.size()-1));
		}
		
		for(int i=0;i<10;i++){
			courses.add(Generator.generateCourse(teachers));
			ch.insert(courses.get(courses.size()-1));
		}
		
		for(int i=0;i<30;i++){
			ph.insert(Generator.generateStudent(courses));
		}
		
		//allow garbage collector to free memory, java is weird and doesnt allow you to do it 
		//manually so as such it's terribly inefficent,
		courses.clear();
		teachers.clear();
		
		System.out.println(new Date().getTime() - startTime + "ms init time\n");
		
		//implement console control system
		
		//storse search results
		ArrayList<Object> searched = new ArrayList<Object>();
		boolean run = true;
		
		//ph.debug();
		ph.testSort();
		
		//CLI loop
		while(run) {
		
			System.out.print("> ");
			String[] command = in.nextLine().toLowerCase().split(" ");
			switch(command[0]){
			
			case "help":
				System.out.println(
						"Commands:\n\n" +
						"HELP\n" + 
							":\tdisplays this\n" + 
						"SEARCH <PERSON | COURSE>\n" + 
							":\tSEARCH PERSON <NAME | PHONE>\n" + 
								":\t:\tSEARCH PERSON NAME <NAME>\n" + 
									":\t:\t:\tsearches for person by name\n" +
								":\t:\tSEARCH PERSON PHONE <PHONE>\n" +
									":\t:\t:\tselects person by home phone number\n" +
							":\tSEARCH COURSE <NAME>\n" + 
							":\t:\tsearches for course by name\n" + 
						"DISPLAY <SELECTION NUMBER>\n" + 
							":\tdisplays data about selected object\n" + 
						"DELETE <SELECTION NUMBER>\n" + 
							":\tdeletes an object from memory\n" +
						"EXIT\n"+
							":\texits"
					);
				break;
			case "search":
				if(command[1].contentEquals("person")){
					ArrayList<Person> results;
					//search person heap
					if(command[2].contentEquals("name")){
						//search by name
						//String fName = /*(command[3]!=null)?command[3]:""*/;
						//String mName = /*(command[4]!=null)?command[4]:""*/;
						//String lName = /*(command[5]!=null)?command[5]:""*/;
						results = ph.searchByName(new String[]{command[3], command[4], command[5]});
					}else{
						//search by phone
						results = ph.searchByPhone(command[3]);
					}
					System.out.println("SEARCH RESULTS:");
					searched.clear();
					for(int i=0;i<results.size();i++){
						searched.add(results.get(i));
						System.out.println("[" + i + "]: " + results.get(i));
					}
				}else{
					//search course heap
					ArrayList<Course> results = ch.search(command[2]);
					System.out.println("SEARCH RESULTS:");
					searched.clear();
					for(int i=0;i<results.size();i++){
						searched.add(results.get(i));
						System.out.println("[" + i + "]: " + results.get(i));
					}
				}
				break;
			case "display":
				System.out.println(searched.get(Integer.parseInt(command[1])));
				break;
			case "delete":
				int index = Integer.parseInt(command[1]);
				if(searched.get(index) instanceof Person){
					System.out.println("\n\n" + searched.get(index) + "\n\n" + "was deleted");
					ph.delete((Person)searched.get(index));
				}else ch.delete((Course)searched.get(index));
				break;
			case "exit":
				run = false;
				break;
			}
		}
		
		in.close();
	}

}
