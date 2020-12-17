package com.example.demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Send {
    public String connectionString = System.getenv("RABBIT_CONNECTION");
    private ConnectionFactory factory = new ConnectionFactory();
    public void sendMessage(Long linkId, String url) throws IOException {
        factory.setHost(connectionString);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
             System.out.println(linkId);
             JSONObject message = new JSONObject();
             message.put("linkId", linkId);
             message.put("url", url);
             channel.exchangeDeclare("links", "fanout", true);
             channel.basicPublish( "links", "", null, message.toJSONString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
