package com.slyone;

import java.io.*;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args)
            throws java.io.IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        String message2 = "Huba, huba!";
        channel.basicPublish("", QUEUE_NAME, null, message2.getBytes());
        System.out.println(" [x] Sent '" + message2 + "'");

        channel.close();
        connection.close();
    }
}
