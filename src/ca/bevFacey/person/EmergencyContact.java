package ca.bevFacey.person;

public class EmergencyContact extends Person {

	private String relationship;
	
	public EmergencyContact(String[] names, String homePhone, String relationship) {
		super(names, homePhone, "Emergency Contact");
		
		this.relationship = relationship;
	}

	public String getRelationship() {
		return relationship;
	}
	
	@Override
	public String toString(){
		return super.getNames()[0] + " " + super.getNames()[1] + " " + super.getNames()[2] + " is an Emergency Contact" + '\n'
				+ "their relationship is " + relationship + '\n'
				+ "they can be reached at " + super.getHomeNumber();
	}
	
}
