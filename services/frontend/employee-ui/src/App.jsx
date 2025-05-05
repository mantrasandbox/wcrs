import HomePage from "./pages/HomePage.jsx";
import { Route, Routes } from "react-router-dom";
import Login from "./pages/Login.jsx";
import Menu from "./pages/Menu.jsx";
import PageNotFound from "./pages/PageNotFound.jsx";

import AppLayout from "./pages/AppLayout.jsx";
import { EmployeeTable } from "./components/EmployeeTable.jsx";
import SignupForm from "./components/SignupForm.jsx";
import { useEffect, useState } from "react";
import { getEmployees } from "./services/client.js";

function App() {
  const [employees, setEmployees] = useState([]);
  const [error, setError] = useState("");

  function fetchEmployees() {
    getEmployees()
      .then((res) => {
        console.log(res.data.content);
        const employeesList = res?.data?.content;
        if (Array.isArray(employeesList)) {
          setEmployees(res.data.content || []);
        }
      })
      .catch((err) => {
        setError(err.message);
      });
  }

  useEffect(() => {
    fetchEmployees();
  }, []);
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="login" element={<Login />} />
      <Route path="signup" element={<SignupForm />} />
      <Route path="menu" element={<Menu />} />
      <Route path="*" element={<PageNotFound />} />

      <Route path="app" element={<AppLayout />}>
        <Route index element={<h1>In progress...</h1>} />
        <Route
          path="employees"
          element={
            <EmployeeTable
              employees={employees}
              fetchEmployees={fetchEmployees}
            />
          }
        />
      </Route>
    </Routes>
  );
}

export default App;
