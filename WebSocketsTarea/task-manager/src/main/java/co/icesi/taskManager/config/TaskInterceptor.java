package co.icesi.taskManager.config;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class TaskInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                    ServerHttpResponse response,
                                    WebSocketHandler wsHandler,
                                    Map<String, Object> attributes) {
           
       String uri = request.getURI().toString();
        String token = null;
        if (uri.contains("token=")) {
            token = uri.substring(uri.indexOf("token=") + 6);
        }

        System.out.println("Token recibido: " + token);
        attributes.put("token", token);

        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
                System.out.println("Finaliza handshake");
    }
}
