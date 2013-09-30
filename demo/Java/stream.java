// An example of stream-based results handling for Splunk searches. 

import com.splunk.*; 
import java.io.*;

public class stream {

    public static void main(String[] args) {

		try {
			if(args.length  < 1) {
				throw new Exception("syntax: stream \"search query\" [rt]");
			}
			Command command = Command.splunk("info").parse(args);
	        
			Service service = Service.connect(command.opts);

	        JobArgs ja = new JobArgs();
			ja.put("auto_cancel", 300);             
	        ja.put("output_mode", "raw");
	        if(args.length == 2 && args[1].equalsIgnoreCase("rt")) {
	        	ja.put("earliest_time", "rt");        
			    ja.put("latest_time", "rt"); 
	        }
	        
	        // We call Service.export(), because it returns an InputStream for the live search results
	        InputStream response = service.export(args[0], ja);
	        
	        // This is a crude stream copy routine but it illustrates the basics:
	        // read from the results, write to the STDOUT stream
	        // Consider something more like org.apache.commons.io.IOUtils.copy(from, to) for robust 
	        // Java stream copy

	        int chr = 0;
			while (chr != -1) System.out.write(chr = response.read());						
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