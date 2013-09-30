import com.splunk.*; 
import java.util.*;
import java.io.*;

public class export {

    public static void main(String[] args) {

		try {
			if(args.length != 1) {
				System.out.println("syntax: export \"search query\"");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
	        InputStream response = service.export(args[0]);

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