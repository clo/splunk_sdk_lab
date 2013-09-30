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
	        //LAB: add output mode to searchArgs and set it to the value of args[1]
	        InputStream response = //LAB: call oneshot and pass it the query in args[0] and searchArgs

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