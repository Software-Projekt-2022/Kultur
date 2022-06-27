package com.api.kultur.Event;

import com.google.gson.JsonObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class MessageBus {
    public static final String MICROSERVICE_NAME = "microservice.kultur";
    public static final String MICROSERVICE_PREFIX = "KUL-";
    public static final String MICROSERVICE_EXCHANGE = "publish_event.kultur";
    public static final String MICROSERVICE_QUEUE = "microservice.kultur";
    private static final String HOST = "localhost";
    public static final ConnectionFactory connectionFactory = new ConnectionFactory();
    protected static Connection connection;
    protected static Channel channel;

    public static void main() throws IOException, TimeoutException {
        createConnection();
        listenForEvents();
    }

    private static void createConnection()
    {
        //Auf localhost verbinden
        connectionFactory.setHost(HOST);
        try
        {
            //Verbindung und Channel erstellen
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
        }
        catch (IOException | TimeoutException e)
        {
            throw new RuntimeException(e);
        }
    }

    private static void sendEvent(AdminMessageBroadcastEvent event)
    {
        try
        {
            String msg = event.toString();

            //Verwendet die Exchange des Microservices und event_type als Rounting-Key
            //Sendet das Event
            channel.basicPublish(MICROSERVICE_EXCHANGE, event.event_type, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.printf("Sent event %s: \"%s\"\n", event.event_type, msg);
        }
        catch(IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Wird immer ausgeführt, wenn ein Event empfangen wurde.
     */
    private static void onEventReceived(String event)
    {
        System.out.printf("Event received: \"%s\"\n", event);
    }

    /**
     * Diese Funktion blockiert nicht und lässt das Programm im Hintergrund auf Events warten.
     */
    private static void listenForEvents() throws IOException
    {
        //Wichtig: Parameter "autoAck" auf "true" für automatisches Acknowledgement
        channel.basicConsume(MICROSERVICE_QUEUE, true, (consumerTag, delivery) ->
        {
            //Wird immer ausgeführt, wenn ein Event empfangen wird
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            onEventReceived(message);
        }, consumerTag -> { });

        System.out.println("Now listening for events. Press CTRL + C to cancel.");
    }

    /**
     * Funktion zum Erstellen eines beispielhaften Events.
     */
    private static AdminMessageBroadcastEvent makeEvent()
    {
        AdminMessageBroadcastEvent event = new AdminMessageBroadcastEvent();
        return event;
    }
}
