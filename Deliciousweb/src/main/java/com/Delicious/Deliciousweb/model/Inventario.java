package com.Delicious.Deliciousweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

/**
 * Clase que representa la entidad Inventario.
 * Se encarga de mapear la tabla "inventario" de la base de datos,
 * almacenando la información de cada producto registrado.
 */
@Entity
@Table(name = "inventario")
public class Inventario {

    /**
     * Identificador único del producto.
     * Se genera automáticamente mediante autoincremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    /**
     * Nombre del producto.
     */
 @NotBlank(message = "El nombre del producto es obligatorio")
@Size(max = 50, message = "El nombre no puede superar los 50 caracteres")
@Column(name = "nombre")
private String nombreProducto;

    /**
     * Cantidad disponible del producto.
     */
 @NotNull(message = "La cantidad es obligatoria")
@Positive(message = "La cantidad debe ser mayor que cero")
@Column(name = "cantidad")
private Integer cantidad;

    /**
     * Precio unitario del producto.
     */
 @NotNull(message = "el precio es obligatorio")
@Positive(message = "el precio debe ser mayor que cero")
    @Column(name = "precio")
    private Float precio;

    /**
     * Fecha en la que se registró el producto.
     */
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

    /**
     * Obtiene el identificador del producto.
     * @return id del producto.
     */
    public Integer getIdProducto() {
        return idProducto;
    }

    /**
     * Asigna el identificador del producto.
     * @param idProducto identificador del producto.
     */
    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * @return nombre del producto.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Asigna el nombre del producto.
     * @param nombreProducto nombre del producto.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene la cantidad disponible.
     * @return cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Asigna la cantidad disponible.
     * @param cantidad cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio del producto.
     * @return precio del producto.
     */
    public Float getPrecio() {
        return precio;
    }

    /**
     * Asigna el precio del producto.
     * @param precio precio del producto.
     */
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la fecha de registro.
     * @return fecha de registro.
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Asigna la fecha de registro.
     * @param fechaRegistro fecha del registro del producto.
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}