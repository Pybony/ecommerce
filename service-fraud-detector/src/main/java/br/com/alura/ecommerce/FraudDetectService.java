package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FraudDetectService {

    public static void main(String[] args) throws IOException {
        var fraudService = new FraudDetectService();
        try(var service = new KafkaService<>(FraudDetectService.class.getSimpleName(),
               "ECOMMERCE_NEW_ORDER",
                fraudService::parse,
                Order.class,
                Map.of())) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Order> record) {
        System.out.println("-----------------------------------");
        System.out.println("Processing ne order, checking for fraud");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }
}
