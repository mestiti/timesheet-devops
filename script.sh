#!/bin/bash
set -x
/usr/bin/mysqld_safe &
sleep 5s 
MYSQL_PASSWORD=123
mysql -uroot -p$MYSQL_PASSWORD -e "CREATE USER 'ons'@'localhost' IDENTIFIED BY '123';GRANT ALL PRIVILEGES ON *.* TO 'ons'@'localhost' WITH GRANT OPTION;"
mysql -uroot -p$MYSQL_PASSWORD -e "CREATE USER 'ons'@'%' IDENTIFIED BY '123';GRANT ALL PRIVILEGES ON *.* TO 'ons'@'%' WITH GRANT OPTION;"
killall mysqld
mkdir /log
sleep 5s 
sleep 10s
java  -jar Timesheet-DOCKER-IMAGE.war