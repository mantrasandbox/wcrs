import { Button } from "@mantine/core";
import { NavLink } from "react-router-dom";

function NavButton({ to, children, ...props }) {
  return (
    <NavLink
      to={to}
      style={() => ({
        textDecoration: "none",
        pointerEvents: "auto",
      })}
    >
      <Button radius="xl" {...props}>
        {children}
      </Button>
    </NavLink>
  );
}

export default NavButton;
