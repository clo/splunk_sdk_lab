import com.splunk.*; 

public class jobstate {

	public static void main (String[] args) {
		if(args.length < 1) {
			System.out.println("syntax: jobstate \"search query\"]");
			System.exit(0);
		}
		Command command = Command.splunk("info").parse(args);
        Service service = Service.connect(command.opts);

		Job job = service.getJobs().create(args[0]);

		while(!job.isDone()) {
			System.out.println(job.getDispatchState() + ", " + job.getDoneProgress() + ", " +  job.getEventCount()  + ", " + job.getEventAvailableCount());
		}
		System.out.println("Done, " + job.getResultCount() + " events.");
	}
}
