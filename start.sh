#!/bin/bash
nohup java -jar /home/patartimotius/applicationtesting/apigateway/apigateway-0.0.1-SNAPSHOT.jar > /home/patartimotius/applicationtesting/apigateway/log.txt 2>&1 &
echo $! > /home/patartimotius/applicationtesting/apigateway/pid.file