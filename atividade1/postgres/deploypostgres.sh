#!/bin/bash

sudo docker stop banco-postgres && sudo docker container rm banco-postgres
sudo docker build -t paulo/postgres:1 .
sudo docker run --name banco-postgres -p 5433:5432 paulo/postgres:1
