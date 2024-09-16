#!/bin/bash

# Função para parar cada aplicação
stop_application() {
  app_dir=$1
  echo "Parando a aplicação $app_dir..."
  cd "$app_dir" || exit
  ./gradlew --stop
  cd ..
}

# Parar as aplicações Java
stop_application "api-clientes"
stop_application "api-produtos"
stop_application "api-notifica-consumer"
stop_application "api-notifica-producer"
stop_application "api-mensagem"

# Parar o container do Kafka
echo "Parando o container Kafka..."
cd infra-kafka || exit
docker compose down

echo "Todas as aplicações e containers foram parados."

# Função para matar processos em uma porta específica
kill_port() {
  port=$1
  pid=$(lsof -t -i:$port)

  if [ -n "$pid" ]; then
    echo "Matando processo na porta $port (PID: $pid)"
    kill -9 $pid
  else
    echo "Nenhum processo rodando na porta $port"
  fi
}

# Matar processos nas portas de 8081 a 8084
for port in {8081..8084}; do
  kill_port $port
done

echo "Processos nas portas 8081 a 8084 foram finalizados."
