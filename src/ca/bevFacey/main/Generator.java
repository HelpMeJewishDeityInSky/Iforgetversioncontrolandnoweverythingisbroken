package ca.bevFacey.main;

import java.util.ArrayList;
import java.util.Arrays;

import ca.bevFacey.course.Course;
import ca.bevFacey.person.EmergencyContact;
import ca.bevFacey.person.Parent;
import ca.bevFacey.person.Student;
import ca.bevFacey.person.Teacher;

public abstract class Generator {
	
	//all of these variables are for testing purposes, and may be expanded or changed, and will not represent an actual school environment
	private static String[][] firstNames = {{"James","Mary"},{"John","Patricia"},{"Robert","Jennifer"},{"Michael","Linda"},{"William","Elizabeth"},{"David","Barbara"},{"Richard","Susan"},{"Joseph","Jessica"},{"Thomas","Sarah"},{"Charles","Karen"},{"Christopher","Nancy"},{"Daniel","Lisa"},{"Matthew","Margaret"},{"Anthony","Betty"},{"Donald","Sandra"},{"Mark","Ashley"},{"Paul","Dorothy"},{"Steven","Kimberly"},{"Andrew","Emily"},{"Kenneth","Donna"},{"Joshua","Michelle"},{"Kevin","Carol"},{"Brian","Amanda"},{"George","Melissa"},{"Edward","Deborah"},{"Ronald","Stephanie"},{"Timothy","Rebecca"},{"Jason","Laura"},{"Jeffrey","Sharon"},{"Ryan","Cynthia"},{"Jacob","Kathleen"},{"Gary","Amy"},{"Nicholas","Shirley"},{"Eric","Angela"},{"Jonathan","Helen"},{"Stephen","Anna"},{"Larry","Brenda"},{"Justin","Pamela"},{"Scott","Nicole"},{"Brandon","Samantha"},{"Benjamin","Katherine"},{"Samuel","Emma"},{"Frank","Ruth"},{"Gregory","Christine"},{"Raymond","Catherine"},{"Alexander","Debra"},{"Patrick","Rachel"},{"Jack","Carolyn"},{"Dennis","Janet"},{"Jerry","Virginia"},{"Tyler","Maria"},{"Aaron","Heather"},{"Jose","Diane"},{"Henry","Julie"},{"Adam","Joyce"},{"Douglas","Victoria"},{"Nathan","Kelly"},{"Peter","Christina"},{"Zachary","Lauren"},{"Kyle","Joan"},{"Walter","Evelyn"},{"Harold","Olivia"},{"Jeremy","Judith"},{"Ethan","Megan"},{"Carl","Cheryl"},{"Keith","Martha"},{"Roger","Andrea"},{"Gerald","Frances"},{"Christian","Hannah"},{"Terry","Jacqueline"},{"Sean","Ann"},{"Arthur","Gloria"},{"Austin","Jean"},{"Noah","Kathryn"},{"Lawrence","Alice"},{"Jesse","Teresa"},{"Joe","Sara"},{"Bryan","Janice"},{"Billy","Doris"},{"Jordan","Madison"},{"Albert","Julia"},{"Dylan","Grace"},{"Bruce","Judy"},{"Willie","Abigail"},{"Gabriel","Marie"},{"Alan","Denise"},{"Juan","Beverly"},{"Logan","Amber"},{"Wayne","Theresa"},{"Ralph","Marilyn"},{"Roy","Danielle"},{"Eugene","Diana"},{"Randy","Brittany"},{"Vincent","Natalie"},{"Russell","Sophia"},{"Louis","Rose"},{"Philip","Isabella"},{"Bobby","Alexis"},{"Johnny","Kayla"},{"Bradley","Charlotte"}};
	private static String[][] middleNames = {{"James","Louise"},{"John","Rose"},{"William","Grace"},{"Thomas","Jane"},{"David","Elizabeth"},{"Robert","Anne","Ann"},{"Edward","May","Mae"},{"Peter","Marie"},{"Lee","Mary"},{"Christopher","Amy"},{"Alexander","Catherine"},{"Michael","Victoria"},{"Daniel","Kate",}};
	
