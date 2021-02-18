package tema2;

import java.util.ArrayList;

public class Queue implements Runnable{

	private ArrayList<Person> clients;
	private int nbOfClientsPerQueue;
	private int minServiceTime;
	private int maxServiceTime;
	private int id;
	private Thread t;
	
	public Queue(int id, int nbOfClientsPerQueue, int minServiceTime, int maxServiceTime){
		this.clients = new ArrayList<Person>();
		this.id = id;
		this.minServiceTime = minServiceTime;
		this.maxServiceTime = maxServiceTime;
		
		for(int i = 0; i < nbOfClientsPerQueue; i++) {
			this.clients.add(new Person(i+1, this.minServiceTime, this.maxServiceTime));
		}
	}
	
	public ArrayList<Person> getClients() {
		return clients;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNbOfClientsPerQueue() {
		return nbOfClientsPerQueue;
	}

	public void setNbOfClientsPerQueue(int nbOfClientsPerQueue) {
		this.nbOfClientsPerQueue = nbOfClientsPerQueue;
	}

	public int getMinServiceTime() {
		return minServiceTime;
	}

	public void setMinServiceTime(int minServiceTime) {
		this.minServiceTime = minServiceTime;
	}

	public int getMaxServiceTime() {
		return maxServiceTime;
	}

	public void setMaxServiceTime(int maxServiceTime) {
		this.maxServiceTime = maxServiceTime;
	}

	public void run() {
		
		int i = 0;

		while(!this.getClients().isEmpty()) {
			int sleepTime = this.getClients().get(i).getServiceTime();
			
			try {
				Thread.sleep(sleepTime*1000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.getClients().remove(i);
		}
	}
	
	 public void start () {
	      if (t == null) {
	         t = new Thread (this);
	         t.start();
	      }
	   }
}
