package com.Delicious.Deliciousweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Clase que representa la entidad DetalleVenta.
 * Mapea la tabla "detalle_venta", almacenando cada producto
 * vendido dentro de una venta, con su cantidad y subtotal.
 */
@Entity
@Table(name = "detalle_venta")
public class DetalleVenta {

    /**
     * Identificador único del detalle.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    /**
     * Venta a la que pertenece este detalle.
     */
    @ManyToOne
    @JoinColumn(name = "id_venta", nullable = false)
    @JsonBackReference
    private Venta venta;

    /**
     * Producto del inventario vendido en este detalle.
     */
    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Inventario producto;

    /**
     * Cantidad vendida del producto.
     */
    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor que cero")
    @Column(name = "cantidad")
    private Integer cantidad;

    /**
     * Precio unitario del producto al momento de la venta.
     */
    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor que cero")
    @Column(name = "precio_unitario")
    private Float precioUnitario;

    /**
     * Subtotal de este detalle (cantidad * precio_unitario).
     */
    @Column(name = "subtotal")
    private Float subtotal;

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Inventario getProducto() {
        return producto;
    }

    public void setProducto(Inventario producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }
}