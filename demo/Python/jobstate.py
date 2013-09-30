
import sys, time, utils
import splunklib.client as client

usage = "usage: jobstate.py <search>"
opts = utils.parse(sys.argv[1:], {}, ".splunkrc", usage=usage)

service = client.connect(**opts.kwargs)

job = service.jobs.create(sys.argv[1])

while not job.is_ready:
    time.sleep(1)

try: 
    print "  STATUS    \tPCT\tRESULTS\t"
    print "==========  \t===\t======="
    while not job.is_done():
        print "%10s\t%3d\t%7d\t%7d" % (job['dispatchState'], int(float(job['doneProgress']) * 100), int(job['eventCount']), int(job['eventAvailableCount']))
        time.sleep(1)    
    print "Done (%s)" % job['resultCount']
    
        
except KeyboardInterrupt:
    print "Halted"

finally:
    job.cancel()