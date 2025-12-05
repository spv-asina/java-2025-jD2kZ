import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8443;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: java ClientSimple <сообщение>");
            return;
        }

        String message = args[0];

        try {
            // Загружаем ключи клиента
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("client.jks"), "clientpass".toCharArray());

            // Загружаем доверенные сертификаты (серверы)
            KeyStore trustStore = KeyStore.getInstance("JKS");
            trustStore.load(new FileInputStream("client_truststore.jks"), "clienttrust".toCharArray());

            // Настраиваем менеджеры
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, "clientpass".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(trustStore);

            // Создаем SSL контекст
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            // Подключаемся к серверу
            SSLSocket socket = (SSLSocket) sslContext.getSocketFactory().createSocket(HOST, PORT);

            System.out.println("Подключаюсь к серверу...");

            // Отправляем сообщение
            PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            out.println(message);
            System.out.println("Отправлено: " + message);

            // Получаем ответ
            String response = in.readLine();
            System.out.println("Ответ сервера: " + response);

            socket.close();

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}