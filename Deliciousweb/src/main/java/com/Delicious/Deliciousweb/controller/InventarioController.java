package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.service.InventarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * relacionadas con el inventario de productos.
 *
 * Permite listar, registrar, actualizar y eliminar
 * productos mediante las vistas de Thymeleaf.
 */
@Controller
public class InventarioController {

    /**
     * Servicio que contiene la lógica de negocio del inventario.
     */
    private final InventarioService inventarioService;

    /**
     * Constructor que inyecta el servicio de inventario.
     *
     * @param inventarioService servicio de inventario.
     */
    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Muestra la lista de productos registrados.
     *
     * @param model objeto utilizado para enviar la información a la vista.
     * @return vista "verinventario".
     */
    @GetMapping("/inventario")
    public String listarInventario(Model model) {

        List<Inventario> listaInventario = inventarioService.listarTodos();

        model.addAttribute("inventarios", listaInventario);

        return "verinventario";
    }

    /**
     * Abre el formulario para registrar un nuevo producto.
     *
     * @param model objeto utilizado para enviar información a la vista.
     * @return vista "actualizarinventario".
     */
    @GetMapping("/inventario/nuevo")
    public String nuevoProducto(Model model) {

        model.addAttribute("inventario", new Inventario());

        return "actualizarinventario";
    }

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param inventario producto recibido desde el formulario.
     * @return redirección a la lista de inventario.
     */
@PostMapping("/inventario/guardar")
public String guardarProducto(@Valid Inventario inventario,
                              BindingResult resultado,
                              Model model,
                              RedirectAttributes redirectAttributes) {

    if (resultado.hasErrors()) {
        model.addAttribute("inventario", inventario);
        return "actualizarinventario";
    }

    boolean esNuevo = (inventario.getIdProducto() == null);

    inventarioService.guardar(inventario);

    if (esNuevo) {
        redirectAttributes.addFlashAttribute("mensaje",
                "✅ Producto registrado correctamente.");
    } else {
        redirectAttributes.addFlashAttribute("mensaje",
                "✏️ Producto actualizado correctamente.");
    }

    return "redirect:/inventario";
}
    /**
     * Busca un producto por su identificador y carga sus datos
     * en el formulario para permitir su actualización.
     *
     * @param id identificador del producto.
     * @param model objeto utilizado para enviar información a la vista.
     * @return vista "actualizarinventario".
     */
    @GetMapping("/inventario/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model) {

        Inventario inventario = inventarioService.buscarPorId(id);

        model.addAttribute("inventario", inventario);

        return "actualizarinventario";
    }

    /**
     * Elimina un producto del inventario según su identificador.
     *
     * @param id identificador del producto.
     * @return redirección a la lista de inventario.
     */
  @GetMapping("/inventario/eliminar/{id}")
public String eliminarProducto(@PathVariable Integer id,
                               RedirectAttributes redirectAttributes) {

        inventarioService.eliminarPorId(id);
 redirectAttributes.addFlashAttribute("mensaje",
            "🗑️ Producto eliminado correctamente.");

    return "redirect:/inventario";
}
}