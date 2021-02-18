package model;

public class Client extends Object {

	int id;
	String name;
	String address;
	String email;
	String phoneNb;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNb() {
		return phoneNb;
	}
	public void setPhoneNb(String phoneNb) {
		this.phoneNb = phoneNb;
	}
	
	public Client(int id, String name, String address, String email, String phoneNb) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNb = phoneNb;
	}
	
	public Client() {
		super();
		this.id = 0;
		this.name = new String();
		this.address = new String();
		this.email = new String();
		this.phoneNb = new String();
	}
	
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phoneNb=" + phoneNb + "]";
	}
}
