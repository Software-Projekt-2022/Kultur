package com.api.kultur.Event;

import com.api.kultur.model.User;
import com.api.kultur.service.UserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Listener {

    private final UserService userService;

    @RabbitListener(queues = Config.QUEUE)
    public void listener(Message message) {
        User user = new User();
        Gson g = new Gson();
        RabbitMqMessage mqMessage = g.fromJson(message.getMessage(), RabbitMqMessage.class);

        user.setId(mqMessage.account_id);
        userService.create(user);
    }

    private class RabbitMqMessage {
        private int account_id;
        private String email;
        private String first_name;
        private String last_name;
        private String date_of_birth;
        private String street;
        private String street2;
        private String house_number;
        private String registration_date;
    }

}