	private static String[] lastNames = {"Smith","Brown","Tremblay","Martin","Roy","Wilson","Macdonald","Gagnon","Johnson","Taylor","Cote","Campbell","Anderson","Leblanc","Lee","Jones","White","Williams","Miller","Thompson","Gauthier","Young","Van","Morin","Bouchard","Scott","Stewart","Belanger","Reid","Pelletier","Moore","Lavoie","King","Robinson","Levesque","Murphy","Fortin","Gagne","Wong","Clark","Johnston","Clarke","Ross","Walker","Thomas","Boucher","Landry","Kelly","Bergeron","Davis","Mitchell","Murray","Poirier","Mcdonald","Richard","Wright","Girard","Lewis","Baker","Roberts","Simard","Graham","Caron","Harris","Jackson","Green","Beaulieu","Fraser","Fournier","Kennedy","Hall","Hill","Chan","Wood","Lapointe","Ouellet","Bell","Dube","Allen","Adams","Cloutier","Bennett","Lefebvre","Watson","Robertson","Walsh","Collins","Evans","Hebert","Hamilton","Cameron","Desjardins","Russell","Nadeau","Cook","Michaud","Morrison","Singh","Grant","Parsons"};
	
	private static String[] genders = {"Male", "Female", "Non-binary", "Agender", "Genderqueer", "Bigender"};
	
	private static int[] monthDays = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	private static String[] phoneCodes = {"587","780"};
	
	private static String[] emergencyRelationships = {"Grandparent", "Family Friend", "Parent's Sibling", "Guardian's Sibling", "Older Sibling"};
	private static String[] courseNames = {"CS", "IT", "Home Ec", "Welding", "WoodWorking", "Art", "Math", "ELA", "Social"};
	
	public static Object[] generatePersonVariables() {
		
		int rGender = (int) Math.round(Math.random()*100);
		String gender;
		
		int genderedName;
		
		//these rates are randomly picked please dont cancel me
		if(rGender < 40){
			gender = genders[0];
			genderedName = 0;
		}else if(rGender < 80){
			gender = genders[1];
			genderedName = 1;
		}else{
			//get random gender between the third one and the final one
			gender = genders[(int)Math.round(Math.random()*(genders.length-3))+2];
			genderedName = (int) Math.round(Math.random());
		}
		
		String[] names = {
				firstNames[(int) Math.round(Math.random()*(firstNames.length-1))][genderedName],
				middleNames[(int) Math.round(Math.random()*(middleNames.length-1))][genderedName],
				lastNames[(int) Math.round(Math.random()*(lastNames.length-1))]
		};
		
		int month = (int) Math.round((Math.random()*11)+1);
		
		int[] birthDate = {
				(int) Math.round((Math.random()*13)+2003),
				month,
				(int) Math.round((Math.random()*(monthDays[month-1]-1))+1)
		};
		
		int[] phoneSuffixInts = new int[7];
		
		for(int i=0;i<phoneSuffixInts.length;i++){
			phoneSuffixInts[i] = (int) Math.round(Math.random()*9);
		}
		String phoneSuffix = Arrays.toString(phoneSuffixInts).replaceAll("\\[|\\]|,|\\s", "");
		
		String homePhone = phoneCodes[(int) Math.round(Math.random())]+phoneSuffix;
		
		
		char[] address = new char[6];
		char[] interval = {'A', '0'};
		for(int i=0;i<6;i++) {
			address[i] = (char) (interval[i&1] + Math.round(Math.random()*(((i&1)==0)?25:9)));
		}
		
		String mailingAddress = Arrays.toString(address).replaceAll("\\[|\\]|,|\\s", "");
		
		String title = "Person";
		
		return new Object[]{names, gender, birthDate, homePhone, mailingAddress, title};
		//return new Person(names, gender, birthDate, homePhone, mailingAddress, title);
	}
	
