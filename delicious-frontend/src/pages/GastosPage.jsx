import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function GastosPage() {
  const navigate = useNavigate();
  const [gastos, setGastos] = useState([]);

  const cargarGastos = () => {
    axios
      .get("http://localhost:8080/api/gasto")
      .then((response) => {
        setGastos(response.data);
      })
      .catch((error) => {
        console.error("Error al cargar los gastos:", error);
      });
  };

  useEffect(() => {
    cargarGastos();
  }, []);

  return (
    <main className="gastos-page">
      <button
        type="button"
        className="btn-regresar"
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>

      <div className="gastos-contenido">
        <h1>Gastos</h1>

        <table className="table table-striped table-hover table-bordered">
          <thead>
            <tr className="table-info">
              <th>Valor</th>
              <th>Fecha</th>
              <th>Descripción</th>
            </tr>
          </thead>

          <tbody>
            {gastos.map((gasto) => (
              <tr key={gasto.idGasto}>
                <td>${gasto.precio.toLocaleString("es-CO")}</td>

                <td>{new Date(gasto.fecha).toLocaleDateString("es-CO")}</td>

                <td>{gasto.descripcionGasto}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </main>
  );
}

export default GastosPage;
