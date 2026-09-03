package com.Delicious.Deliciousweb.controller;

import com.Delicious.Deliciousweb.model.Usuario;
import com.Delicious.Deliciousweb.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador encargado de gestionar el inicio de sesión
 * de la aplicación.
 */
@Controller
public class LoginController {

    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor del controlador.
     *
     * @param usuarioRepository repositorio encargado de consultar
     *                           los usuarios almacenados en MySQL.
     */
    public LoginController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Muestra la página de inicio de sesión.
     *
     * @return vista "login".
     */
    @GetMapping("/")
    public String mostrarLogin() {
        return "login";
    }

    /**
     * Procesa las credenciales enviadas desde el formulario.
     *
     * Busca el correo en la base de datos y posteriormente
     * comprueba si la contraseña coincide.
     *
     * @param correo correo ingresado en el formulario.
     * @param password contraseña ingresada en el formulario.
     * @param model permite enviar mensajes a la vista.
     * @return dashboard si las credenciales son correctas,
     *         o login si son incorrectas.
     */
    @PostMapping("/login")
    public String iniciarSesion(
            @RequestParam("correo") String correo,
            @RequestParam("password") String password,
            Model model) {

        // Busca el usuario utilizando el correo ingresado.
        Usuario usuario = usuarioRepository.findByCorreo(correo).orElse(null);
      System.out.println("Correo recibido: " + correo);

if (usuario != null) {
    System.out.println("Correo BD: " + usuario.getCorreo());
    System.out.println("Contraseña BD: " + usuario.getContraseña());
} else {
    System.out.println("NO SE ENCONTRO EL USUARIO");
}
        // Comprueba que el usuario exista y que la contraseña coincida.
        if (usuario != null && usuario.getContraseña().equals(password)) {
            return "redirect:/dashboard";
        }

        // Si las credenciales son incorrectas, se muestra un mensaje.
        model.addAttribute("error", "Correo o contraseña incorrectos.");

        return "login";
    }
}