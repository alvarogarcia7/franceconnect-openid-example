package com.example.spike.franceconnect.openidconnect.example;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.glassfish.grizzly.http.server.HttpServer;

public class FranceConnectApp {

    private static final URI BASE_URI = URI.create("http://localhost:8080/");

    public static void main(String[] args) {
        try {
            final ResourceConfig resourceConfig = new ResourceConfig(HelloWorldResource.class);
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, resourceConfig);

            System.out.println(String.format("Application started.\nTry out %s\nHit enter to stop it...",
                    BASE_URI));
            System.in.read();
            server.shutdownNow();
        } catch (IOException ex) {
            Logger.getLogger(FranceConnectApp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
