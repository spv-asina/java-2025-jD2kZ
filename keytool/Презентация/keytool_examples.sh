# Проверка наличия keytool
keytool -help

# Создание нового хранилища и ключа
keytool -genkeypair -alias mykey -keyalg RSA -keystore mykeystore.jks -keysize 2048 -validity 365

# Просмотр содержимого keystore
keytool -list -v -keystore mykeystore.jks

# Экспорт публичного сертификата
keytool -export -alias mykey -file mycert.cer -keystore mykeystore.jks

# Импорт сертификата сервера
keytool -import -trustcacerts -alias server -file server.cer -keystore mykeystore.jks

# Удаление записи из keystore
keytool -delete -alias server -keystore mykeystore.jks

# Проверка сертификата
keytool -printcert -file mycert.cer

# Изменение пароля хранилища
keytool -storepasswd -keystore mykeystore.jks

# Подпись JAR-файла
jarsigner -keystore mykeystore.jks myapp.jar mykey

# Проверка подписи
jarsigner -verify -verbose -certs myapp.jar
