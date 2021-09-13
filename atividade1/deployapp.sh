#!/bin/bash

sudo docker stop atividade1 && sudo docker container rm atividade1
mvn clean package
sudo docker build -t paulo/atividade1:1 .
#sudo docker container run --name atividade1 -p 8081:8080 paulo/atividade1:1
#sudo docker stop banco-postgres && sudo docker container banco-postgres
#sudo docker run --name banco-postgres -p 5433:5432 paulo/postgres:1
sudo docker container run -p 8080:8080 --name atividade1 --link banco-postgres:host-banco paulo/atividade1:1
