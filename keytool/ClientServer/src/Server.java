import javax.net.ssl.*;
import java.io.*;
import java.security.KeyStore;

public class Server {
    private static final int PORT = 8443;

    public static void main(String[] args) {
        try {
            // Загружаем ключи сервера
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(new FileInputStream("server.jks"), "serverpass".toCharArray());

            // Загружаем доверенные сертификаты (клиенты)
            KeyStore trustStore = KeyStore.getInstance("JKS");
            trustStore.load(new FileInputStream("server_truststore.jks"), "servertrust".toCharArray());

            // Настраиваем менеджеры
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            kmf.init(keyStore, "serverpass".toCharArray());

            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            tmf.init(trustStore);

            // Создаем SSL контекст
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

            // Создаем серверный сокет
            SSLServerSocket serverSocket = (SSLServerSocket)
                    sslContext.getServerSocketFactory().createServerSocket(PORT);
            serverSocket.setNeedClientAuth(true); // Требуем клиентский сертификат

            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                try (SSLSocket clientSocket = (SSLSocket) serverSocket.accept()) {
                    // Получаем информацию о клиенте
                    SSLSession session = clientSocket.getSession();
                    String clientName = session.getPeerPrincipal().getName();
                    System.out.println("Подключился клиент: " + clientName);

                    // Читаем запрос
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream()), true);

                    String request = in.readLine();
                    System.out.println("Запрос: " + request);

                    // Отправляем ответ
                    String response = "Сервер принял ваш запрос: '" + request + "'";
                    out.println(response);
                    System.out.println("Ответ отправлен");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}