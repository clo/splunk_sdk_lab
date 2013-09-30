# An example of stream-based results handling for Splunk searches

import splunklib.client as client
import utils, sys
import shutil

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) < 2:
    print "Syntax: stream.py \"search query\" [rt]" 
    sys.exit()
try: 
    service = client.connect(**opts.kwargs)
    
    # In this example we are using Service.get(), to call the search/jobs/export
    # endpoint directly, because it returns a  binding.HttpLib object which behaves 
    # like a file-like stream. 
    
    if len(sys.argv) == 3 and sys.argv[2] == 'rt':
        response = service.get("search/jobs/export", search=sys.argv[1],
                               auto_cancel=1000, earliest_time="rt", 
                               latest_time="rt",output_mode="raw")
    else:
        response = service.get("search/jobs/export", search=sys.argv[1], 
                               auto_cancel=10, output_mode="raw")


    # response is a HttpLib instance. it is a dictionary which contains "body", a "file-like object supporting basic streaming.
    
    # the following copies one char at a time from the HttpLib.body in response to STDOUT. 
    # it is not very efficient and does not buffer, but it serves the purpose for our example.
             
    shutil.copyfileobj(response.body, sys.stdout, 1)
    
except KeyboardInterrupt:
    print "bye"

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
