
import splunklib.client as client
import utils
import sys
from pprint import pprint

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
try: 

    if len(sys.argv) == 1:
        print "syntax: users.py [list | username]"
        sys.exit()
        
    service = client.connect(**opts.kwargs)

    if sys.argv[1] == "list":
        print "List of all users:" 
        #LAB: print all user names to STDOUT
    else:
        u = #LAB: get the user named in sys.argv[1]
        #LAB: print all user attributes to STDOUT 
        
      
except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
