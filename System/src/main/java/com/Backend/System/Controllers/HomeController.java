package com.Backend.System.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {
        System.out.println("HomeController: Accediendo a /home");
        return "Bienvenido a la página de inicio!";
    }

    @GetMapping("/private")
    public String privatePage() {
        System.out.println("HomeController: Intento de acceso a /private");
        return "Esta es una página privada!";
    }
}
