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
	        	//LAB: add name/value pairs for sourcetype, source, and host, setting any static value for each as desired
	        
	        Index index = //LAB: get the index named in args[0]
	        //LAB: submit the data in args[1] to the index, passing the params, and add a date/time stamp to the beginning of the data
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