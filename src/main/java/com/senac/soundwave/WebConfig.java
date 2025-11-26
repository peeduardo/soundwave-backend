package com.senac.soundwave;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// --- IMPORT NECESSÁRIO ADICIONADO ---
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 1. O seu método de uploads (que já estava certo)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

    // ==========================================================
    // --- 2. MÉTODO DE CORS ADICIONADO (A CORREÇÃO) ---
    // ==========================================================
    /**
     * Adiciona a permissão de CORS para que o frontend (porta 5500)
     * possa fazer requisições para o backend (porta 8080).
     */
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permite para todos os endpoints

                // Endereço do seu Live Server
                .allowedOrigins("http://127.0.0.1:5500")

                // Métodos HTTP permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
               // .allowCredentials(true);
    }
}