package tema_4;

public class MainClass {

	public static void main(String args[]) {
		
		ProcessData p = new ProcessData();
		
		p.processDataActivities(); //p va avea campul activities actualizat la contintul fisierului, se vor deschide fisierele necesare pentru afisarea rezultatelor
		
		//for(MonitoredData d : p.activities) {
		//	System.out.println(d.getStart_time() + "\t" + d.getEnd_time() + "\t" + d.getActivity());
		//}
		
		p.numberOfDays(); //Count the distinct days that appear in the monitoring data.
		
		p.nbOfTimesPerActivity(); //Determine a map of type <String, Integer> that maps to each distinct action type the number of occurrences in the log. Write the resulting map into a text file.
		
		p.activityCountPerDay(); //Generates a data structure of type Map<Integer, Map<String, Integer>> that contains the activity count for each day of the log (task number 2 applied for each day of the log) and writes the result in a text file.

		p.timePerActivity(); //Determine a data structure of the form Map<String, DateTime> that maps for each activity the total duration computed over the monitoring period. Filter the activities with total duration larger than 10 hours. Write the result in a text file.

		p.filterActivities(); //Filter the activities that have 90% of the monitoring samples with duration less than 5 minutes, collect the results in a List<String> containing only the distinct activity names and write the result in a text file.

	}
}
