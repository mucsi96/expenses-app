#!/bin/sh

curl -i \
  -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@card-transactions.csv" \
  http://localhost:8080/upload


curl -i \
  -X POST \
  -H "Content-Type: multipart/form-data" \
  -F "file=@personal-transactions.csv" \
  http://localhost:8080/upload