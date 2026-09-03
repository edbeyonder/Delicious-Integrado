
package com.Delicious.Deliciousweb.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

 @Entity
@Table(name="gastos")
public class Gasto {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id_gasto")
   private Integer idGasto;
 
 
  @NotNull(message = "el precio es obligatorio")
@Positive(message = "el precio debe ser mayor que cero")
    @Column(name = "precio")
    private Float precio;
  
  
   @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fecha;
  
  @Size(max = 75, message = "La descripcion no puede superar los 50 caracteres")
@Column(name = "descripcion")
private String descripcionGasto;

  public Gasto() {
}

public Gasto(Integer idGasto, Float precio, Date fecha, String DescripcionGasto) {
    this.idGasto = idGasto;
    this.precio = precio;
    this.fecha = fecha;
    this.descripcionGasto = DescripcionGasto;
}

    public Integer getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Integer idGasto) {
        this.idGasto = idGasto;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionGasto() {
        return descripcionGasto;
    }

    public void setDescripcionGasto(String DescripcionGasto) {
        this.descripcionGasto = DescripcionGasto;
    }
  
}
