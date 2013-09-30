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
        params = {"auto_cancel": 300, "earliest_time" : "rt", "latest_time" : "rt"}
        print "realtime"
    else:
        params = {"auto_cancel": 300}
        print "normal"

    job = service.jobs.create(sys.argv[1], **params)
  
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
        else:
            time.sleep(0.1)
        if job.is_done(): break
                        
except KeyboardInterrupt:
    print "bye"

except Exception, err:
    sys.stderr.write("Exception: " + str(err))
    
finally:
    job.cancel()
