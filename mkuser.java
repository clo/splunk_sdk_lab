import com.splunk.*; 
import java.util.*;
import java.io.*;

public class mkuser {

    public static void main(String[] args) {

		try {
			if(!(args.length == 4)) {
				System.out.println("syntax: mkuser username password role \"real name\"");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

	        User user = //LAB: create the user named in args[0], password in args[1], and role in args[2]
	        //LAB: set the user's real name to args[3]
	        //LAB: send the user changes to the server
	        System.out.println("User " + user.getName() + " created.");
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