import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function RegistrarGastoPage() {
  const navigate = useNavigate();

  const [valor, setValor] = useState("");
  const [descripcionGasto, setDescripcionGasto] = useState("");

  const registrarGasto = async (e) => {
    e.preventDefault();

    try {
      await axios.post("https://delicious-backend-uwql.onrender.com/api/gasto", {
        precio: parseFloat(valor),
        descripcionGasto: descripcionGasto,
      });

      alert("✅ Gasto registrado correctamente");

      setValor("");
      setDescripcionGasto("");

      navigate("/gastos/nuevo");
    } catch (error) {
      console.error("Error al registrar el gasto:", error);

      alert("❌ No se pudo registrar el gasto");
    }
  };

  return (
    <main className="formulario-page">
      <button
        type="button"
        className="btn-regresar"
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>

      <div className="formulario-container">
        <h1>Registrar Gasto</h1>

        <form onSubmit={registrarGasto}>
          <div className="campo">
            <label>Valor</label>

            <input
              type="number"
              value={valor}
              onChange={(e) => setValor(e.target.value)}
              min="1"
              required
            />
          </div>

          <div className="campo">
            <label>Descripción</label>

            <input
              type="text"
              value={descripcionGasto}
              onChange={(e) => setDescripcionGasto(e.target.value)}
              maxLength="75"
              required
            />
          </div>

          <button type="submit">Registrar Gasto</button>
        </form>
      </div>
    </main>
  );
}

export default RegistrarGastoPage;
