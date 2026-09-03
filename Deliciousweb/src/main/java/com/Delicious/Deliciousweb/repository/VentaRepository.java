package com.Delicious.Deliciousweb.repository;

import com.Delicious.Deliciousweb.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio encargado de gestionar el acceso a los datos
 * de la entidad Venta.
 */
public interface VentaRepository extends JpaRepository<Venta, Integer> {

}