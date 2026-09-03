package com.Delicious.Deliciousweb.repository;

import com.Delicious.Deliciousweb.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio encargado de gestionar el acceso a los datos
 * de la entidad Inventario.
 *
 * Hereda de JpaRepository para disponer de las operaciones
 * básicas de persistencia como guardar, actualizar, eliminar
 * y consultar productos de la base de datos.
 */
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

}