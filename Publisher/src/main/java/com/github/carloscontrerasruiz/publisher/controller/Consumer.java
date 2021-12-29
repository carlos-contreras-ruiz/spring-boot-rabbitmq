package com.github.carloscontrerasruiz.publisher.controller;

import com.github.carloscontrerasruiz.publisher.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = "Queue-1")
    public void getMessage(Person person){
    //public void getMessage(byte[] message){
        //System.out.println(message.toString());
        System.out.println(person.getName());
        System.out.println("===============================");
    }

    @RabbitListener(queues = "Mobile")
    public void getMessageMobile(Person person){
        //public void getMessage(byte[] message){
        //System.out.println(message.toString());
        System.out.println(person.getName());
        System.out.println("MOBILE===============================");
    }
}
