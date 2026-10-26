
import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";

function Navbar() {
  const navigate = useNavigate();
  const [ventasAbierto, setVentasAbierto] = useState(false);
  const [gastosAbierto, setGastosAbierto] = useState(false);
  const [inventarioAbierto, setInventarioAbierto] = useState(false);

  const cerrarSesion = () => {
    sessionStorage.removeItem("sesionIniciada");
    navigate("/");
  };

  return (
    <aside className="sidebar">
      <h2>DELICIOUS</h2>

      <Link to="/dashboard" className="acceso">
        🏠 Dashboard
      </Link>

      {/* VENTAS */}
      <button
        className="menu-btn"
        onClick={() => setVentasAbierto(!ventasAbierto)}
      >
        📊 Ventas {ventasAbierto ? "▲" : "▼"}
      </button>

      {ventasAbierto && (
        <div className="submenu">
          <Link to="/registrarventa">Registrar Venta</Link>
          <Link to="/ventas">Ver Ventas</Link>
        </div>
      )}

      {/* GASTOS */}
      <button
        className="menu-btn"
        onClick={() => setGastosAbierto(!gastosAbierto)}
      >
        💰 Gastos {gastosAbierto ? "▲" : "▼"}
      </button>

      {gastosAbierto && (
        <div className="submenu">
          <Link to="/gastos/nuevo">Registrar Gasto</Link>
          <Link to="/gastos">Ver Gastos</Link>
        </div>
      )}

      {/* INVENTARIO */}
      <button
        className="menu-btn"
        onClick={() => setInventarioAbierto(!inventarioAbierto)}
      >
        📦 Inventario {inventarioAbierto ? "▲" : "▼"}
      </button>

      {inventarioAbierto && (
        <div className="submenu">
          <Link to="/inventario/nuevo">Agregar Producto</Link>
          <Link to="/inventario">Ver Inventario</Link>
        </div>
      )}

      <button onClick={cerrarSesion} className="acceso">
        🚪 Cerrar sesión
      </button>
    </aside>
  );
}

export default Navbar;
