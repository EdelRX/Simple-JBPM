# Simple-JBPM

This is a demo project to test Transaction and recover features of jBPM


In order to test it we created a simple flow with a Custom Work Item Handler executed by a Spring Bean.
![Workflow of the demo](test.png?raw=true)

The goal is to avoid transaction issues on high volume while keeping the recover feature.



In order to reproduce the error:

1) Start your preferred DB (we used docker to avoid installations).

Postgres:
```
docker run -d --name postgres -v my_dbdata:/var/lib/postgresql/data -p 54320:5432 postgres:9.6.2-alpine
```
MySQL:
```
docker run --name mysql -e MYSQL_USER=jbpm -e MYSQL_PASSWORD=jbpm -e MYSQL_DATABASE=jbpm -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 mysql:5.7
```

And then run the code:
```
git clone https://github.com/EdelRX/Simple-JBPM.git
cd Simple-JBPM
cd business-application-service
chmod 755 launch.sh (only needed for unix environments , use launch.bat for windows)
./launch.sh (or launch.bat for windows) clean install
```


The errors are:

Postgre:
org.postgresql.util.PSQLException: ERROR: could not serialize access due to read/write dependencies among transactions
  Detail: Reason code: Canceled on identification as a pivot, during write.
  Hint: The transaction might succeed if retried.
  
MySQL:
