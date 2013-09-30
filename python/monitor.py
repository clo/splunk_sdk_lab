import splunklib.client as client
import splunklib.results as results
import utils, sys, time

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) < 2:
    print "Syntax: monitor.py \"search query\" [rt]"
    sys.exit()
try: 
    service = client.connect(**opts.kwargs)
 
    if len(sys.argv) > 2 and (sys.argv[2] == 'rt'):
        params = {#LAB: add name/value pairs for auto cancel in 5 minutes and real-time search 
        print "realtime"
    else:
        params = {#LAB: add name/value pair for auto cancel in 5 minutes
        print "normal"

    job = #LAB: create the job using the query in sys.argv[1] and params
  
    offset = 0
    while True:
        job.refresh()
        total = int(job['eventCount'])
        if total > offset:
            resultSet = job.events(**{"offset" : offset, "count" : total})
            events = results.ResultsReader(resultSet)
            for event in events:
                print event['_raw']
            offset = total
	      		if job.is_done(): break
        else:
            time.sleep(0.1)
                        
except KeyboardInterrupt:
    print "bye"

except Exception, err:
    sys.stderr.write("Exception: " + str(err))
    
finally:
    job.cancel()
