package com.verter.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String queue;
    private final String exchange;
    private final String routingKey;
    private final String username;
    private final String password;
    private final String host;

    public RabbitMQConfig(@Value("${spring.rabbitmq.queue}") String queue,
                          @Value("${spring.rabbitmq.exchange}") String exchange,
                          @Value("${spring.rabbitmq.routingKey}") String routingKey,
                          @Value("${spring.rabbitmq.username}") String username,
                          @Value("${spring.rabbitmq.password}") String password,
                          @Value("${spring.rabbitmq.host}") String host) {
        this.queue = queue;
        this.exchange = exchange;
        this.routingKey = routingKey;
        this.username = username;
        this.password = password;
        this.host = host;
    }

    @Bean
    Queue queue() {
        return new Queue(queue, true);
    }

    @Bean
    Exchange myExchange() {
        return ExchangeBuilder.directExchange(exchange).durable(true).build();
    }

    @Bean
    Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(myExchange())
                .with(routingKey)
                .noargs();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(host);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setPassword(password);
        return cachingConnectionFactory;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
