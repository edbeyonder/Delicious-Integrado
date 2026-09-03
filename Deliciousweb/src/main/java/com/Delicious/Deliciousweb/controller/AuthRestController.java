
package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Usuario;
import com.Delicious.Deliciousweb.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST encargado de gestionar la autenticación
 * de los usuarios desde el Front-end desarrollado en React.
 */
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api")
public class AuthRestController {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor que inyecta el repositorio de usuarios.
     *
     * @param usuarioRepository repositorio utilizado para consultar
     *                          los usuarios almacenados en MySQL.
     */
    public AuthRestController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Verifica las credenciales enviadas desde React.
     *
     * @param datos objeto que contiene el correo y la contraseña.
     * @return 200 OK si las credenciales son correctas,
     *         o 401 Unauthorized si son incorrectas.
     */
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario datos) {

        // Busca en MySQL un usuario que tenga el correo recibido.
        Usuario usuario = usuarioRepository
                .findByCorreo(datos.getCorreo())
                .orElse(null);

        // Comprueba que el usuario exista y que la contraseña coincida.
        if (usuario != null &&
                usuario.getContraseña().equals(datos.getContraseña())) {

            return ResponseEntity.ok("Autenticación satisfactoria");
        }

        // Si las credenciales no coinciden, devuelve error 401.
        return ResponseEntity
                .status(401)
                .body("Correo o contraseña incorrectos");
    }
}
