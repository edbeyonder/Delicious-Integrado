package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Gasto;
import com.Delicious.Deliciousweb.service.GastoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con los gastos del negocio.
 *
 * Permite listar, registrar, actualizar y eliminar
 * gastos mediante las vistas de Thymeleaf.
 */
@Controller
public class GastoController {

    private final GastoService gastoService;

    public GastoController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    /**
     * Muestra la lista de gastos registrados.
     *
     * @param model objeto utilizado para enviar la información a la vista.
     * @return vista "vergastos".
     */
    @GetMapping("/gastos")
    public String listarGastos(Model model) {

        List<Gasto> listaGastos = gastoService.listarTodos();

        model.addAttribute("gastos", listaGastos);

        return "vergastos";
    }

    /**
     * Abre el formulario para registrar un nuevo gasto.
     *
     * @param model objeto utilizado para enviar información a la vista.
     * @return vista "actualizargasto".
     */
    @GetMapping("/gastos/nuevo")
    public String nuevoGasto(Model model) {

        model.addAttribute("gasto", new Gasto());

        return "actualizargasto";
    }

    /**
     * Guarda un nuevo gasto o actualiza uno existente.
     *
     * @param gasto gasto recibido desde el formulario.
     * @return redirección a la lista de gastos.
     */
    @PostMapping("/gastos/guardar")
    public String guardarGasto(@Valid Gasto gasto,
                                BindingResult resultado,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (resultado.hasErrors()) {
            model.addAttribute("gasto", gasto);
            return "actualizargasto";
        }

        boolean esNuevo = (gasto.getIdGasto() == null);

        gastoService.guardar(gasto);

        if (esNuevo) {
            redirectAttributes.addFlashAttribute("mensaje",
                    "✅ Gasto registrado correctamente.");
        } else {
            redirectAttributes.addFlashAttribute("mensaje",
                    "✏️ Gasto actualizado correctamente.");
        }

        return "redirect:/gastos";
    }

  
    
}