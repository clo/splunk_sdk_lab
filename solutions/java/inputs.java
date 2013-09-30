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
				for (String inputName : service.getInputs().keySet())
					System.out.println("  " + inputName);
			}
			else if(args[0].equalsIgnoreCase("show")) {
				Input ip = service.getInputs().get(args[1]);
				for (String key : ip.keySet())
					  System.out.println("  " + key + " = " + ip.get(key));
			}
			else if(args[0].equalsIgnoreCase("create")) {
				Args params = new Args();
				params.put("index", args[2]);
				Input ip = service.getInputs() .create(args[1], InputKind.Monitor, params);
				System.out.println("  input " + ip.getName() + " created for index " + args[2]);
			}
			else if(args[0].equalsIgnoreCase("delete")) {
				service.getInputs().remove(args[1]);
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