import com.splunk.*; 
import java.util.*;
import java.io.*;

public class jobs {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: jobs.py [list | show jobid | results jobid | delete jobid]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
			if(args[0].equalsIgnoreCase("list")) {
				//LAB: print each job's ID and search string (hint: eventSearch) to STDOUT
			}
			else if(args[0].equalsIgnoreCase("show")) {
				Job job = //LAB: get the job named in args[1]
				//LAB: print all job attributes to STDOUT
			}
			else if(args[0].equalsIgnoreCase("results")) {
				Job job = //LAB: get the job named in args[1]
		        InputStream response = //LAB: get the job results
		        int chr = 0;
				while (chr != -1) System.out.write(chr = response.read());
			}
			else if(args[0].equalsIgnoreCase("delete")) {
				Job job = //LAB: get the job named in args[1]
				//LAB: cancel the job
				System.out.println("  " + args[1] + " deleted.");
			}
			else {
				throw new Exception("Unknown command \"" + args[0] + "\"");
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