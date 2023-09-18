package com.example.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class RabbitMqConfig {
    @Value("${rabbitMq.uri}")
    private String uri;


    @Bean
    public ConnectionFactory connectionFactory() throws URISyntaxException {
        URI uriConnect = new URI(uri);
        return new CachingConnectionFactory(uriConnect);


    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }

    @Bean("queue_test1")
    public Queue queue() {
        return new Queue("Queue_1");
    }

    @Bean
    public Exchange exchange() {
        Exchange exchange = new DirectExchange("exchange-1");
        return exchange;
    }


    @Bean
    public Binding binding(@Qualifier("queue_test1") Queue queue, Exchange exchange) {

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("routingKey")
                .noargs();
    }


}
