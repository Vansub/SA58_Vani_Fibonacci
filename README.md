# oracle challenge(Please check Master)
Coin calculator application . Separate directories for frontend and backend and docker-compose.yml at the root to orchestrate the container.
Prerequisites
Docker installed
Docker compose installed
Set up
clone the repository
Build and run the docker container - docker-compose up --build
access the application 
frontend : http://localhost:3000
Backend: http://localhost:8080
docker-compose.yml should be in the same folder as frontend and backend
frontend: build context:'./frontend'
portmapping : 3000:3000
backend: Springboot :'./backend'
port mapping : 8080:8080
stop the application using docker-compose down
if any troubleshooting: checl logs using docker-compose logs

