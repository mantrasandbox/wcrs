import axios from "axios";
const API_BASE_URL = "http://localhost:4040/api/v1/employees/";

export const getEmployees = async () => {
  return await axios.get(`${API_BASE_URL}viewAll`);
};
