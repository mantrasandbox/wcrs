import HomePage from "./pages/HomePage.jsx";
import { Route, Routes } from "react-router-dom";
import Login from "./pages/Login.jsx";
import Menu from "./pages/Menu.jsx";
import PageNotFound from "./pages/PageNotFound.jsx";
import SignUp from "./pages/SignUp.jsx";

import AppLayout from "./pages/AppLayout.jsx";
import { EmployeeTable } from "./components/EmployeeTable.jsx";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="login" element={<Login />} />
      <Route path="signup" element={<SignUp />} />
      <Route path="menu" element={<Menu />} />
      <Route path="*" element={<PageNotFound />} />

      <Route path="app" element={<AppLayout />}>
        <Route index element={<h1>In progress...</h1>} />
        <Route path="employees" element={<EmployeeTable />} />
      </Route>
    </Routes>
  );
}

export default App;
