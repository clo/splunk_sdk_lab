import com.splunk.*; 
import com.splunk.JobArgs.ExecutionMode;

import java.util.*;
import java.io.*;

public class searchmode {

    public static void main(String[] args) {

		try {
			if(args.length != 2) {
				System.out.println("syntax: setmode \"search query\" [normal | blocking]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

	        JobArgs ja = new JobArgs();

	        if(args[1].equalsIgnoreCase("normal"))
	        		ja.setExecutionMode(ExecutionMode.NORMAL);
	        else if(args[1].equalsIgnoreCase("blocking"))
	        		ja.setExecutionMode(ExecutionMode.BLOCKING);
	        else 
	        	throw new Exception("Unknown mode " + args[1]);
	        	
			Job job = service.getJobs().create(args[0], ja);

	        if(args[1].equalsIgnoreCase("normal")) {
	        	System.out.print("Job is running");
	        	while(!job.isDone()) {
	        		System.out.print(".");
	        		Thread.sleep(1000);
	        	}
	        	System.out.println("complete!");
	        } 
	        InputStream response = job.getResults();
	        String line;	        
 			BufferedReader results = new BufferedReader(new InputStreamReader(response));
 			while ((line = results.readLine() ) != null) {
 				System.out.println(line);
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