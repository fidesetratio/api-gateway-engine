Install disini
sudo vi /etc/systemd/system/web-application.service

jangan lupa untuk chown 
sudo chown patartimotius:patartimotius apigateway-0.0.1-SNAPSHOT.jar
sudo systemctl daemon-reload
sudo systemctl start web-application
sudo systemctl stop web-application