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
	    	//LAB: add name/value pair to ja for auto cancel set to 5 minutes 
	        if(args.length == 2 && args[1].equalsIgnoreCase("rt")) { 
    	        //LAB: add name/value pairs to ja for realtime search
	        }
	        
			job = //LAB: create a search job using the query in args[0] and ja

			int offset = 0;
			Event event;
			
			while (true) {
				int total = //LAB: get the total number of events in job so far
				if (total > offset) {
					Args outputArgs = new Args();
					//LAB: add name/value pairs outputArgs for offset=offset and count=total 
					InputStream resultSet = //LAB: get the job's events, passing outputArgs
					ResultsReaderXml events = //LAB: convert resultSet to a ResultsReaderXML
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