	public static Object[] generateEmergencyContactVariables(){
		//String[] names, String homePhone, String relationship
		int rGender = (int) Math.round(Math.random()*100);
		int genderedName;
	
		if(rGender < 50){
			genderedName = 0;
		}else{
			genderedName = 1;
		}
		
		
		String[] names = {
				firstNames[(int) Math.round(Math.random()*(firstNames.length-1))][genderedName],
				middleNames[(int) Math.round(Math.random()*(middleNames.length-1))][genderedName],
				lastNames[(int) Math.round(Math.random()*(lastNames.length-1))]
		};
		
		int[] phoneSuffixInts = new int[7];
		
		for(int i=0;i<phoneSuffixInts.length;i++){
			phoneSuffixInts[i] = (int) Math.round(Math.random()*9);
		}
		String phoneSuffix = Arrays.toString(phoneSuffixInts).replaceAll("\\[|\\]|,|\\s", "");
		
		String homePhone = phoneCodes[(int) Math.round(Math.random())]+phoneSuffix;
		
		String relationship = emergencyRelationships[(int) Math.round(Math.random()*(emergencyRelationships.length-1))];
		
		return new Object[]{names,homePhone,relationship};
	}
	
	public static Student generateStudent(ArrayList<Course> courses){
		//this also adds students to the actual course object, it would be quite dumb if it didn't
		
		int lockerNum = (int) Math.round(Math.random()*1000);
		
		int[] lockerComb = {
				(int) Math.round(Math.random()*39),
				(int) Math.round(Math.random()*39),
				(int) Math.round(Math.random()*39)
		};
		
		int grade = (int) Math.round(Math.random()*23);
		
		Object[] var1 = generatePersonVariables();
		Object[] var2 = generatePersonVariables();
		
		Parent[] parents = {
				new Parent((String[])var1[0],(String)var1[1],(int[])var1[2],(String)var1[3],(String)var1[4]),
				new Parent((String[])var2[0],(String)var2[1],(int[])var2[2],(String)var2[3],(String)var2[4])
		};
		
		
		var1 = generateEmergencyContactVariables();
		var2 = generateEmergencyContactVariables();
		
		EmergencyContact[] emergencyContacts = {
				new EmergencyContact((String[])var1[0],(String)var1[1],(String)var1[2]),
				new EmergencyContact((String[])var2[0],(String)var2[1],(String)var2[2])
		};
		
		if(courses.size()<1){
			var1 = generatePersonVariables();
			return new Student((String[])var1[0],(String)var1[1],(int[])var1[2],(String)var1[3],(String)var1[4],lockerNum,lockerComb,grade,parents,emergencyContacts,new ArrayList<Course>());
		}
		
		int classes = (int) ((int) 8-Math.round(Math.random()*2));
		ArrayList<Course> studentCourses = new ArrayList<Course>();
		
		for(int i=0;i<classes;i++){
			Course course = courses.get((int)Math.round(Math.random()*(courses.size()-1)));
			studentCourses.add(course);
		}
		
		var1 = generatePersonVariables();
		
		Student student = new Student((String[])var1[0],(String)var1[1],(int[])var1[2],(String)var1[3],(String)var1[4],lockerNum,lockerComb,grade,parents,emergencyContacts,studentCourses);
		
		for(int i=0;i<classes;i++){
			Course course = student.getCourses().get(i);
			course.addStudent(student);
		}
		
		return student;
		//int lockerNum, int[] lockerComb, int grade, Parent[] parents, EmergencyContact[] emergencyContacts, ArrayList<Course> courses
	}
	
	public static Teacher generateTeacher() {
		Object[] var1 = generateEmergencyContactVariables();
		Object[] var2 = generateEmergencyContactVariables();
		
		EmergencyContact[] emergencyContacts = {
				new EmergencyContact((String[])var1[0],(String)var1[1],(String)var1[2]),
				new EmergencyContact((String[])var2[0],(String)var2[1],(String)var2[2])
		};
		
		var1 = generatePersonVariables();
		
		return new Teacher((String[])var1[0], (String)var1[1],(int[])var1[2],(String)var1[3],(String)var1[4], emergencyContacts, new ArrayList<Course>());
		//EmergencyContact[] emergencyContacts, ArrayList<Course> courses
	}
	
	public static Course generateCourse(ArrayList<Teacher> teachers){
		Teacher teacher = teachers.get((int) Math.round(Math.random()*(teachers.size()-1)));
		
		String name = courseNames[(int) Math.round(Math.random()*(courseNames.length-1))];
		
		int room = (int) Math.round(Math.random()*300);
		
		Course course = new Course(teacher, new ArrayList<Student>(), name, room);
		
		teacher.addCourse(course);
		return course;
		//Teacher teacher, ArrayList<Student> students, String name, int room
	}
	
}
