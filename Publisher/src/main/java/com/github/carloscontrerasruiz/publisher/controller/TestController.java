package com.github.carloscontrerasruiz.publisher.controller;

import com.github.carloscontrerasruiz.publisher.model.Person;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testApi(@PathVariable("name") String name) {
        Person person = Person.builder().id(1L).name(name).build();
        rabbitTemplate.convertAndSend("Queue-1", person);
        rabbitTemplate.convertAndSend("Direct-Exchange", "mobile", person);
        rabbitTemplate.convertAndSend("Fanout-Exchange", "", person);
        rabbitTemplate.convertAndSend("Topic-Exchange", "tv.mobile.ac", person);
        return "Success";
    }

    @GetMapping("/test1/{name}")
    public String testApiHeaders(@PathVariable("name") String name) throws IOException {
        Person person = Person.builder().id(1L).name(name).build();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = new ObjectOutputStream(bos);
        out.writeObject(person);
        out.flush();
        out.close();
        byte[] byteMessage = bos.toByteArray();
        bos.close();

        //The headers must be configured in Rabbitmq console
        Message message = MessageBuilder
                .withBody(byteMessage)
                .setHeader("item1", "mobile")
                .build();
        rabbitTemplate.send("Headers-Exchange","",message);
        return "Success";
    }
}
