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
	        ja.setExecutionMode(ExecutionMode.BLOCKING);
			Job job = //LAB: create a job using the query in args[0] and ja

			int total = //LAB: get the total number of results from the job
			int offset = 0;
			int count = 5;
			while (offset < total) {
				System.out.println("====== PAGE " + (offset / count + 1) + " ============" );
				Args params = new Args();
				//LAB: add name/value pairs to params for count = count and offset = offset
				ResultsReaderXml set = //LAB: get the results from the job, passing params, and use them to create a new ResultsReaderXML
				Event event;       
				while ((event = set.getNextEvent()) != null) {
					System.out.println("------ RESULT " + //LAB: get the event's _serial field value + " -------------------");
					System.out.flush();
					for (String key: event.keySet())     
						System.out.print(key + "=" + event.get(key) + "| "); 
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