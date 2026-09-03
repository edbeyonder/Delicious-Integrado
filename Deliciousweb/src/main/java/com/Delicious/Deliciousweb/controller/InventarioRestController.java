package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST encargado de gestionar las operaciones
 * del inventario mediante servicios web.
 *
 * Expone los endpoints necesarios para realizar las operaciones
 * CRUD sobre los productos almacenados en la base de datos.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/inventario")
public class InventarioRestController {

    private final InventarioService inventarioService;

    /**
     * Constructor que inyecta el servicio de inventario.
     *
     * @param inventarioService servicio encargado de gestionar
     *                          la lógica de negocio del inventario.
     */
    public InventarioRestController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    /**
     * Endpoint de prueba para comprobar que la API REST
     * se encuentra funcionando correctamente.
     *
     * @return mensaje indicando que la API está funcionando.
     */
    @GetMapping("/prueba")
    public ResponseEntity<String> prueba() {
        return ResponseEntity.ok("API funcionando");
    }

    /**
     * Consulta todos los productos registrados en el inventario.
     *
     * @return lista completa de productos con estado HTTP 200 OK.
     */
    @GetMapping
    public ResponseEntity<List<Inventario>> listarProductos() {

        List<Inventario> productos = inventarioService.listarTodos();

        return ResponseEntity.ok(productos);
    }

    /**
     * Consulta un producto específico utilizando su identificador.
     *
     * @param id identificador del producto que se desea consultar.
     * @return producto encontrado con estado 200 OK o 404 Not Found
     *         si el producto no existe.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarProducto(@PathVariable Integer id) {

        Inventario producto = inventarioService.buscarPorId(id);

        if (producto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    /**
     * Registra un nuevo producto en el inventario.
     *
     * @param inventario información del producto que será registrado.
     * @return producto registrado con estado HTTP 201 Created.
     */
    @PostMapping
    public ResponseEntity<Inventario> guardarProducto(
            @Valid @RequestBody Inventario inventario) {

        // Se establece el ID en null para garantizar que
        // MySQL genere automáticamente un nuevo identificador.
        inventario.setIdProducto(null);

        inventarioService.guardar(inventario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventario);
    }

    /**
     * Actualiza la información de un producto existente.
     *
     * @param id identificador del producto que se desea actualizar.
     * @param inventario nuevos datos del producto.
     * @return producto actualizado con estado 200 OK o 404 Not Found
     *         si el producto no existe.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarProducto(
            @PathVariable Integer id,
            @Valid @RequestBody Inventario inventario) {

        // Se busca primero el producto para comprobar que exista.
        Inventario existente = inventarioService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        // Se actualizan los datos permitidos del producto.
        existente.setNombreProducto(inventario.getNombreProducto());
        existente.setCantidad(inventario.getCantidad());
        existente.setPrecio(inventario.getPrecio());

        // Se conserva el ID y la fecha de registro existentes.
        inventarioService.guardar(existente);

        return ResponseEntity.ok(existente);
    }

    /**
     * Elimina un producto utilizando su identificador.
     *
     * @param id identificador del producto que se desea eliminar.
     * @return estado 204 No Content si fue eliminado correctamente
     *         o 404 Not Found si el producto no existe.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {

        // Se comprueba que el producto exista antes de eliminarlo.
        Inventario existente = inventarioService.buscarPorId(id);

        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        
        inventarioService.eliminarPorId(id);

        return ResponseEntity.noContent().build();
    }
}