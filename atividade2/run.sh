echo "\033[1;31m----< gerando .war >------\033[0m"
mvn clean package 
echo "\033[1;31m----< iniciando o docker-compose >------\033[0m"
sudo docker-compose -f ./docker/docker-compose.yaml up --build -d
