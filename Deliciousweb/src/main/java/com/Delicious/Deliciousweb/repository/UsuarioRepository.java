package com.Delicious.Deliciousweb.repository;

import com.Delicious.Deliciousweb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio encargado de realizar las operaciones
 * de acceso a datos de los usuarios.
 *
 * Spring Data JPA genera automáticamente la implementación
 * de esta interfaz.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su correo.
     *
     * @param correo correo utilizado para iniciar sesión.
     * @return el usuario encontrado, si existe.
     */
    Optional<Usuario> findByCorreo(String correo);
}