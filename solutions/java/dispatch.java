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
			ssca.setOwner("-");
			ssca.setApp("-");
			ssca.setSearch(args[0]);
			SavedSearchCollection ssc = service.getSavedSearches(ssca);
			if(args[0].equalsIgnoreCase("run")) {
				if(!ssc.containsKey(args[1])) {
					throw new Exception("No such saved search: " + args[1]);
				}
				SavedSearch ss = ssc.get(args[1]);
				Job job = ss.dispatch();
				
	        	System.out.print("Job is running");
	        	while(!job.isDone()) {
	        		System.out.print(".");
	        		Thread.sleep(1000);
	        	}
	        	System.out.println("complete!");
		        InputStream response = job.getResults();
		        int chr = 0;
				while (chr != -1) System.out.write(chr = response.read());
			}
			else if(args[0].equalsIgnoreCase("list")) 
				for (SavedSearch s: ssc.values()) 
					System.out.println(s.getName()); 
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