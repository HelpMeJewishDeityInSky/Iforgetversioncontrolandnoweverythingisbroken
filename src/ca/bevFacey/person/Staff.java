package ca.bevFacey.person;

import java.util.UUID;

public class Staff extends Person {

	private UUID staffId;
	private EmergencyContact[] emergencyContacts;
	
	public Staff(String[] names, String gender, int[] birthDate, String homePhone, String mailingAddress, 
			EmergencyContact[] emergencyContacts, String title) {
		super(names, gender, birthDate, homePhone, mailingAddress, title);

		this.staffId = UUID.randomUUID();
		this.emergencyContacts = emergencyContacts;
	}

	public void setEmergencyContacts(EmergencyContact[] emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}

	public UUID getStaffId() {
		return staffId;
	}

	public EmergencyContact[] getEmergencyContacts() {
		return emergencyContacts;
	}
	
	@Override
	public String toString(){
		return super.toString() + '\n'
				+ "with a staff ID of " + staffId + '\n'
				+ "and Emergency Contacts " + emergencyContacts[0].getNames()[0] + ' '
						+ emergencyContacts[0].getNames()[1] + ' '
						+ emergencyContacts[0].getNames()[2]
						+ " and " + emergencyContacts[1].getNames()[0] + ' '
						+ emergencyContacts[1].getNames()[1] + ' '
						+ emergencyContacts[1].getNames()[2];
	}

}
