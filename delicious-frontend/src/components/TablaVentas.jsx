import { useState } from "react";
import axios from "axios";

function TablaVentas({ ventas, onVentaEliminada }) {
  const [ventaExpandida, setVentaExpandida] = useState(null);

  const toggleDetalle = (idVenta) => {
    setVentaExpandida(ventaExpandida === idVenta ? null : idVenta);
  };

  const eliminarVenta = async (id) => {
    const confirmar = window.confirm("¿Está seguro de eliminar esta venta?");

    if (!confirmar) {
      return;
    }

    try {
      await axios.delete(`https://delicious-backend-uwql.onrender.com/api/venta/${id}`);

      alert("🗑️ Venta eliminada correctamente");

      // En vez de recargar toda la página, avisamos al padre
      // para que vuelva a pedir la lista actualizada.
      if (onVentaEliminada) {
        onVentaEliminada();
      } else {
        window.location.reload();
      }
    } catch (error) {
      console.error("Error al eliminar la venta:", error);

      alert("❌ No se pudo eliminar la venta");
    }
  };

  return (
    <table className="table table-striped table-hover table-bordered">
      <thead>
        <tr className="table-info">
          <th>ID Venta</th>
          <th>Fecha</th>
          <th>Total</th>
          <th>Acciones</th>
        </tr>
      </thead>

      <tbody>
        {ventas.map((venta) => (
          <>
            <tr key={venta.idVenta}>
              <td>{venta.idVenta}</td>

              <td>{new Date(venta.fecha).toLocaleDateString("es-CO")}</td>

              <td>${venta.total}</td>

              <td>
                <button
                  className="btn btn-info btn-sm me-2"
                  onClick={() => toggleDetalle(venta.idVenta)}
                >
                  {ventaExpandida === venta.idVenta ? "Ocultar" : "Ver detalle"}
                </button>

                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => eliminarVenta(venta.idVenta)}
                >
                  Eliminar
                </button>
              </td>
            </tr>

            {ventaExpandida === venta.idVenta && (
              <tr>
                <td colSpan="4">
                  <table className="table table-sm table-bordered mb-0">
                    <thead>
                      <tr>
                        <th>Producto</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                        <th>Subtotal</th>
                      </tr>
                    </thead>
                    <tbody>
                      {venta.detalles.map((detalle) => (
                        <tr key={detalle.idDetalle}>
                          <td>{detalle.producto.nombreProducto}</td>
                          <td>{detalle.cantidad}</td>
                          <td>${detalle.precioUnitario}</td>
                          <td>${detalle.subtotal}</td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </td>
              </tr>
            )}
          </>
        ))}
      </tbody>
    </table>
  );
}

export default TablaVentas;
