
import splunklib.client as client
import utils, time, sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 3:
    print "Syntax: setmode.py \"search query\" [normal \ blocking]"
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)
    
    if sys.argv[2] == "normal":     params = {"exec_mode" : "normal"}
    elif sys.argv[2] == "blocking": params = {"exec_mode" : "blocking"}
    else: raise Exception("unknown output mode")
    
    job = service.jobs.create(sys.argv[1], **params)
    
    if sys.argv[2] == "normal":
        sys.stdout.write("Job is running")
        sys.stdout.flush()
        while True:
            sys.stdout.write(".")
            sys.stdout.flush()
            time.sleep(1)
            job.refresh()
            if job.is_done():
                break    
    
    response = job.results()    
    print response

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
