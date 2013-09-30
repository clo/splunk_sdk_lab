import com.splunk.*; 
import java.util.*;
import java.io.*;

public class dispatch {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: dispatch [list | run \"search name\"]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
			SavedSearchCollectionArgs ssca = new SavedSearchCollectionArgs();
			//LAB: set the owner and app in ssca to wildcards
			SavedSearchCollection ssc = //LAB: get all saved searches matching the settings in ssca 
			if(args[0].equalsIgnoreCase("run")) {
				if(!ssc.containsKey(args[1])) {
					throw new Exception("No such saved search: " + args[1]);
				}
				SavedSearch ss = //LAB: get the saved search named in args[1]
				Job job = //LAB: run the saved search 
				
	        	System.out.print("Job is running");
	        	while(//LAB: ...the job is not done) {
	        		System.out.print(".");
	        		Thread.sleep(1000);
	        	}
	        	System.out.println("complete!");
		        InputStream response = //LAB: get the job results 
		        int chr = 0;
				while (chr != -1) System.out.write(chr = response.read());
			}
			else if(args[0].equalsIgnoreCase("list")) 
				//LAB: add a for loop that prints each saved search name 
			else 
				throw new Exception("unknown command " + args[0]);
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