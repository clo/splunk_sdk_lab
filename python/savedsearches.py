
import splunklib.client as client
import utils
import sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
try: 

    if len(sys.argv) == 1:
        print "syntax: savedsearches.py [list owner app | \"search name\"]"
        sys.exit()
        
    if sys.argv[1] == "list":
        params = {"host"        : opts.kwargs["host"],
                  "port"        : opts.kwargs["port"],
                  "username"    : opts.kwargs["username"],
                  "password"    : opts.kwargs["password"],
                  #LAB: set owner to sys.argv[2] and app to sys.argv[3]
                  }
        service = client.connect(**params)
        print 'saved searches in %s/%s namespace:' % (service.namespace.owner, service.namespace.app)
        for s in #LAB: current namespace saved search collection
            print '  %s (%s/%s)' % (s.name, s.access.owner, s.access.app)
 
    else:
        params = {"host"        : opts.kwargs["host"],
                  "port"        : opts.kwargs["port"],
                  "username"    : opts.kwargs["username"],
                  "password"    : opts.kwargs["password"],
                  #LAB: set owner and app to wildcards
                  }
        service = client.connect(**params)
        search = #LAB: get the saved search named in sys.argv[1]
        #LAB: print all attbibutes of search to STDOUT
      
except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
