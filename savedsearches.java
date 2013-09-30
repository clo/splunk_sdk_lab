import com.splunk.*; 
import java.util.*;
import java.io.*;

public class savedsearches {

    public static void main(String[] args) {

		try {
			if(args.length < 1) {
				System.out.println("syntax: savedsearch [list owner app | \"search name\"]");
				System.exit(0);
			}
			Command command = Command.splunk("info").parse(args);
	        Service service = Service.connect(command.opts);
			SavedSearchCollectionArgs ssca = new SavedSearchCollectionArgs();
			if(args.length == 1) {
				//LAB: add wildcards for owner and app to ssca, and set search = args[0]
                                ssca.setOwner("-");
                                ssca.setApp("-");
                                ssca.setSearch(args[0]);
				SavedSearchCollection ssc = service.getSavedSearches(ssca);//LAB: get all saved searches matching sscaservice.getSavedSearches(ssca);
				if(!ssc.containsKey(args[0])) {
					throw new Exception("No such saved search: " + args[0]);
				}
				SavedSearch ss = ssc.get(args[0]);
				System.out.println("Getting info for " + ss.getName());
		        //LAB: print all attributes of ss to STDOUT
                                for (String key : ss.keySet()) {
                                  System.out.println("    " + key + ": " + ss.get(key));
                                }
			}
			if(args[0].equalsIgnoreCase("list")) {
				//LAB: add name/value pairs to ssca for owner (from args[1] and app [from args[2]
				ssca.setOwner(args[1]);
                                ssca.setApp(args[2]);
				SavedSearchCollection ssc = service.getSavedSearches(ssca);//LAB: get all saved searches matching ssca 
				System.out.println("List of Saved Searches for context: " + args[1] + "/" + args[2] + ":");
				for (SavedSearch s: ssc.values()) {
					System.out.println(s.getName() + " (" + s.getMetadata().getOwner() + "/" + s.getMetadata().getApp() + ")"); 
				}
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
