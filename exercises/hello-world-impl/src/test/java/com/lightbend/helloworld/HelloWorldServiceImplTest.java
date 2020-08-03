package com.lightbend.helloworld;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HelloWorldServiceImplTest {
    private HelloWorldService helloWorldService;

    @Test
    public void helloWorld_shouldReturnTheStringHelloWorld() throws InterruptedException, ExecutionException, TimeoutException {
        withServer(defaultSetup(), server -> {
            // Setup
            helloWorldService = server.client(HelloWorldService.class);

            // Execute
            final String result = helloWorldService.helloWorld()
                    .invoke()
                    .toCompletableFuture()
                    .get(5, SECONDS);

            // Assert
            assertThat(result, is("Hello World!"));
        });
    }
}