package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Venta;
import com.Delicious.Deliciousweb.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Delicious.Deliciousweb.model.DetalleVenta;
import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.service.InventarioService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones
 * de visualización de ventas mediante las vistas de Thymeleaf.
 */
@Controller
public class VentaController {

    private final VentaService ventaService;
    private final InventarioService inventarioService;

    public VentaController(VentaService ventaService, InventarioService inventarioService) {
        this.ventaService = ventaService;
        this.inventarioService = inventarioService;
    }

    @GetMapping("/ventas")
    public String listarVentas(Model model) {
        List<Venta> listaVentas = ventaService.listarTodas();
        model.addAttribute("ventas", listaVentas);
        return "verventas";
    }

    @GetMapping("/ventas/detalle/{id}")
    public String verDetalleVenta(@PathVariable Integer id, Model model) {
        Venta venta = ventaService.buscarPorId(id);
        model.addAttribute("venta", venta);
        return "detalleventa";
    }

    @GetMapping("/ventas/nueva")
    public String nuevaVenta(Model model) {
        List<Inventario> productos = inventarioService.listarTodos();
        model.addAttribute("productos", productos);
        return "registrarventa";
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@RequestParam("idProducto") List<Integer> idsProducto,
                                @RequestParam("cantidad") List<Integer> cantidades,
                                @RequestParam("precio") List<Float> precios,
                                RedirectAttributes redirectAttributes) {

        Venta venta = new Venta();
        List<DetalleVenta> detalles = new java.util.ArrayList<>();

        for (int i = 0; i < idsProducto.size(); i++) {

            Inventario producto = new Inventario();
            producto.setIdProducto(idsProducto.get(i));

            DetalleVenta detalle = new DetalleVenta();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidades.get(i));
            detalle.setPrecioUnitario(precios.get(i));

            detalles.add(detalle);
        }

        venta.setDetalles(detalles);

        ventaService.guardarVenta(venta);

        redirectAttributes.addFlashAttribute("mensaje",
                "✅ Venta registrada correctamente.");

        return "redirect:/ventas";
    }
}