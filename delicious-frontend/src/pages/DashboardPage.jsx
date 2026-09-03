import Navbar from "../components/Navbar";

function DashboardPage() {
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
          <div className="resumen-card ingresos">
            <h3>Ingresos</h3>
            <h2>$0</h2>
            <p>Total de ventas</p>
          </div>

          <div className="resumen-card gastos">
            <h3>Gastos</h3>
            <h2>$0</h2>
            <p>Total de gastos</p>
          </div>

          <div className="resumen-card balance">
            <h3>Balance</h3>
            <h2>$0</h2>
            <p>Ingresos - gastos</p>
          </div>
        </div>

        <div className="resumen-financiero">
          <div className="panel">
            <h2>Resumen financiero</h2>

            <div className="fila-resumen">
              <span>Ingresos</span>
              <strong>$0</strong>
            </div>

            <div className="fila-resumen">
              <span>Gastos</span>
              <strong>$0</strong>
            </div>

            <div className="fila-resumen total">
              <span>Balance</span>
              <strong>$0</strong>
            </div>
          </div>
        </div>
      </main>
    </>
  );
}

export default DashboardPage;
