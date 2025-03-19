package io.berndruecker.ticketbooking;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeDeployment;  // Correct annotation

@SpringBootApplication(scanBasePackages = {"io.berndruecker.ticketbooking", "io.berndruecker.ticketbooking.rest"})  // Explicitly include subpackages
@EnableZeebeClient
@ZeebeDeployment(resources = "classpath:ticket-booking.bpmn")  // Fixed annotation
public class TicketBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Queue paymentResponseQueue() {
        return new Queue("paymentResponse", true);
    }
}