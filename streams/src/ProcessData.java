package tema_4;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProcessData {

	ArrayList<MonitoredData> activities;
	String file;
	
	FileWriter fileWriter;
	PrintWriter printWriter;
	
	FileWriter fileWriter1;
	PrintWriter printWriter1;
	
	FileWriter fileWriter2;
	PrintWriter printWriter2;
	
	FileWriter fileWriter3;
	PrintWriter printWriter3;
	
	public ProcessData() {
		this.activities = new ArrayList<MonitoredData>();
		
		this.file = new String("activities.txt");
		
		try {
			this.fileWriter = new FileWriter("output.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.printWriter = new PrintWriter(fileWriter);
		
		try {
			this.fileWriter1 = new FileWriter("output1.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.printWriter1 = new PrintWriter(fileWriter1);
		
		try {
			this.fileWriter2 = new FileWriter("output2.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.printWriter2 = new PrintWriter(fileWriter2);
		
		try {
			this.fileWriter3 = new FileWriter("output3.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.printWriter3 = new PrintWriter(fileWriter3);
	}
	
	public void processDataActivities() { 
		
	    try (Stream<String> stream = Files.lines(Paths.get(file))) {

	    	activities = stream.map(line -> line.split("\t\t"))
	    			.map(factory::apply)  
	                .collect(Collectors.toCollection(ArrayList::new));

	    } catch (IOException e) {

	        e.printStackTrace();
	    }
	}
	
	Function<String[],MonitoredData> factory = data->{return new MonitoredData(data[0], data[1], data[2]);}; //functie care defineste un nou field in ArrayList-ul activities

	public void numberOfDays() {
		
		System.out.println("Number of distinct days: " + activities.stream().map(date -> date.getDate()).distinct().count());
	 }
	
	public void nbOfTimesPerActivity(){
		
		printWriter.append("Each activity and the number of times it is monitored:\r\n");
		
		activities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()))
				.forEach((key, value) -> printWriter.append("number of times: " + value + "\tActivity: " + key + "\r\n"));
		
		printWriter.close();
		
	}
	
	public void activityCountPerDay(){
		
		printWriter1.append("Activity count for each day of the log:\r\n");
		
		activities.stream()
			.collect(Collectors.groupingBy(MonitoredData::getDate, Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting())))
			.forEach((key, value) -> printWriter1.append("date: " + key+ "\t" + value + "\r\n"));
		
		printWriter1.close();
	}
	
	public void timePerActivity() {
		
		printWriter2.append("Duration for each activity:\r\n");
		
		activities.stream()
			.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingLong(MonitoredData::getTimeBetweenHours)))
			.forEach((key, value) -> {if(value>=10) printWriter2.append("Activity: " + key + " Time: " + value + "\r\n");});
		
		printWriter2.close();
	}
	
	public void filterActivities() {
		
		printWriter3.append("Filtered activities:\r\n");
		
		ArrayList<String> activitiesFiltered = new ArrayList<String>();
		
		Map<String, Long> countUnderFiveMins = activities.stream()
						.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.summingLong(MonitoredData::getTimeBetweenMins)));
		
		Map<String, Long> count = activities.stream()
				.collect(Collectors.groupingBy(MonitoredData::getActivity, Collectors.counting()));
		
		for(Map.Entry<String, Long> entryCountUnderFiveMins : countUnderFiveMins.entrySet()) {
			
			for(Map.Entry<String, Long> entryCount : count.entrySet()) {
				
				if(entryCountUnderFiveMins.getKey() == entryCount.getKey()) {
					
					if(entryCountUnderFiveMins.getValue() > 0.9 * entryCount.getValue()) {
						activitiesFiltered.add(entryCount.getKey());
					}
				}
			}
		}
		
		activitiesFiltered.forEach(s ->  printWriter3.append("Activity: " + s + "\r\n"));
		
		printWriter3.close();
	}
}
