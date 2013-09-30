
import splunklib.client as client
import utils, sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 5:
    print "Syntax: mkuser.py username password role \"real name\""
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)
    params = #LAB: create a name/value pair for the real name with value in sys.argv[4]

    user = #LAB: create a user with name in sys.argv[1], password in sys.argv[2], role in sys.argv[3], and pass params
    print "User %s created." % user.name

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
