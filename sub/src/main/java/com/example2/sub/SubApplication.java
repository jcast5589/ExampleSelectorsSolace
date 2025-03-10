package com.example2.sub;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.function.Function;
@SpringBootApplication
public class SubApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubApplication.class, args);
	}

	@Bean
    public Function<byte[], String> consumePastaMessages() {
        return messageBytes -> {
            try {
                // 🔥 Convertimos byte[] a Map<String, Object>
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> message = objectMapper.readValue(messageBytes, Map.class);

                System.out.println("🍽️ Pasta recibida: " + message);
                return "Pasta procesada exitosamente";

            } catch (Exception e) {
                e.printStackTrace();
                return "Error procesando el mensaje";
            }
        };
    }
}
