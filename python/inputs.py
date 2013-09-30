
import splunklib.client as client
import utils
import sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
try: 
    if len(sys.argv) == 1:
        print "syntax: inputs.py [list | show inputname | delete inputname | create inputname index]"
        sys.exit()
    service = client.connect(**opts.kwargs)

    if sys.argv[1] == "list":
        #LAB: print all input names to STDOUT
    elif sys.argv[1] == "show":
        # need a work-around since service.indexes[path] does not work
        for ip in service.inputs:
            if ip.name == sys.argv[2]:
                #LAB: print all input attributes to STDOUT 
                break
    elif sys.argv[1] == "create":
        ip = #LAB: create the input with path from sys.argv[2], kind=monitor, and set the index to sys.argv[3]
        print "  created input %s" % ip.name
    elif sys.argv[1] == "delete":
        raise Exception("delete is not currently supported")
        # Indexes.remove() does not work for same reason as given in "list"
    else:
        raise Exception("unknown command \"%s\"" % sys.argv[1])

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
