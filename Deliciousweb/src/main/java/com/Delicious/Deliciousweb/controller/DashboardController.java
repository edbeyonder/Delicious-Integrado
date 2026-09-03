package com.Delicious.Deliciousweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador encargado de mostrar el panel principal
 * de la aplicación Delicious.
 */
@Controller
public class DashboardController {

    /**
     * Muestra la vista principal del sistema (Dashboard).
     *
     * @return vista "dashboard".
     */
    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "dashboard";
    }
}