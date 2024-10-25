package com.ms.user.producers;

import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.queue.email}")
    private String routingKey;


    public void publishMessageEmail(UserModel userModel){
        EmailDto emailDto = new EmailDto();
        emailDto.setUserId(userModel.getId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("Cadastro realizado com sucesso!");
        emailDto.setText(userModel.getName()+", seja bem vindo(a)! \nAgradecemos cadastro.");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }




























}
