package org.example;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.opencsv.CSVReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class CsvToKafkaApplication implements CommandLineRunner {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CsvToKafkaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        readAndSend("fake_users.csv");
    }

    public void readAndSend(String csvFilePath) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] headers = reader.readNext(); // Read header line
            String[] line;
            ObjectMapper objectMapper = new ObjectMapper();

            while ((line = reader.readNext()) != null) {
                User user = new User(line[0], line[1], new SimpleDateFormat("yyyy-MM-dd").parse(line[2]), Integer.parseInt(line[3]),
                                     line[4], line[5], Integer.parseInt(line[6]));

                String json = objectMapper.writeValueAsString(user);
//                System.out.println(json);
                kafkaTemplate.send("user-topic", json); // Send the record

                System.out.printf("Sent record for user: %s%n", user.getEmail());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}