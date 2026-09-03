package com.Delicious.Deliciousweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase que representa la entidad Venta.
 * Se encarga de mapear la tabla "venta" de la base de datos,
 * almacenando la información de cada venta registrada junto
 * con sus detalles asociados.
 */
@Entity
@Table(name = "venta")
public class Venta {

    /**
     * Identificador único de la venta.
     * Se genera automáticamente mediante autoincremento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venta")
    private Integer idVenta;

    /**
     * Fecha en la que se registró la venta.
     */
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha;

    /**
     * Monto total de la venta, calculado a partir
     * de la suma de los subtotales de sus detalles.
     */
    @Column(name = "total")
    private Float total;

    /**
     * Lista de detalles (productos) que componen la venta.
     * Se guardan/eliminan en cascada junto con la venta.
     */
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> detalles;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVenta> detalles) {
        this.detalles = detalles;
    }
}