package com.example1.pub;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;
import java.util.Random;


@Component
public class PastaPublisher {

    private final StreamBridge streamBridge;
    private final List<String> pastaTypes = List.of("macaroni", "fettuccini", "farfalle", "fiori", "rotini", "penne");
    private final Random random = new Random();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PastaPublisher(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Scheduled(fixedRate = 3000)
    public void publishPastaMessage() {
        try {
            String pasta = pastaTypes.get(random.nextInt(pastaTypes.size()));

            // Construir el mensaje como JSON
            Map<String, String> payload = Map.of("pasta", pasta);
            String jsonPayload = objectMapper.writeValueAsString(payload);

            streamBridge.send("publishPastaMessages-out-0",
                MessageBuilder.withPayload(jsonPayload)
                              .setHeader("contentType", "application/json")  //  Importante en java cuando trabajamos esto, ya que no podemos enviar un objeto, ya que solace lo recibe y entrega en formato byte utf-8
                              .setHeader("pasta", pasta)
                              .build()
            );

            System.out.println("📤 Enviado JSON: " + jsonPayload);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}