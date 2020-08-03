package com.lightbend.helloworld;

import com.lightbend.lagom.javadsl.client.integration.LagomClientFactory;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        try (final LagomClientFactory clientFactory = LagomClientFactory.create("client", LagomClientFactory.class.getClassLoader())) {
            final HelloWorldService helloWorldService = clientFactory.createClient(HelloWorldService.class, URI.create("http://localhost:9000"));

            System.out.println(helloWorldService.helloWorld().invoke().toCompletableFuture().get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}