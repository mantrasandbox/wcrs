import { AppShell, Burger, Group, rem } from "@mantine/core";
import { NavBar } from "../components/NavBar";

import { Logo } from "../components/Logo";
import { useDisclosure } from "@mantine/hooks";
import { Outlet } from "react-router-dom";

function AppLayout() {
  const [opened, { toggle }] = useDisclosure();
  return (
    <AppShell
      header={{ height: 80 }}
      navbar={{
        width: 300,
        breakpoint: "sm",
        collapsed: { mobile: !opened },
      }}
      padding="md"
      styles={{
        main: {
          minHeight: "100vh",
          backgroundColor: "#f8f9fa",
        },
        navbar: {
          backgroundColor: "transparent",
          borderRight: "none",
          padding: rem(16),
        },
        header: {
          backgroundColor: "#fff",
          borderBottom: "1px solid #e9ecef",
          boxShadow: "0 1px 3px rgba(0,0,0,0.05)",
          padding: `0 ${rem(20)}`,
        },
      }}
    >
      <AppShell.Header>
        <Group
          h="100%"
          px="md"
          style={{
            display: "flex",
            alignItems: "center",
            justifyContent: "space-between",
          }}
        >
          <Group>
            <Burger
              opened={opened}
              onClick={toggle}
              hiddenFrom="sm"
              size="sm"
              style={{ marginRight: rem(12) }}
            />
            <Logo />
          </Group>
        </Group>
      </AppShell.Header>

      <AppShell.Navbar>
        <NavBar />
      </AppShell.Navbar>

      <AppShell.Main>
        <div
          style={{
            backgroundColor: "white",
            borderRadius: rem(12),
            boxShadow: "0 1px 3px rgba(0,0,0,0.1)",
            padding: rem(24),
            height: "100%",
          }}
        >
          <Outlet />
        </div>
      </AppShell.Main>
    </AppShell>
  );
}

export default AppLayout;
