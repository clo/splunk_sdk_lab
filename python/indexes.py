
import splunklib.client as client
import utils
import sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
try: 
    if len(sys.argv) == 1:
        print "syntax: indexes.py [list | show indexname | clean indexname | delete indexname | create indexname]"
        sys.exit()
    service = client.connect(**opts.kwargs)

    if sys.argv[1] == "list":
        #LAB: print all index names to STDOUT
    elif sys.argv[1] == "show":
        index = #LAB: get the index named in sys.argv[2]
        #LAB: print all index attributes to STDOUT
    elif sys.argv[1] == "create":
        index = #LAB: create the index named in sys.argv[2]
        print "  created index %s" % index.name
    elif sys.argv[1] == "clean":
        index = #LAB: get the index named in sys.argv[2]
        #LAB: clean the index 
        print "  cleaned index %s" % index.name
    elif sys.argv[1] == "delete":
        index = #LAB: get the index named in sys.argv[2]
        #LAB: delete the index
        print "  index %s deleted" % sys.argv[2]
    else:
        raise Exception("unknown command \"%s\"" % sys.argv[1])

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
