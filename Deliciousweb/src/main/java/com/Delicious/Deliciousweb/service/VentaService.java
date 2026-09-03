package com.Delicious.Deliciousweb.service;

import com.Delicious.Deliciousweb.model.DetalleVenta;
import com.Delicious.Deliciousweb.model.Inventario;
import com.Delicious.Deliciousweb.model.Venta;
import com.Delicious.Deliciousweb.repository.VentaRepository;
import com.Delicious.Deliciousweb.repository.InventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Servicio encargado de gestionar la lógica de negocio
 * relacionada con las ventas y sus detalles.
 */
@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final InventarioRepository inventarioRepository;

    /**
     * Constructor que inyecta los repositorios necesarios.
     * @param ventaRepository repositorio de ventas.
     * @param inventarioRepository repositorio de inventario,
     *                              usado para descontar stock.
     */
    public VentaService(VentaRepository ventaRepository,
                         InventarioRepository inventarioRepository) {
        this.ventaRepository = ventaRepository;
        this.inventarioRepository = inventarioRepository;
    }

    /**
     * Registra una venta calculando subtotales y el total,
     * y descuenta el stock correspondiente en el inventario.
     *
     * @param venta venta a registrar, con su lista de detalles.
     * @return venta guardada con id y total calculados.
     */
    @Transactional
    public Venta guardarVenta(Venta venta) {

        float totalVenta = 0f;

        for (DetalleVenta detalle : venta.getDetalles()) {

            // Se recalcula el subtotal en el servidor por seguridad,
            // sin confiar en el valor que pueda enviar el frontend.
            float subtotal = detalle.getCantidad() * detalle.getPrecioUnitario();
            detalle.setSubtotal(subtotal);
            detalle.setVenta(venta);

            totalVenta += subtotal;

            // Se descuenta el stock del producto vendido.
            Inventario producto = inventarioRepository
                    .findById(detalle.getProducto().getIdProducto())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado: " + detalle.getProducto().getIdProducto()));

            int nuevoStock = producto.getCantidad() - detalle.getCantidad();

            if (nuevoStock < 0) {
                throw new RuntimeException(
                        "Stock insuficiente para el producto: " + producto.getNombreProducto());
            }

            producto.setCantidad(nuevoStock);
            inventarioRepository.save(producto);
        }

        venta.setTotal(totalVenta);
        venta.setFecha(new Date());

        return ventaRepository.save(venta);
    }

    /**
     * Lista todas las ventas registradas.
     * @return lista completa de ventas.
     */
    public List<Venta> listarTodas() {
        return ventaRepository.findAll();
    }

    /**
     * Busca una venta por su identificador.
     * @param id identificador de la venta.
     * @return venta encontrada o null si no existe.
     */
    public Venta buscarPorId(Integer id) {
        return ventaRepository.findById(id).orElse(null);
    }

    /**
     * Elimina una venta según su identificador.
     * @param id identificador de la venta.
     */
    public void eliminarPorId(Integer id) {
        ventaRepository.deleteById(id);
    }
}