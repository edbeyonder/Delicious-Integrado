import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function RegistrarVentaPage() {
  const [inventario, setInventario] = useState([]);
  const [detalles, setDetalles] = useState([]);
  const [idProductoSeleccionado, setIdProductoSeleccionado] = useState("");
  const [cantidad, setCantidad] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    axios
      .get("https://delicious-backend-uwql.onrender.com/api/inventario")
      .then((response) => {
        setInventario(response.data);
      })
      .catch((error) => {
        console.error("Error al cargar el inventario:", error);
      });
  }, []);

  const agregarDetalle = () => {
    if (!idProductoSeleccionado || !cantidad || cantidad <= 0) {
      alert("⚠️ Selecciona un producto y una cantidad válida");
      return;
    }

    const producto = inventario.find(
      (p) => p.idProducto === parseInt(idProductoSeleccionado),
    );

    if (!producto) {
      return;
    }

    if (parseInt(cantidad) > producto.cantidad) {
      alert(`⚠️ Stock insuficiente. Disponible: ${producto.cantidad}`);
      return;
    }

    const nuevoDetalle = {
      producto: { idProducto: producto.idProducto },
      nombreProducto: producto.nombreProducto,
      cantidad: parseInt(cantidad),
      precioUnitario: producto.precio,
      subtotal: parseInt(cantidad) * producto.precio,
    };

    setDetalles([...detalles, nuevoDetalle]);
    setIdProductoSeleccionado("");
    setCantidad("");
  };

  const eliminarDetalle = (index) => {
    const nuevosDetalles = [...detalles];
    nuevosDetalles.splice(index, 1);
    setDetalles(nuevosDetalles);
  };

  const totalVenta = detalles.reduce((acc, d) => acc + d.subtotal, 0);

  const registrarVenta = async () => {
    if (detalles.length === 0) {
      alert("⚠️ Agrega al menos un producto a la venta");
      return;
    }

    try {
      await axios.post("https://delicious-backend-uwql.onrender.com/api/venta", {
        detalles: detalles.map((d) => ({
          producto: { idProducto: d.producto.idProducto },
          cantidad: d.cantidad,
          precioUnitario: d.precioUnitario,
        })),
      });

      alert("✅ Venta registrada correctamente");

      navigate("/registrarventa");
    } catch (error) {
      console.error("Error al registrar la venta:", error);

      const mensaje =
        error.response?.data?.message || "No se pudo registrar la venta";

      alert(`❌ ${mensaje}`);
    }
  };

  return (
    <div className="container mt-4">
      <button
        type="button"
        className="btn-regresar"
        onClick={() => navigate("/dashboard")}
      >
        ← Regresar
      </button>

      <h2>Registrar venta</h2>

      <div className="row mb-3">
        <div className="col-md-5">
          <select
            className="form-select"
            value={idProductoSeleccionado}
            onChange={(e) => setIdProductoSeleccionado(e.target.value)}
          >
            <option value="">Selecciona un producto</option>
            {inventario.map((producto) => (
              <option key={producto.idProducto} value={producto.idProducto}>
                {producto.nombreProducto} (stock: {producto.cantidad}) - $
                {producto.precio}
              </option>
            ))}
          </select>
        </div>

        <div className="col-md-3">
          <input
            type="number"
            className="form-control"
            placeholder="Cantidad"
            min="1"
            value={cantidad}
            onChange={(e) => setCantidad(e.target.value)}
          />
        </div>

        <div className="col-md-3">
          <button className="btn btn-primary" onClick={agregarDetalle}>
            Agregar producto
          </button>
        </div>
      </div>

      <table className="table table-bordered">
        <thead>
          <tr className="table-info">
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio unitario</th>
            <th>Subtotal</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          {detalles.map((detalle, index) => (
            <tr key={index}>
              <td>{detalle.nombreProducto}</td>
              <td>{detalle.cantidad}</td>
              <td>${detalle.precioUnitario}</td>
              <td>${detalle.subtotal}</td>
              <td>
                <button
                  className="btn btn-danger btn-sm"
                  onClick={() => eliminarDetalle(index)}
                >
                  Quitar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      <h4>Total: ${totalVenta}</h4>

      <button className="btn btn-success mt-2" onClick={registrarVenta}>
        Registrar venta
      </button>
    </div>
  );
}

export default RegistrarVentaPage;
