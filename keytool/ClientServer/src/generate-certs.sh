#!/bin/bash

# Удаляем старые файлы если есть
rm -f *.jks *.crt *.csr *.cer

echo "=== Генерация корневого CA ==="
# Создаем корневой CA
keytool -genkeypair -alias rootCA -keyalg RSA -keysize 2048 \
  -keystore rootCA.jks -validity 3650 \
  -storepass password123 -keypass password123 \
  -dname "CN=MyRootCA, OU=Security, O=MyCompany, C=RU" \
  -ext bc:c

echo "=== Экспорт корневого сертификата ==="
# Экспортируем корневой сертификат в файл
keytool -exportcert -alias rootCA -keystore rootCA.jks \
  -storepass password123 -rfc -file rootCA.crt

echo "=== Генерация сертификата сервера ==="
# Создаем хранилище сервера с ключом
keytool -genkeypair -alias server -keyalg RSA -keysize 2048 \
  -keystore server.jks -validity 365 \
  -storepass serverpass -keypass serverpass \
  -dname "CN=localhost, OU=Server, O=MyCompany, C=RU"

# Создаем запрос на подпись для сервера
keytool -certreq -alias server -keystore server.jks \
  -storepass serverpass -file server.csr

# Подписываем сертификат сервера корневым CA
keytool -gencert -alias rootCA -keystore rootCA.jks \
  -storepass password123 -infile server.csr \
  -outfile server.crt -validity 365

# Создаем новое хранилище сервера и импортируем цепочку
echo "=== Настройка хранилища сервера ==="
keytool -importcert -alias rootCA -file rootCA.crt \
  -keystore server_truststore.jks -storepass servertrust -noprompt

keytool -importcert -alias rootCA -file rootCA.crt \
  -keystore server.jks -storepass serverpass -noprompt

keytool -importcert -alias server -file server.crt \
  -keystore server.jks -storepass serverpass

echo "=== Генерация сертификата клиента ==="
# Создаем хранилище клиента с ключом
keytool -genkeypair -alias client -keyalg RSA -keysize 2048 \
  -keystore client.jks -validity 365 \
  -storepass clientpass -keypass clientpass \
  -dname "CN=Client1, OU=Client, O=MyCompany, C=RU"

# Создаем запрос на подпись для клиента
keytool -certreq -alias client -keystore client.jks \
  -storepass clientpass -file client.csr

# Подписываем сертификат клиента корневым CA
keytool -gencert -alias rootCA -keystore rootCA.jks \
  -storepass password123 -infile client.csr \
  -outfile client.crt -validity 365

echo "=== Настройка хранилища клиента ==="
# Создаем truststore для клиента с корневым сертификатом
keytool -importcert -alias rootCA -file rootCA.crt \
  -keystore client_truststore.jks -storepass clienttrust -noprompt

# Импортируем корневой сертификат в keystore клиента
keytool -importcert -alias rootCA -file rootCA.crt \
  -keystore client.jks -storepass clientpass -noprompt

# Импортируем подписанный сертификат клиента
keytool -importcert -alias client -file client.crt \
  -keystore client.jks -storepass clientpass

echo "=== Настройка сервера для проверки клиентов ==="
# Импортируем сертификат клиента в truststore сервера
keytool -exportcert -alias client -keystore client.jks \
  -storepass clientpass -rfc -file client_pub.cer

keytool -importcert -alias client -file client_pub.cer \
  -keystore server_truststore.jks -storepass servertrust -noprompt

echo "=== Настройка клиента для проверки сервера ==="
# Импортируем сертификат сервера в truststore клиента
keytool -exportcert -alias server -keystore server.jks \
  -storepass serverpass -rfc -file server_pub.cer

keytool -importcert -alias server -file server_pub.cer \
  -keystore client_truststore.jks -storepass clienttrust -noprompt

echo "=========================================="
echo "Генерация завершена! Созданы файлы:"
echo "1. server.jks - keystore сервера (пароль: serverpass)"
echo "2. server_truststore.jks - truststore сервера (пароль: servertrust)"
echo "3. client.jks - keystore клиента (пароль: clientpass)"
echo "4. client_truststore.jks - truststore клиента (пароль: clienttrust)"
echo "5. rootCA.jks - корневой CA (пароль: password123)"
echo "=========================================="