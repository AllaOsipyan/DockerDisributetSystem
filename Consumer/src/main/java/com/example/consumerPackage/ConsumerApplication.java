package com.example.consumerPackage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
@Configuration
@EnableCaching
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("links");
    }

    @Bean
    public Queue link1() {
        return new Queue("link1");
    }

    @Bean
    public Binding binding1(FanoutExchange fanout,
                            Queue link1) {
        return BindingBuilder.bind(link1).to(fanout);
    }

    @Bean
    public Consumer receiver() {
        return new Consumer(redisTemplate());
    }

    @Bean
    public RedisTemplate<String, Integer> redisTemplate() {
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(System.getenv("REDIS_CONNECTION"));
        System.out.println(System.getenv("REDIS_CONNECTION"));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
}