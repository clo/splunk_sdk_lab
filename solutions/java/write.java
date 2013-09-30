import com.splunk.*; 
import com.splunk.JobArgs.ExecutionMode;

import java.util.*;
import java.io.*;

public class write {

    public static void main(String[] args) {

		try {
			if(args.length != 2) {
				System.out.println("syntax: write index \"data\"");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);

			Service service = Service.connect(command.opts);

	        Args params = new Args();
		        params.put("sourcetype", "mysourcetype");
		        params.put("source", "mysource");
		        params.put("host", "myhost");
	        
	        Index index = service.getIndexes().get(args[0]);
	        index.submit(params, new Date() + ": " + args[1]);
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