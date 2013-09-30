#!/usr/bin/perl

# Example using Splunk REST API from Perl to run a search. For learning purposes only--no
# exception handling, use at own risk, some settling may occur, it's perl, your milage may vary...

# SYNTAX: search "search string" [atom|csv|json|json_cols|json_rows|raw|xml]

# EXAMPLE: search "index=_audit action=add" csv

use LWP; # LibWWW-Perl--simple HTTP/S processing

my $agent = LWP::UserAgent->new; # create a new useragent--a simple browser object

# Disable SSL host verification, because Splunk SSL default certs are not validated
$agent->ssl_opts("verify_hostname" => 0); 

# Authenticate and retrieve a token, then set the token in the agent's list of default headers
my $response = $agent->post('https://localhost:8089/services/auth/login',  ['username' => 'admin','password' => 'changeme'],);
(my $token) = $response->content =~ /<sessionKey>(.*?)</;
$agent->default_header("Authorization" => "Splunk $token");

# Run the search using the 1st input arg as the search string and get back a search job ID (sid)
$response = $agent->post("https://localhost:9501/services/search/jobs", [ 'search' => 'search ' . shift,],);
(my $sid) = $response->content =~ /<sid>(.*?)</;

# Check the status of the search job each second until done
CHECK: {
	$response = $agent->get("https://localhost:9501/services/search/jobs/$sid");
	last CHECK if $response->content =~ /isDone">1/;	
	sleep 1;
	redo CHECK;
}

# Dump the results to STDOUT, using the 2nd input arg as the format specifier
$response = $agent->get("https://localhost:9501/services/search/jobs/$sid/results?output_mode=" . shift);
print "\n" . $response->content;