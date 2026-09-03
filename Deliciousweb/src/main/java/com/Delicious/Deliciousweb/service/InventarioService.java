package com.Delicious.Deliciousweb.service;

import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio
 * relacionada con el inventario de productos.
 */
@Service
public class InventarioService {

    /**
     * Repositorio utilizado para acceder a la base de datos.
     */
    private final InventarioRepository inventarioRepository;

    /**
     * Constructor que inyecta el repositorio de inventario.
     * @param inventarioRepository repositorio de inventario.
     */
    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    /**
     * Guarda un producto en la base de datos.
     * @param producto producto a registrar.
     */
    public void guardarProducto(Inventario producto) {
        inventarioRepository.save(producto);
    }

    /**
     * Obtiene la lista de todos los productos registrados.
     * @return lista de productos.
     */
    public List<Inventario> listarProductos() {
        return inventarioRepository.findAll();
    }

    /**
     * Elimina un producto según su identificador.
     * @param id identificador del producto.
     */
    public void eliminarPorId(Integer id) {
        inventarioRepository.deleteById(id);
    }

    /**
     * Lista todos los productos del inventario.
     * @return lista completa de productos.
     */
    public List<Inventario> listarTodos() {
        return inventarioRepository.findAll();
    }

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     * Si el producto ya existe, conserva la fecha de registro.
     * Si es un producto nuevo, asigna la fecha actual.
     *
     * @param inventario producto a guardar.
     */
    public void guardar(Inventario inventario) {

        if (inventario.getIdProducto() != null) {

            Inventario existente = inventarioRepository
                    .findById(inventario.getIdProducto())
                    .orElse(null);

            if (existente != null) {
                inventario.setFechaRegistro(existente.getFechaRegistro());
            }

        } else {
            inventario.setFechaRegistro(new java.util.Date());
        }

        inventarioRepository.save(inventario);
    }

    /**
     * Busca un producto por su identificador.
     * @param id identificador del producto.
     * @return producto encontrado o null si no existe.
     */
    public Inventario buscarPorId(Integer id) {
        return inventarioRepository.findById(id).orElse(null);
    }
}