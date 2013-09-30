
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
        for u in service.users:
            print '  %s' % u.name
    else:
        u = service.users[sys.argv[1]]
        for key in u.state.keys():
            print '  $s: ' % key

            if isinstance(dict, u[key]:
            	print
            	for sub in u[key]:
              	  print "    %s: %s" % (sub, u[key][sub])
            else: 
            	print u[key]
        
except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
