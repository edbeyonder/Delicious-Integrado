import axios from "axios";

/*
 * URL base de la API de Spring Boot.
 * Todas las peticiones del inventario partirán desde aquí.
 */
const API_URL = "http://localhost:8080/api/inventario";

/*
 * Obtiene todos los productos registrados.
 */
export const obtenerInventario = () => {
  return axios.get(API_URL);
};
