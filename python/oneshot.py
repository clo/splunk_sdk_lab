
import splunklib.client as client
import utils
import sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 2:
    print "Syntax: oneshot.py \"search query\""
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)
    response = #LAB: call oneshot, passing sys.argv[1]
    print response

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
