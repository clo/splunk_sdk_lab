
import splunklib.client as client
import utils, sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
try: 
    if len(sys.argv) == 1:
        print "syntax: jobs.py [list | show jobid | results jobid | delete jobid]"
        sys.exit()
    
    service = client.connect(**opts.kwargs)

    if sys.argv[1] == "list":
        #LAB: print each job's ID and search string (hint: eventSearch) to STDOUT
        
    elif sys.argv[1] == "show":
        job = #LAB: get the job named in sys.argv[2]
        #LAB: print all job attributes to STDOUT

    elif sys.argv[1] == "results":
        job = #LAB: get the job named in sys.argv[2]
        if not job.is_done():
            raise Exception("Job not complete")
        response = #LAB: get the job results
        print response
        
    elif sys.argv[1] == "delete":
        job = #LAB: get the job named in sys.argv[2]
        #LAB: cancel the job
        print "  job %s deleted" % job.sid
 
    else:
        raise Exception("unknown command \"%s\"" % sys.argv[1])

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
