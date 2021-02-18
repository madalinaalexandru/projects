package tema2;

import java.util.*;

public class QueueArray{

	private int nbOfQueues;
	private int nbOfClients;
	private ArrayList<Queue> store;
	private int minServiceTime;
	private int maxServiceTime;
	
	public int getNbOfQueues() {
		return nbOfQueues;
	}

	public void setNbOfQueues(int nbOfQueues) {
		this.nbOfQueues = nbOfQueues;
	}

	public ArrayList<Queue> getStore() {
		return store;
	}

	public int getNbOfClients() {
		return nbOfClients;
	}

	public void setNbOfClients(int nbOfClients) {
		this.nbOfClients = nbOfClients;
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

	public QueueArray(int nbOfQueues, int nbOfClients, int minServiceTime, int maxServiceTime){
		
		this.nbOfClients = nbOfClients;
		this.nbOfQueues = nbOfQueues;
		this.maxServiceTime = maxServiceTime;
		this.minServiceTime = minServiceTime;
		
		int nbOfClientsPerQueue = nbOfClients/nbOfQueues;
		
		int restNbOfClientsPerQueue =  nbOfClients%nbOfQueues;
		
		this.store = new ArrayList<Queue>();
		
		store.add(new Queue(1, nbOfClientsPerQueue + restNbOfClientsPerQueue, this.minServiceTime, this.maxServiceTime));
		
		for(int i = 1; i < nbOfQueues; i++) {
			
			store.add(new Queue(i+1, nbOfClientsPerQueue, this.minServiceTime, this.maxServiceTime));
		}
	}
}
