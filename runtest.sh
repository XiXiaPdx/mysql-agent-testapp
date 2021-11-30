#!/bin/bash

for i in {1..20}
do
  echo "$i -- execute sql to database"
  curl -X POST --location "http://localhost:8080/demo/add"
  sleep 3
done

