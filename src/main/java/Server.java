
import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class Server extends Observable {
    private HttpServer server;

    public Server() throws Exception {
        server = HttpServer.create(new InetSocketAddress(InetAddress.getByName("0.0.0.0"), 8001), 0);
        server.createContext("/", new MyHttpHandler());
        server.setExecutor(Executors.newFixedThreadPool(10));
        server.start();
    }

    private void sendDefaultResponse(HttpExchange httpExchange, String response) throws IOException {
        //Headers responseHeaders = httpExchange.getResponseHeaders();
        //responseHeaders.set("Access-Control-Allow-Origin", "https://localhost:63342");

        byte[] bs = response.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, bs.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(bs);
        os.flush();
        os.close();
    }


    protected class MyHttpHandler implements HttpHandler {

        public void handle(HttpExchange httpExchange) throws IOException {
            try {
                if ("GET".equals(httpExchange.getRequestMethod())) {
                    handleGetRequest(httpExchange);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String handleGetRequest(HttpExchange httpExchange) throws IOException {
            String htmlContent = "<p>Just checking heroku</p>";
            sendDefaultResponse(httpExchange, htmlContent);
            return "";
        }

    }
}
