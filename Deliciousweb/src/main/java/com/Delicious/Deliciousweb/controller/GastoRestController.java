
package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Gasto;
import com.Delicious.Deliciousweb.service.GastoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/gasto")
public class GastoRestController {
    
     private final GastoService gastoService;

    public GastoRestController(GastoService gastoService) {
        this.gastoService = gastoService;
    }

    @GetMapping
    public ResponseEntity<List<Gasto>> listarGasto() {
        return ResponseEntity.ok(gastoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> buscarVenta(@PathVariable Integer id) {

        Gasto gasto = gastoService.buscarPorId(id);

        if (gasto== null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(gasto);
    }

@PostMapping
public ResponseEntity<Gasto> guardarGasto(@Valid @RequestBody Gasto gasto) {

    System.out.println("PRECIO RECIBIDO: " + gasto.getPrecio());
    System.out.println("DESCRIPCION RECIBIDA: " + gasto.getDescripcionGasto());

    gasto.setIdGasto(null);

    Gasto gastoGuardado = gastoService.guardar(gasto);

    System.out.println("DESCRIPCION GUARDADA: " + gastoGuardado.getDescripcionGasto());

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(gastoGuardado);
}

    
    
}
