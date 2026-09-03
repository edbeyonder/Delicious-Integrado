import "./App.css";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import DashboardPage from "./pages/DashboardPage";
import InventarioPage from "./pages/InventarioPage";
import FproductoPage from "./pages/FProductoPage";
import LoginPage from "./pages/LoginPage";
import RegistrarVentaPage from "./pages/RegistrarVentaPage";
import VerVentasPage from "./pages/VerVentasPage";
import GastosPage from "./pages/GastosPage";
import RegistrarGastoPage from "./pages/RegistrarGastoPage";

// Componente que protege las rutas internas de Delicious.
// Si no existe una sesión iniciada, devuelve al usuario al Login.
function RutaProtegida({ children }) {
  const sesionIniciada = sessionStorage.getItem("sesionIniciada");

  if (sesionIniciada !== "true") {
    return <Navigate to="/" replace />;
  }

  return children;
}

function App() {
  return (
    <BrowserRouter>
      <Routes>
        {/* Login: esta ruta es pública */}
        <Route path="/" element={<LoginPage />} />

        {/* Dashboard protegido */}
        <Route
          path="/dashboard"
          element={
            <RutaProtegida>
              <DashboardPage />
            </RutaProtegida>
          }
        />

        {/* Inventario protegido */}
        <Route
          path="/inventario"
          element={
            <RutaProtegida>
              <InventarioPage />
            </RutaProtegida>
          }
        />

        <Route
          path="/inventario/nuevo"
          element={
            <RutaProtegida>
              <FproductoPage />
            </RutaProtegida>
          }
        />

        <Route
          path="/inventario/actualizar"
          element={
            <RutaProtegida>
              <FproductoPage />
            </RutaProtegida>
          }
        />

        {/* Ventas protegidas */}
        <Route
          path="/registrarventa"
          element={
            <RutaProtegida>
              <RegistrarVentaPage />
            </RutaProtegida>
          }
        />

        <Route
          path="/ventas"
          element={
            <RutaProtegida>
              <VerVentasPage />
            </RutaProtegida>
          }
        />

        <Route
          path="/ventas/:id"
          element={
            <RutaProtegida>
              <div>Detalle de venta</div>
            </RutaProtegida>
          }
        />

        {/* Gastos protegidos */}
        <Route
          path="/gastos"
          element={
            <RutaProtegida>
              <GastosPage />
            </RutaProtegida>
          }
        />

        <Route
          path="/gastos/nuevo"
          element={
            <RutaProtegida>
              <RegistrarGastoPage />
            </RutaProtegida>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
