import com.splunk.*; 
import java.util.*;
import java.io.*;

public class users {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: users [list | username]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

			if(args[0].equalsIgnoreCase("list")) {
				System.out.println("List of users:");
				for (User u: service.getUsers().values()) {
					System.out.println("  " +u.getName()); 
				}
			} else {
				User u = service.getUsers().get(args[0]);
				System.out.println("Settings for user " + u.getName());
				for (String key : u.keySet())
		            System.out.println("    " + key + ": " + u.get(key));
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