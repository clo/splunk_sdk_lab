import splunklib.client as client
import sys

uid  = sys.argv[1];
pwd  = sys.argv[2];
host = sys.argv[3];
port = sys.argv[4];

if len(sys.argv) != 5:
	print "Syntax: login.py username password hostname port"
	sys.exit()
	
try: 
	service = client.connect(
    	username=uid,
    	password=pwd,   	
    	host=host,
    	port=port) 
	context=service.login()
	
	print 'Authenticated, token = [%s]' % service.token	

except Exception, err:
	sys.stderr.write('Error: %s\n' % str(err))

