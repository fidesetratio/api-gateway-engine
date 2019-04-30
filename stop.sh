#!/bin/bash
if [ -f /home/patartimotius/applicationtesting/apigateway/pid.file ]; then
	kill $(cat /home/patartimotius/applicationtesting/apigateway/pid.file)
	echo 'kill them...'
fi
