#!/bin/bash
set -x
service mysql start
SPRING_DB="springdb"
MYSQL_PASSWORD=
mysqladmin -u root password $MYSQL_PASSWORD 
mysql -uroot -p$MYSQL_PASSWORD -e "CREATE DATABASE springdb; GRANT ALL PRIVILEGES ON springdb.* TO 'springdb@'localhost'; FLUSH PRIVILEGES;"
mkdir /log
sleep 5s 
java  -jar Timesheet-DOCKER-IMAGE.war