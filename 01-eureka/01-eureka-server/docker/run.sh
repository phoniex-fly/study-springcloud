cd ..&&docker build ./eureka-server -t eureka-server:1.0
docker run --name eureka-server -p 8761:8761 -d eureka-server:1.0
