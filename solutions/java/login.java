import com.splunk.*; 
import java.util.*;

public class login {

	public static void main(String[] args) {
		if(args.length < 4) {
			System.out.println("syntax: login uid pwd host port");
			System.exit(0);
		} else {	
			Args loginArgs = new Args();
			loginArgs.add("username", args[0]);
			loginArgs.add("password", args[1]);
			loginArgs.add("host", args[2]);
			loginArgs.add("port", Integer.parseInt(args[3]));	
					
			try {
				Service service = Service.connect(loginArgs);
				
				System.out.println("Authenticated, using token " + service.getToken());
				
			} catch (Exception e)  {
				System.err.println("The error type was: " + e.getMessage());
			
			}
		}
	}
}