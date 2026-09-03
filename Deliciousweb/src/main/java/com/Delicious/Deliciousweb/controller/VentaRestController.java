package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Venta;
import com.Delicious.Deliciousweb.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones
 * de venta mediante servicios web.
 *
 * Expone los endpoints necesarios para registrar y consultar
 * ventas junto con sus detalles.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/venta")
public class VentaRestController {

    private final VentaService ventaService;

    /**
     * Constructor que inyecta el servicio de ventas.
     * @param ventaService servicio encargado de la lógica de negocio.
     */
    public VentaRestController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    /**
     * Consulta todas las ventas registradas.
     * @return lista completa de ventas con estado 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas() {
        return ResponseEntity.ok(ventaService.listarTodas());
    }

    /**
     * Consulta una venta específica utilizando su identificador.
     * @param id identificador de la venta.
     * @return venta encontrada con estado 200 OK o 404 Not Found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarVenta(@PathVariable Integer id) {

        Venta venta = ventaService.buscarPorId(id);

        if (venta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(venta);
    }

    /**
     * Registra una nueva venta junto con sus detalles.
     * Descuenta automáticamente el stock del inventario.
     *
     * @param venta venta a registrar, incluyendo la lista de detalles.
     * @return venta registrada con estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<Venta> guardarVenta(@Valid @RequestBody Venta venta) {

        venta.setIdVenta(null);

        Venta ventaGuardada = ventaService.guardarVenta(venta);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ventaGuardada);
    }

    /**
     * Elimina una venta utilizando su identificador.
     * @param id identificador de la venta.
     * @return estado 204 No Content si fue eliminada correctamente
     *         o 404 Not Found si la venta no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Integer id) {

        Venta existente = ventaService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        ventaService.eliminarPorId(id);

        return ResponseEntity.noContent().build();
    }
}