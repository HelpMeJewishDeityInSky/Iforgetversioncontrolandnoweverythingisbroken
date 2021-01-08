package ca.bevFacey.course;

import java.util.ArrayList;

import ca.bevFacey.person.Person;

public class CourseHeap {
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	//private ArrayList<Person> peopleByPhone = new ArrayList<Person>();
	
	public CourseHeap() {
		
	}
	
	public void debug(){
		for(int i=0;i<courses.size();i++){
			Course course = courses.get(i);
			System.out.print(course.getName() + " " + (int)course.getName().charAt(0) + " ");
			System.out.print("\n");
		}
	}
	
	public ArrayList<Course> search(String name){
		ArrayList<Course> sortList = extractList();
		
		int min = 0;
		int max = sortList.size()-1;
		int i = 0;
		int c = 0;
		while(true){	
			c++;
			if(c>10)return new ArrayList<Course>();
			i = Math.round(((max-min)/2)+min);
			
			if(sortList.get(i).getName().equals(name))break;
			
			
			if(isBefore(name, sortList.get(i).getName())){
				min = ++i;
				//System.out.println(i + " is less than " + target);
			}else{
				max = --i;
				//System.out.println(i + " is greater than " + target);
			}
		}
		
		int j = i;
		
		while(true){
			if(sortList.get(i-1).getName().contentEquals(name))i--;
			else break;
		}
		
		while(true){
			if(sortList.get(j+1).getName().contentEquals(name))j++;
			else break;
		}
		
		return new ArrayList<Course>(sortList.subList(i, ++j));
	}
	
	public Course get(int i){
		return courses.get(i);
	}
	
	public Course delete(int i){
		//swap with last one
		if(courses.size() > 1)swap(i,courses.size()-1);
		Course course = courses.remove(courses.size()-1);
		if(courses.size() > 1)siftDown(i);
		return course;
	}
	
	public Course extract(){
		return delete(0);
	}
	
	public ArrayList<Course> extractList(){
		
		@SuppressWarnings("unchecked")
		ArrayList<Course> origList = (ArrayList<Course>) this.courses.clone();
		ArrayList<Course> ans = new ArrayList<Course>();
		
		for(int i=0;i<origList.size();i++){
			ans.add(this.extract());
		}
		
		this.courses = origList;
		
		return ans;
	}
	
	public int insert(Course course){
		courses.add(course);
		if(courses.size() > 1 && isBefore(courses.get(getParent(courses.size()-1)).getName(), course.getName())){
			return siftUp(courses.size()-1);
		}
		else return courses.size()-1;
	}
	
	private void swap(int i,int j){
		Course ii = courses.get(i);
		Course jj = courses.get(j);
		courses.set(i, jj);
		courses.set(j, ii);
	}
	
	private int getParent(int i){
        return (int)Math.floor((i-1)/2);
    }
	
	private int siftUp(int i){
		do {
			//do while person is more than it's parent, as this is a max heap
			//System.out.println(getParent(i) + " " + people.get(getParent(i)).getNames());
			//System.out.println(isBefore(people.get(i).getNames(), people.get(getParent(i)).getNames()));
			
			//assure we are not already at root of heap
			if(getParent(i) >= 0) {
				swap(i, getParent(i));
				i = getParent(i);
				//System.out.println("yeet " + i);
			}else {
				break;
			}
			
		} while (isBefore(courses.get(getParent(i)).getName(), courses.get(i).getName()));
		
		return i;
	}
	
	private int siftDown(int i){
		while(true){
			int childOne = i*2+1;
			int childTwo = i*2+2;
			
			int largerChild;
			if(childTwo < courses.size()) {
				largerChild = (isBefore(courses.get(childOne).getName(), courses.get(childTwo).getName())) ? childTwo : childOne;
			}
			else if(childOne < courses.size()) {
				largerChild = childOne;
			}else{
				return courses.size()-1;
			}
			
			//if i is less than it's larger child
			if(isBefore(courses.get(i).getName(),courses.get(largerChild).getName())){
				swap(i, largerChild);
				i = largerChild;
			}else return i;
		}
	}
	
	private boolean isBefore(String name, String otherName){
		int len = Math.min(name.length(), otherName.length());
		
		for(int j=0;j<len;j++){
			int nameOne = (int)name.charAt(j);
			int nameTwo = (int)otherName.charAt(j);
			if(nameOne > nameTwo) {
				//System.out.println((char)nameOne + " " + nameOne + " > " + (char)nameTwo + " " + nameTwo);
				return false;
			}
			if(nameOne < nameTwo) {
				//System.out.println((char)nameOne + " " + nameOne + " < " + (char)nameTwo + " " + nameTwo);
				return true;
			}
		}
		if(name.length() > otherName.length())return false;
		if(name.length() < otherName.length())return true;
		
		return false;
	}
	
	public void delete(Course course){
		courses.remove(course);
	}
	
}
