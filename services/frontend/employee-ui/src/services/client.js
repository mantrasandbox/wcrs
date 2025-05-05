const API_BASE_URL = "http://employee:4000";

export const getEmployees = async () => {
  return await axios.get(`${API_BASE_URL}` / viewAll);
};
