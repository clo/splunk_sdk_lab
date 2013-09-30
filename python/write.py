
import splunklib.client as client
import utils
import sys
import datetime

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 3:
    print "Syntax: write.py index \"data\""
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)
 
    index = #LAB: get the index named in sys.argv[1]
    #LAB: submit the data in sys.argv[2] to the index, adding a date/time stamp to the beginning of the data, and set host, source and sourcetype to static values as desired
    
except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
