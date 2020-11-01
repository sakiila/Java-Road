package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.ArrayList;

public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";
    
    public static void main(String[] args) {
        String proxyServer = System.getProperty("proxyServer","http://localhost:8808");
        String proxyPort = System.getProperty("proxyPort","8888");
        
          //  http://localhost:8888/api/hello  ==> gateway API
          //  http://localhost:8088/api/hello  ==> backend service
        ArrayList<String> proxyServers = new ArrayList<>();
        proxyServers.add(proxyServer);
        proxyServers.add("http://localhost:8809");
        proxyServers.add("http://localhost:8810");
        int port = Integer.parseInt(proxyPort);

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServers);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + proxyServer);
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
