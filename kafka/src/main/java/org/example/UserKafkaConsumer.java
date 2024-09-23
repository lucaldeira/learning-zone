package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaConsumer {

    @Autowired
    private UserRepository userRepository; // Inject the UserRepository

    @KafkaListener(topics = "user-topic", groupId = "group_id")
    public void listen(String message) {
        // Process the consumed message
        System.out.println("Received message: " + message);
        // Add additional processing logic here, if needed

        // Convert JSON message to User object (assuming you are using Jackson)
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(message, User.class);

            // Save the user to the database
            userRepository.save(user);
            System.out.printf("Saved user to database: %s%n", user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any parsing or saving errors as needed
        }
    }
}
