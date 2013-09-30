import com.splunk.*; 
import java.util.*;
import java.io.*;

public class oneshot {

    public static void main(String[] args) {

		try {
			if(args.length == 0) {
				System.out.println("syntax: java oneshot \"search query\" [output]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
                JobArgs params = new JobArgs();
                String outputmode = "xml";
                if (!args[1].equals("")){
                   outputmode = args[1];                
                }
                params.add("output_mode",outputmode);
	        InputStream response = service.oneshotSearch(args[0],params);//LAB: call oneshot, passing args[0]
                //ResultReaderXML reader = new ResultReaderXML(repsone);
                
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
