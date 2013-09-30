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
				for (String indexName : service.getIndexes().keySet())
					System.out.println("  " + indexName);
			}
			else if(args[0].equalsIgnoreCase("show")) {
				Index index = service.getIndexes().get(args[1]);
				for (String key : index.keySet())
					  System.out.println("  " + key + " = " + index.get(key));
			}
			else if(args[0].equalsIgnoreCase("clean")) {
				Index index = service.getIndexes().get(args[1]);
				index.clean(120);
				System.out.println("  index " + index.getName() + " cleaned.");
			}
			else if(args[0].equalsIgnoreCase("create")) {
				Index index = service.getIndexes().create(args[1]);
				System.out.println("  index " + index.getName() + " created.");
			}
			else if(args[0].equalsIgnoreCase("delete")) {
				service.getIndexes().remove(args[1]);
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