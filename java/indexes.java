import com.splunk.*; 
import java.util.*;
import java.io.*;

public class indexes {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: indexes.py [list | show indexname | clean indexname | create indexname | delete indexname]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

	        if(args[0].equalsIgnoreCase("list")) {
				//LAB: list all indexes by name to STDOUT
			}
			else if(args[0].equalsIgnoreCase("show")) {
				Index index = //LAB: get the index named in args[1]
				//LAB: print all index attributes
			}
			else if(args[0].equalsIgnoreCase("clean")) {
				Index index = //LAB: get the index named in args[1]
				//LAB: clean the index
				System.out.println("  index " + index.getName() + " cleaned.");
			}
			else if(args[0].equalsIgnoreCase("create")) {
				Index index = //LAB: create the index named in args[1]
				System.out.println("  index " + index.getName() + " created.");
			}
			else if(args[0].equalsIgnoreCase("delete")) {
				//LAB: delete the index named in args[1]
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