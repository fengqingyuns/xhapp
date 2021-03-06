package com.example.demo.util;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqConnectionUtil {

    public static Connection getConnection() throws IOException, TimeoutException {
        
        ConnectionFactory factory = new ConnectionFactory();
        
        factory.setHost("39.98.226.58");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("admin");
        factory.setPassword("admin");
        return factory.newConnection();
    }
}
