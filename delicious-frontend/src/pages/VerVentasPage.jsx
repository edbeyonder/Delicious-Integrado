import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import TablaVentas from "../components/TablaVentas";

function VerVentasPage() {
  const [ventas, setVentas] = useState([]);
  const navigate = useNavigate();

  const cargarVentas = () => {
    axios
      .get("http://localhost:8080/api/venta")
      .then((response) => {
        setVentas(response.data);
      })
      .catch((error) => {
        console.error("Error al cargar las ventas:", error);
      });
  };

  useEffect(() => {
    cargarVentas();
  }, []);

  return (
    <div className="container mt-4">
      <button
        type="button"
        className="btn-regresar"
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>

      <h2>Ventas registradas</h2>

      <TablaVentas ventas={ventas} onVentaEliminada={cargarVentas} />
    </div>
  );
}

export default VerVentasPage;
