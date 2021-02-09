package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.ArrayList;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {

        ArrayList<String> proxyServers = new ArrayList<>();
        proxyServers.add(System.getProperty("proxyServer1", "http://localhost:8808"));
//        proxyServers.add(System.getProperty("proxyServer2", "http://localhost:8809"));
//        proxyServers.add(System.getProperty("proxyServer3", "http://localhost:8810"));

        String proxyPort = System.getProperty("proxyPort", "8888");
        int port = Integer.parseInt(proxyPort);

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServers);

        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
