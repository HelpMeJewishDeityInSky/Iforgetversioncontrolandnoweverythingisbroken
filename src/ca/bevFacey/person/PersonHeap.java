package ca.bevFacey.person;

import java.util.ArrayList;

public class PersonHeap {
	
	private ArrayList<Person> people = new ArrayList<Person>();
	private ArrayList<Person> peopleSorted = new ArrayList<Person>();
	private boolean updated = false;
	
	private ArrayList<Person> peopleByPhone = new ArrayList<Person>();
	private boolean phoneUpdated = false;
	
	public PersonHeap() {
		
	}
	
	public void debug(){
		for(int i=0;i<people.size();i++){
			Person person = people.get(i);
			for(int j=0;j<3;j++){
				System.out.print(person.getNames()[j] + " " + (int)person.getNames()[j].charAt(0) + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void testSort() {
		ArrayList<Person> sortList = extractList();
		for(int i =0;i <sortList.size( );i ++) {
			System.out.println(sortList.get(i).getFirstName() + " " + sortList.get(i).getMiddleName() + " " + sortList.get(i).getLastName());
		}
	}
	
	public ArrayList<Person> searchByName(String[] names){
		
		for(int i=0;i<3;i++) {
			names[i] = names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
		}
		
		
		
		String name = (names[0] + names[1] + names[2]);
		
		ArrayList<Person> sortList;
		//long startTime = new Date().getTime();
		if(updated) {
			System.out.println("SORTING");
			updated = false;
			peopleSorted = extractList();
		}
		//System.out.println("extract time: " + (new Date().getTime() - startTime));
		
		sortList = peopleSorted;
		
		//System.out.println(sortList);
		
		int min = 0;
		int max = sortList.size()-1;
		int i = 0;
		int c = 0;
		while(true){	
			c++;
			if(c>100) {
				System.out.println("stopped at 100");
				return new ArrayList<Person>();
			}
			i = Math.round(((max-min)/2)+min);
			
			String cName = (sortList.get(i).getFirstName() + sortList.get(i).getMiddleName() + sortList.get(i).getLastName());
			
			if(name.toLowerCase().contentEquals(cName.toLowerCase()))break;
			System.out.print(min + " ");
			System.out.print(max + " ");
			System.out.print(i + " ");
			System.out.println(cName);
			System.out.println(names[0] + names[1] + names[2]);
			
			System.out.println(isBefore(names, sortList.get(i).getNames()));
			
			if(isBefore(names, sortList.get(i).getNames())){
				min = ++i;
				//System.out.println(i + " is less than " + target);
			}else{
				max = --i;
				//System.out.println(i + " is greater than " + target);
			}
		}
		
		int j = i;
		while(true){
			String cName = (sortList.get(i-1).getFirstName()
					+ ((!names[1].contentEquals(""))?sortList.get(i-1).getMiddleName():"")
					+ ((!names[1].contentEquals(""))?sortList.get(i-1).getLastName():""));
			
			if(name.toLowerCase().contentEquals(cName.toLowerCase())) {
				i--;
			}else break;
		}
		
		while(true){
			String cName = (sortList.get(j+1).getFirstName()
					+ ((!names[1].contentEquals(""))?sortList.get(j+1).getMiddleName():"")
					+ ((!names[1].contentEquals(""))?sortList.get(j+1).getLastName():""));
			if(name.toLowerCase().contentEquals(cName.toLowerCase())){
				j++;
			}else break;
		}
		
		return new ArrayList<Person>(sortList.subList(i, ++j));
	}
	
	public ArrayList<Person> searchByPhone(String phone){
		//dumb warning, there is no other fix
		@SuppressWarnings("unchecked")
		ArrayList<Person> unsorted = (ArrayList<Person>) this.people.clone();
		ArrayList<Person> sorted;
		if(phoneUpdated) {
			//sort list if not sorted
			phoneUpdated = false;
			peopleByPhone = sortByPhone(unsorted, 0, unsorted.size()-1);
		}
		sorted = peopleByPhone;
		
		int min = 0;
		int max = sorted.size()-1;
		int i = 0;
		int c = 0;
		while(true){	
			c++;
			if(c>200)return new ArrayList<Person>();
			i = Math.round(((max-min)/2)+min);
			
			if(sorted.get(i).getHomePhone().equals(phone))break;
			
			
			if(isBefore(phone, sorted.get(i).getHomePhone())){
				max = --i;
				//System.out.println(phone + " is less than " + sorted.get(i).getHomePhone() + " at i=" + i);
			}else{
				min = ++i;
				//System.out.println(phone + " is greater than " + sorted.get(i).getHomePhone() + " at i=" + i);
			}
		}
		
		int j=i;
		
		while(true){
			if(sorted.get(i-1).getHomePhone().contentEquals(phone))i--;
			else break;
		}
		
		while(true){
			if(sorted.get(j+1).getHomePhone().contentEquals(phone))j++;
			else break;
		}
		
		return new ArrayList<Person>(sorted.subList(i, ++j));
		
	}
	
	private ArrayList<Person> sortByPhone(ArrayList<Person> list, int lo, int hi){
		if(lo < hi){
			int p = partitionByPhone(list, lo, hi);
			sortByPhone(list, lo, p-1);
			sortByPhone(list, p+1, hi);
		}
		return list;
	}
	
	private int partitionByPhone(ArrayList<Person> people, int lo, int hi){
		String pivot = people.get(hi).getHomePhone();
		int i = lo;
		
		for(int j=lo;j<hi;j++){
			if(isBefore(people.get(j).getHomePhone(), pivot)){
				Person ii = people.get(i);
				Person jj = people.get(j);
				people.set(i, jj);
				people.set(j, ii);
				i++;
			}
		}
		
		Person ii = people.get(i);
		Person jj = people.get(hi);
		people.set(i, jj);
		people.set(hi, ii);
		
		return i;
	}
	
	public Person get(int i){
		return people.get(i);
	}
	
	public Person delete(int i){
		//swap with last one
		updated = true;
		phoneUpdated = true;
		if(people.size() > 1) {
			swap(i,people.size()-1);
			siftDown(i);
		}
		return people.remove(people.size()-1);
	}
	
	public Person extract(){
		return delete(0);
	}
	
	public ArrayList<Person> extractList(){
		
		boolean wasUpdated = updated;
		
		@SuppressWarnings("unchecked")
		ArrayList<Person> origList = (ArrayList<Person>) this.people.clone();
		ArrayList<Person> ans = new ArrayList<Person>();
		
		//long startTime = new Date().getTime();
		for(int i=0;i<origList.size();i++){
			ans.add(extract());
		}
		//System.out.println("loop time: " + (new Date().getTime() - startTime));
		
		this.people = origList;
		
		updated = wasUpdated;
		
		return ans;
	}
	
	public int insert(Person person){
		updated = true;
		phoneUpdated = true;
		people.add(person);
		//phone list is WIP
		//peopleByPhone.add(person);
		if(people.size() > 1 && isBefore(people.get(getParent(people.size()-1)).getNames(), person.getNames())){
			//System.out.println(isBefore( people.get( getParent(people.size()-1) ).getNames(), person.getNames() ));
			//System.out.println(people.get(getParent(people.size()-1)).getNames()[2]);
			//System.out.println(people.get(people.size()-1).getNames()[2]);
			return siftUp(people.size()-1);
		}
		else return people.size()-1;
	}
	
	private void swap(int i,int j){
		Person ii = people.get(i);
		Person jj = people.get(j);
		people.set(i, jj);
		people.set(j, ii);
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
			
		} while (isBefore(people.get(getParent(i)).getNames(), people.get(i).getNames()));
		
		return i;
	}
	
	private int siftDown(int i){
		while(true){
			int childOne = i*2+1;
			int childTwo = i*2+2;
			
			int largerChild;
			if(childTwo < people.size()) {
				largerChild = (isBefore(people.get(childOne).getNames(), people.get(childTwo).getNames())) ? childTwo : childOne;
			}
			else if(childOne < people.size()) {
				largerChild = childOne;
			}else{
				return people.size()-1;
			}
			
			//if i is less than it's larger child
			if(isBefore(people.get(i).getNames(),people.get(largerChild).getNames())){
				swap(i, largerChild);
				i = largerChild;
			}else return i;
		}
	}
	
	private boolean isBefore(String[] names, String[] otherNames){
		
		for(int i = 2;i>=0;i--){
			int len = Math.min(names[i].length(), otherNames[i].length());
			
			for(int j=0;j<len;j++){
				int nameOne = (int)names[i].charAt(j);
				int nameTwo = (int)otherNames[i].charAt(j);
				if(nameOne > nameTwo) {
					//System.out.println((char)nameOne + " " + nameOne + " > " + (char)nameTwo + " " + nameTwo);
					return false;
				}
				if(nameOne < nameTwo) {
					//System.out.println((char)nameOne + " " + nameOne + " < " + (char)nameTwo + " " + nameTwo);
					return true;
				}
			}
			if(names[i].length() > otherNames[i].length())return false;
			if(names[i].length() < otherNames[i].length())return true;
		}
		
		return false;
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
	
	public void delete(Person person){
		people.remove(person);
		updated = true;
		phoneUpdated = true;
	}
	
}
