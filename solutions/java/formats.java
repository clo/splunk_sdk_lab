import com.splunk.*; 
import java.util.*;
import java.io.*;

public class formats {

    public static void main(String[] args) {

		try {
			if(args.length != 2) {
				System.out.println("syntax: oneshot \"search query\" output_mode");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
	        Args searchArgs = new Args();
	        searchArgs.put("output_mode", args[1]);
	        InputStream response = service.oneshotSearch(args[0], searchArgs);

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