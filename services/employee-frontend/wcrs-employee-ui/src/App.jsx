import HomePage from "./pages/HomePage.jsx";
import { Route, Routes } from "react-router";
import Login from "./pages/Login.jsx";
import Menu from "./pages/Menu.jsx";
import PageNotFound from "./pages/PageNotFound.jsx";
import SignUp from "./pages/SignUp.jsx";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="login" element={<Login />} />
      <Route path="signup" element={<SignUp />} />
      <Route path="menu" element={<Menu />} />
      <Route path="*" element={<PageNotFound />} />
    </Routes>
  );
}

export default App;
