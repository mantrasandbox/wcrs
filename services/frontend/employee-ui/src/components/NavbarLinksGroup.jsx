import { useState } from "react";
import {
  IconCalendarStats,
  IconChevronRight,
  IconChevronDown,
} from "@tabler/icons-react";
import {
  Box,
  Collapse,
  Group,
  Text,
  ThemeIcon,
  UnstyledButton,
  rem,
  Stack,
} from "@mantine/core";
import { useDisclosure } from "@mantine/hooks";
import { NavLink } from "react-router-dom";

export function LinksGroup({ icon: Icon, label, initiallyOpened, links }) {
  const hasLinks = Array.isArray(links) && links.length > 0;
  const [opened, { toggle }] = useDisclosure(initiallyOpened ?? false);
  const items = (hasLinks ? links : []).map((link) => (
    <Text
      component={NavLink}
      to={link.link}
      key={link.label}
      size="sm"
      fw={500}
      style={{
        display: "block",
        padding: `6px ${rem(18)}`,
        color: "#495057",
        textDecoration: "none",
        borderRadius: rem(6),
      }}
      className="hover:bg-gray-100"
    >
      {link.label}
    </Text>
  ));

  return (
    <Stack gap={4}>
      <UnstyledButton
        onClick={hasLinks ? toggle : undefined}
        component={hasLinks ? "button" : NavLink}
        to={hasLinks ? undefined : "#"}
        style={{
          display: "block",
          width: "100%",
          padding: `${rem(8)} ${rem(12)}`,
          borderRadius: rem(8),
          color: "#212529",
          fontWeight: 600,
          transition: "background-color 0.2s",
        }}
        className="hover:bg-gray-100"
      >
        <Group justify="space-between" align="center" wrap="nowrap">
          <Group gap="xs">
            <ThemeIcon variant="light" color="orange" size="md">
              <Icon size="1.1rem" />
            </ThemeIcon>
            <Text size="sm">{label}</Text>
          </Group>
          {hasLinks &&
            (opened ? (
              <IconChevronDown size="1rem" />
            ) : (
              <IconChevronRight size="1rem" />
            ))}
        </Group>
      </UnstyledButton>
      {hasLinks && (
        <Collapse in={opened} transitionDuration={150}>
          <Stack gap={2} pl="lg" mt={4}>
            {items}
          </Stack>
        </Collapse>
      )}
    </Stack>
  );
}

const mockdata = {
  label: "Releases",
  icon: IconCalendarStats,
  links: [
    { label: "Upcoming releases", link: "/" },
    { label: "Previous releases", link: "/" },
    { label: "Releases schedule", link: "/" },
  ],
};

export function NavbarLinksGroup() {
  return (
    <Box mih={220} p="md">
      <LinksGroup {...mockdata} />
    </Box>
  );
}
