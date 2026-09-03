import { useState } from "react";
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";

function FproductoPage() {
  const location = useLocation();
  const navigate = useNavigate();

  const productoEditar = location.state;

  const [nombreProducto, setNombreProducto] = useState(
    productoEditar ? productoEditar.nombreProducto : "",
  );

  const [cantidad, setCantidad] = useState(
    productoEditar ? productoEditar.cantidad : "",
  );

  const [precio, setPrecio] = useState(
    productoEditar ? productoEditar.precio : "",
  );

  const guardarProducto = async (e) => {
    e.preventDefault();

    try {
      const producto = {
        nombreProducto: nombreProducto,
        cantidad: Number(cantidad),
        precio: Number(precio),
      };

      if (productoEditar) {
        await axios.put(
          `http://localhost:8080/api/inventario/${productoEditar.idProducto}`,
          producto,
        );

        alert("✏️ Producto actualizado correctamente");
      } else {
        await axios.post("http://localhost:8080/api/inventario", producto);

        alert("✅ Producto registrado correctamente");
      }

      navigate("/inventario");
    } catch (error) {
      console.error("Error al guardar el producto:", error);

      alert("❌ No se pudo guardar el producto");
    }
  };

  return (
    <>
      <button
        className="btn btn-outline-info "
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>
      <div className="pagina-formulario">
        <div className="formulario-producto">
          <h1>{productoEditar ? "Actualizar Producto" : "Agregar Producto"}</h1>

          <form onSubmit={guardarProducto}>
            <div className="campo">
              <label>Nombre del Producto</label>

              <input
                type="text"
                value={nombreProducto}
                onChange={(e) => setNombreProducto(e.target.value)}
                maxLength="50"
                required
              />
            </div>

            <div className="campo">
              <label>Cantidad</label>

              <input
                type="number"
                value={cantidad}
                onChange={(e) => setCantidad(e.target.value)}
                min="1"
                required
              />
            </div>

            <div className="campo">
              <label>Precio</label>

              <input
                type="number"
                value={precio}
                onChange={(e) => setPrecio(e.target.value)}
                min="1"
                required
              />
            </div>

            <button type="submit">
              {productoEditar ? "Actualizar Producto" : "Guardar Producto"}
            </button>
          </form>
        </div>
      </div>
    </>
  );
}

export default FproductoPage;
