#!/bin/bash
if [ -f /home/patartimotius/applicationtesting/apigateway/pid.file ]; then
	
 if ps -p $(cat /home/patartimotius/applicationtesting/apigateway/pid.file) > /dev/null;then
 	kill $(cat /home/patartimotius/applicationtesting/apigateway/pid.file) > /dev/null
	echo 'kill them...'
 fi
 
fi
