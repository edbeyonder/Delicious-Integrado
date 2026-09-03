import { useEffect, useState } from "react";
import { obtenerInventario } from "../services/inventarioService";
import Tablainventario from "../components/Tablainventario";
function InventarioPage() {
  const [inventario, setInventario] = useState([]);

  useEffect(() => {
    cargarInventario();
  }, []);

  const cargarInventario = async () => {
    try {
      const respuesta = await obtenerInventario();

      console.log("Datos recibidos:", respuesta.data);

      setInventario(respuesta.data);
    } catch (error) {
      console.error("Error al obtener el inventario:", error);
    }
  };

  return (
    <div className="container mt-4">
      <h2 className="text-center mb-4">Inventario</h2>
      <Tablainventario inventario={inventario} />
    </div>
  );
}

export default InventarioPage;
