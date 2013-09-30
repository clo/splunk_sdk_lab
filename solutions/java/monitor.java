import com.splunk.*; 
import java.io.*;

public class monitor {

	public static void main (String[] args) {
		if(args.length < 1) {
			System.out.println("syntax: monitor \"search query\" [rt]");
			System.exit(0);
		}	
		Job job = null;
		try {
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
	        
	        JobArgs ja = new JobArgs();
	    	ja.put("auto_cancel", 300);
	        if(args.length == 2 && args[1].equalsIgnoreCase("rt")) { 
    	        ja.put("earliest_time", "rt");
    	    	ja.put("latest_time", "rt");
    	    	System.out.println("rt");
	        }
	        
			job = service.getJobs().create(args[0], ja);

			int offset = 0;
			Event event;
			
			while (true) {
				int total = job.getEventCount();
				if (total > offset) {
					Args outputArgs = new Args("count", total);
					outputArgs.put("offset", offset);
					InputStream resultSet = job.getEvents(outputArgs);
					ResultsReaderXml events = new ResultsReaderXml(resultSet);
					while ((event = events.getNextEvent()) != null) 
						System.out.println(event.get("_raw"));
					offset = total;
				} else Thread.sleep(100);
				if(job.isDone()) break;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally{
			job.cancel();
			
		}
	}
}
