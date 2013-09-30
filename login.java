import com.splunk.*; 
import java.util.*;

public class login {

	public static void main(String[] args) {
		if(args.length < 4) {
			System.out.println("syntax: login uid pwd host port");
			System.exit(0);
		} else {	
			ServiceArgs loginArgs = new ServiceArgs();
			//LAB: add arguments to loginArgs for username, password, host and port (hint: port is an int)
			loginArgs.setUsername(args[0]);
			loginArgs.setPassword(args[1]);
			loginArgs.setHost(args[2]);
			loginArgs.setPort(new Integer(args[3]));
					
			try {
				Service service = Service.connect(loginArgs);//LAB: connect using loginArgs
				
				System.out.println("Authenticated, using token " + service.getToken());//LAB: get the service token
				
			} catch (Exception e)  {
				System.err.println("The error type was: " + e.getMessage());
			
			}
		}
	}
}
