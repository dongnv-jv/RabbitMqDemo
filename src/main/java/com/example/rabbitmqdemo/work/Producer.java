package com.example.rabbitmqdemo.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class Producer {


    @Autowired
    public RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 1000)
    public void sendData(){
        rabbitTemplate.convertAndSend("exchange-1","routingKey","Gui thu");

    }



}
