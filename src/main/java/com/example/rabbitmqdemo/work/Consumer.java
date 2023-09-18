package com.example.rabbitmqdemo.work;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Consumer {


    @RabbitListener(queues = "#{queue_test1}")
    public void processQueueNotify(String data) {

        log.info("receiver message from queue_1 with data: {}", data);


    }


}
