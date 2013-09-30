
import splunklib.client as client
import utils
import sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 3:
    print "Syntax: formats.py \"search query\" output_mode"
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)
    response = #LAB: call oneshot, pass sys.argv[1] and set the output mode to the value of sys.argv[2]
    print response

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
