#!/bin/bash

# Subir o container do Kafka
echo "Subindo o container Kafka..."
cd infra-kafka || exit
docker compose up --build -d

# Voltar para o diretório principal
cd ..

# Função para subir cada aplicação
start_application() {
  app_dir=$1
  echo "Subindo a aplicação $app_dir..."
  cd "$app_dir" || exit
  ./gradlew clean build
  ./gradlew bootRun &
  cd ..
}

# Subir as aplicações Java
start_application "api-clientes"
start_application "api-produtos"
start_application "api-notifica-consumer"
start_application "api-notifica-producer"
start_application "api-mensagem"

echo "Todas as aplicações estão rodando."
