import com.splunk.*; 
import com.splunk.JobArgs.ExecutionMode;

import java.util.*;
import java.io.*;

public class page {

    public static void main(String[] args) {

		try {
			if(args.length != 1) {
				System.out.println("syntax: setmode \"search query\"");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

	        JobArgs ja = new JobArgs();
	        ja.setExecutionMode(ExecutionMode.BLOCKING); //forces the call to create() to block until complete
			Job job = service.getJobs().create(args[0], ja);

			int total = job.getResultCount(); 
			int offset = 0;
			int count = 5;
			while (offset < total) {
				System.out.println("====== PAGE " + (offset / count + 1) + " ============" );
				Args params = new Args();
				params.put("count", count);
				params.put("offset", offset);
				ResultsReaderXml set = new ResultsReaderXml(job.getResults(params));
				Event event;       
				while ((event = set.getNextEvent()) != null) {
					System.out.println("------ RESULT " + event.get("_serial") + " -------------------");
					System.out.flush();
					for (String key: event.keySet())     
						System.out.print(key+"="+event.get(key)+"| "); 
					System.out.println("--");
				}
				offset = offset + count; 
				System.in.read();
			}
		} 
    	catch (Exception e)  {
    		if(e.getMessage() != null) {
    			System.err.println("Error message: " + e.getMessage());
    		} else {
    			System.err.println("Error type: " + e.getClass());
    		}
		}
	}
}