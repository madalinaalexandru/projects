package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
	
	@Id
	private String id;
	
	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String address;

	@Column
	private String phoneNumber;

	@Column
	private boolean marriageStatus;

	@Column
	private boolean legalStatus;

	@OneToMany(mappedBy = "user")
	public List<Request> requests;

	@OneToMany(mappedBy = "user")
	public List<Property> properties;

	public User(String firstName, String lastName, String email, String password, String address, String phoneNumber,
				boolean marriageStatus, boolean legalStatus) {

		UUID id = UUID.randomUUID();

		properties = new ArrayList<Property>();

		this.id = id.toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.marriageStatus = marriageStatus;
		this.legalStatus = legalStatus;
	}
	
	public User() {

		properties = new ArrayList<>();
		requests = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(boolean marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public boolean isLegalStatus() {
		return legalStatus;
	}

	public void setLegalStatus(boolean legalStatus) {
		this.legalStatus = legalStatus;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public List<Request> getRequests() {
		return requests;
	}
}
