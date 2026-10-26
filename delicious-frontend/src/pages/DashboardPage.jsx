
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "../components/Navbar";

const API_URL = "https://delicious-backend-uwql.onrender.com";

function DashboardPage() {
  const navigate = useNavigate();

  const [ventas, setVentas] = useState([]);
  const [gastos, setGastos] = useState([]);
  const [errorVentas, setErrorVentas] = useState(false);
  const [errorGastos, setErrorGastos] = useState(false);
  const [cargando, setCargando] = useState(true);

  useEffect(() => {
    let activo = true;

    async function cargarResumen() {
      setCargando(true);

      const resultados = await Promise.allSettled([
        axios.get(`${API_URL}/api/venta`),
        axios.get(`${API_URL}/api/gasto`)
      ]);

      if (!activo) return;

      if (resultados[0].status === "fulfilled") {
        const datos = resultados[0].value.data;
        setVentas(Array.isArray(datos) ? datos : []);
        setErrorVentas(!Array.isArray(datos));
      } else {
        setErrorVentas(true);
      }

      if (resultados[1].status === "fulfilled") {
        const datos = resultados[1].value.data;
        setGastos(Array.isArray(datos) ? datos : []);
        setErrorGastos(!Array.isArray(datos));
      } else {
        setErrorGastos(true);
      }

      setCargando(false);
    }

    cargarResumen();

    return () => {
      activo = false;
    };
  }, []);

  const totalIngresos = ventas.reduce(
    (suma, venta) => suma + (Number(venta.total) || 0),
    0
  );

  const totalGastos = gastos.reduce(
    (suma, gasto) => suma + (Number(gasto.precio) || 0),
    0
  );

  const balance = totalIngresos - totalGastos;


  const ultimosMovimientos = [
    ...ventas.map((venta) => ({
      id: `venta-${venta.idVenta}`,
      tipo: "venta",
      fecha: venta.fecha,
      descripcion: `Venta #${venta.idVenta}`,
      detalle:
        Array.isArray(venta.detalles) && venta.detalles.length > 0
          ? venta.detalles
              .map((d) => d.producto?.nombreProducto)
              .filter(Boolean)
              .join(", ")
          : "Venta registrada",
      valor: Number(venta.total) || 0,
      ruta: "/ventas",
    })),
    ...gastos.map((gasto) => ({
      id: `gasto-${gasto.idGasto}`,
      tipo: "gasto",
      fecha: gasto.fecha,
      descripcion: `Gasto #${gasto.idGasto}`,
      detalle: gasto.descripcionGasto || "Sin descripción",
      valor: Number(gasto.precio) || 0,
      ruta: "/gastos",
    })),
  ]
    .sort(
      (a, b) =>
        (new Date(b.fecha).getTime() || 0) -
        (new Date(a.fecha).getTime() || 0)
    )
    .slice(0, 5);


  const formatoCOP = (valor) =>
    new Intl.NumberFormat("es-CO", {
      style: "currency",
      currency: "COP",
      maximumFractionDigits: 0
    }).format(valor);

  const mostrarValor = (valor, error) => {
    if (cargando) return "Cargando...";
    if (error) return "No disponible";
    return formatoCOP(valor);
  };

  const errorBalance = errorVentas || errorGastos;

  return (
    <>
      <Navbar />

      <main className="dashboard">
        <div className="dashboard-header">
          <div>
            <h1>Dashboard</h1>
            <p>Bienvenido al sistema de gestión de Delicious.</p>
          </div>
        </div>

        <div className="resumen-cards">
          <button
            type="button"
            className="resumen-card ingresos"
            onClick={() => navigate("/ventas")}
          >
            <h3>Ingresos</h3>
            <h2>{mostrarValor(totalIngresos, errorVentas)}</h2>
            <p>Consultar ventas</p>
          </button>

          <button
            type="button"
            className="resumen-card gastos"
            onClick={() => navigate("/gastos")}
          >
            <h3>Gastos</h3>
            <h2>{mostrarValor(totalGastos, errorGastos)}</h2>
            <p>Consultar gastos</p>
          </button>

          <button
            type="button"
            className="resumen-card balance"
            onClick={() => navigate("/ventas")}
          >
            <h3>Balance</h3>
            <h2>{mostrarValor(balance, errorBalance)}</h2>
            <p>Ingresos menos gastos</p>
          </button>
        </div>

        <div className="resumen-financiero">
          <div className="panel">
            <h2>Resumen financiero</h2>

            <div className="fila-resumen">
              <span>Ingresos totales</span>
              <strong>{mostrarValor(totalIngresos, errorVentas)}</strong>
            </div>

            <div className="fila-resumen">
              <span>Gastos totales</span>
              <strong>{mostrarValor(totalGastos, errorGastos)}</strong>
            </div>

            <div className="fila-resumen total">
              <span>Balance general</span>
              <strong>{mostrarValor(balance, errorBalance)}</strong>
            </div>

            {(errorVentas || errorGastos) && (
              <p role="alert">
                No se pudieron cargar todos los datos. Recarga la página e
                intenta nuevamente.
              </p>
            )}

            <h2 style={{ marginTop: "30px" }}>Últimos movimientos</h2>

            {cargando ? (
              <p>Cargando movimientos...</p>
            ) : ultimosMovimientos.length === 0 ? (
              <p>No hay movimientos registrados.</p>
            ) : (
              ultimosMovimientos.map((movimiento) => (
                <div className="fila-resumen" key={movimiento.id}>
                  <div>
                    <strong>
                      {movimiento.tipo === "venta" ? "🟢 " : "🔴 "}
                      {movimiento.descripcion}
                    </strong>
                    <p style={{ margin: "6px 0", color: "#777" }}>
                      {movimiento.detalle}
                    </p>
                    <p style={{ margin: "0 0 5px", fontSize: "13px" }}>
                      {movimiento.fecha
                        ? new Date(movimiento.fecha).toLocaleDateString(
                            "es-CO"
                          )
                        : "Fecha no disponible"}
                    </p>
                    <button
                      type="button"
                      onClick={() => navigate(movimiento.ruta)}
                    >
                      Ver {movimiento.tipo === "venta" ? "ventas" : "gastos"}
                    </button>
                  </div>

                  <strong>
                    {movimiento.tipo === "venta" ? "+" : "−"}
                    {formatoCOP(movimiento.valor)}
                  </strong>
                </div>
              ))
            )}
          </div>
        </div>

      </main>
    </>
  );
}

export default DashboardPage;

