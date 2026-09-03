import { useNavigate } from "react-router-dom";
import axios from "axios";

function Tablainventario({ inventario }) {
  const navigate = useNavigate();

  const actualizarProducto = (producto) => {
    navigate("/inventario/actualizar", {
      state: producto,
    });
  };

  const eliminarProducto = async (id) => {
    const confirmar = window.confirm("¿Está seguro de eliminar este producto?");

    if (!confirmar) {
      return;
    }

    try {
      await axios.delete(`http://localhost:8080/api/inventario/${id}`);

      alert("🗑️ Producto eliminado correctamente");

      window.location.reload();
    } catch (error) {
      console.error("Error al eliminar el producto:", error);

      alert("❌ No se pudo eliminar el producto");
    }
  };

  return (
    <>
      <button
        type="button"
        className="btn-regresar"
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>
      <table class="table table-striped table-hover  table-borderedtable table-bordered">
        <thead>
          <tr class="table-info">
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Fecha Registro</th>
            <th>Acciones</th>
          </tr>
        </thead>

        <tbody>
          {inventario.map((producto) => (
            <tr key={producto.idProducto}>
              <td>{producto.nombreProducto}</td>

              <td>{producto.cantidad}</td>

              <td>${producto.precio}</td>

              <td>
                {new Date(producto.fechaRegistro).toLocaleDateString("es-CO")}
              </td>

              <td>
                <button
                  className="btn btn-warning btn-sm me-2"
                  onClick={() => actualizarProducto(producto)}
                >
                  Actualizar
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => eliminarProducto(producto.idProducto)}
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
}

export default Tablainventario;
