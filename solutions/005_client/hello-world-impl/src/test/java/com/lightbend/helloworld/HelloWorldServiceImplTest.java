package com.lightbend.helloworld;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.lightbend.lagom.javadsl.testkit.ServiceTest.defaultSetup;
import static com.lightbend.lagom.javadsl.testkit.ServiceTest.withServer;
import static org.junit.Assert.assertEquals;

public class HelloWorldServiceImplTest {

    @Test
    public void helloWorld_shouldReturnTheExpectedResult() {
        withServer(defaultSetup(), server -> {
            HelloWorldService helloWorldService = server.client(HelloWorldService.class);

            String result = helloWorldService
                    .helloWorld()
                    .invoke()
                    .toCompletableFuture()
                    .get(5, TimeUnit.SECONDS);

            assertEquals("Hello World", result);
        });
    }
}
