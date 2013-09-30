import com.splunk.*; 
import java.util.*;
import java.io.*;

public class inputs {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: inputs [list | show inputname | create inputname index | delete inputname]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);

	        if(args[0].equalsIgnoreCase("list")) {
				//LAB: print all input names to STDOUT
			}
			else if(args[0].equalsIgnoreCase("show")) {
				Input ip = //LAB: get the input named in args[1]
				//LAB: print all input attributes to STDOUT
			}
			else if(args[0].equalsIgnoreCase("create")) {
				Args params = new Args();
				//LAB: add index = args[2] to params 
				Input ip = //LAB: create a new input with the path in args[1], kind = monitor, and pass params
				System.out.println("  input " + ip.getName() + " created for index " + args[2]);
			}
			else if(args[0].equalsIgnoreCase("delete")) {
				//LAB: delete the input named in args[1]
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