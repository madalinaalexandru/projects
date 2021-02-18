package tema_4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MonitoredData {

	String start_time;
	String end_time;
	String activity;
	
	public MonitoredData() {
		
		start_time = null;
		end_time = null;
		activity = null;
	}
	
	public MonitoredData(String start_time, String end_time, String activity) {
		this.start_time = start_time;
		this.end_time = end_time;
		this.activity = activity;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getDate() {
		return start_time.substring(0, 10);
	}
	
	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}
	
	public long getTimeBetweenHours() {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		Date d1 = null, d2 = null;
		
		try {
			d1 = df.parse(start_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			d2 = df.parse(end_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long timeInMillis = Math.abs((d2.getTime() - d1.getTime()));
		
		long diffHours = timeInMillis / (60 * 60 * 1000) % 24;
		
		return diffHours;
	}
	
	public long getTimeBetweenMins() {
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		Date d1 = null, d2 = null;
		
		try {
			d1 = df.parse(start_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			d2 = df.parse(end_time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long timeInMillis = Math.abs((d2.getTime() - d1.getTime()));
		
		long diffMins = timeInMillis / (60 * 1000) % 60;
		
		if(diffMins < 5)
			return 1;
		else
			return 0;
	}
}
