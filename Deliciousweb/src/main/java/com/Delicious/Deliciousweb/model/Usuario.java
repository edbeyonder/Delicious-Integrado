
package com.Delicious.Deliciousweb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Modelo que representa un usuario del sistema.
 *
 * Esta clase corresponde a la tabla "usuarios" de MySQL
 * y contiene los datos necesarios para la autenticación.
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    // Identificador único del usuario.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    // Nombre del usuario.
    private String nombre;

    // Correo utilizado para iniciar sesión.
    private String correo;

    // Contraseña utilizada para iniciar sesión.
    private String contraseña;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Usuario() {
    }

    /**
     * Obtiene el identificador del usuario.
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * Asigna el identificador del usuario.
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * Obtiene el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el correo del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Asigna la contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Object getUsuario() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
