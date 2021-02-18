package tema2;

public class Person {

	private int id;
	private int serviceTime;
	private int maxServiceTime;
	private int minServiceTime;
	
	public Person(int id, int maxServiceTime, int minServiceTime) {
		this.id = id;
		
		this.maxServiceTime = maxServiceTime;
		this.minServiceTime = minServiceTime;
		
		this.serviceTime = (int)(Math.random() * (this.maxServiceTime - this.minServiceTime+1)) + this.minServiceTime;
		
	}
	
	public Person() {
		
		this.maxServiceTime = 5;
		this.minServiceTime = 1;
		
		this.serviceTime = (int)(Math.random() * (this.maxServiceTime - this.minServiceTime+1)) + this.minServiceTime;
		
		this.id = 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}
}