
import splunklib.client as client
import splunklib.results as results
import utils, sys

opts = utils.parse(sys.argv[1:], {}, ".splunkrc")
if len(sys.argv) != 2:
    print "Syntax: page.py \"search query\""
    sys.exit()
    
try: 
    service = client.connect(**opts.kwargs)

    params = {"exec_mode": "blocking"}  #forces the call to create() to block until complete  
    job = #LAB: create the job using the query in sys.argv[1] and params
    
    total      = #LAB: get the total number of results in the job
    offset   = 0;                       
    pageSize = 10;                       
    
    print "Total results = %s" % str(total)

    while (offset < total):
        print "\n===== Page %s ===================================" % str(offset / pageSize + 1)
        pageParams = {#LAB: add name/value pairs for count=pageSize and offset=offset
        page = #LAB: get the job results, passing pageParams
        for result in results.ResultsReader(page):
            print "-----RESULT %s --------------------------------------" % #LAB: get the _serial field value from result 
            for key in result.keys():
                print "%s=%s|" % (key, result[key]),
            print 
        offset += pageSize
        raw_input('Enter to continue:')

except Exception, err:
    sys.stderr.write('Error: %s\n' % str(err))
