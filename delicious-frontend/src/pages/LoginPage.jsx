import { useState } from "react";
import { useNavigate } from "react-router-dom";

function LoginPage() {
  const navigate = useNavigate();

  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const iniciarSesion = async (e) => {
    e.preventDefault();

    setError("");

    // Verifica que los campos estén completos.
    if (usuario === "" || password === "") {
      setError("Complete todos los campos");
      return;
    }

    try {
      // Envía las credenciales al Back-end de Spring Boot.
      const respuesta = await fetch("https://delicious-backend-uwql.onrender.com/api/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          correo: usuario,
          contraseña: password,
        }),
      });

      // Si Spring Boot responde correctamente, permite entrar.
      if (respuesta.ok) {
        sessionStorage.setItem("sesionIniciada", "true");
        navigate("/dashboard");
      } else {
        // Si las credenciales son incorrectas, muestra el mensaje.
        const mensaje = await respuesta.text();
        setError(mensaje);
      }
    } catch (error) {
      // Error cuando React no logra comunicarse con Spring Boot.
      setError("No se pudo conectar con el servidor");
    }
  };

  return (
    <div className="login-page">
      <div className="login-box">
        <h1>DELICIOUS</h1>

        <p>Iniciar sesión</p>

        <form onSubmit={iniciarSesion}>
          <div className="campo">
            <label htmlFor="correo">Correo electrónico</label>

            <input
              value={usuario}
              onChange={(e) => setUsuario(e.target.value)}
              required
              type="email"
              id="correo"
              placeholder="name@example.com"
            />
          </div>

          <div className="campo">
            <label htmlFor="password">Contraseña</label>

            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
              id="password"
              placeholder="Contraseña"
            />
          </div>

          {error && <p className="mensaje-error">⚠️ {error}</p>}

          <button type="submit">Ingresar</button>
        </form>
      </div>
    </div>
  );
}

export default LoginPage;
