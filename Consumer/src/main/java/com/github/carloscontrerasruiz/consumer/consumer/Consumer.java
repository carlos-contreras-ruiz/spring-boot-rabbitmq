package com.github.carloscontrerasruiz.consumer.consumer;

import com.github.carloscontrerasruiz.publisher.model.Person;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Service
public class Consumer {

    @RabbitListener(queues = "Ac")
    public void getMessage(Person person){
    //public void getMessage(byte[] message){
        //System.out.println(message.toString());
        System.out.println(person.getName());
        System.out.println("ANOTHERSERVIE=============================");
    }

    @RabbitListener(queues = "Tv")
    public void getMessageTv(Person person){
        System.out.println(person.getName());
        System.out.println("TVTVTVTVT=============================");
    }

//    @RabbitListener(queues = "Tv")
//    public void getMessageMobHeaders(byte[] message) throws IOException, ClassNotFoundException {
//        ByteArrayInputStream bis = new ByteArrayInputStream(message);
//        ObjectInput in = new ObjectInputStream(bis);
//        Person person = (Person) in.readObject();
//
//        in.close();
//        bis.close();
//
//        System.out.println(person.getName());
//        System.out.println("TVTVTVTVT=============================");
//    }
}
