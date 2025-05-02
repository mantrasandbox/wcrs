import {
  IconAdjustments,
  IconCalendarStats,
  IconFileAnalytics,
  IconGauge,
  IconPresentationAnalytics,
} from "@tabler/icons-react";
import { Code, Group, ScrollArea, rem, Stack } from "@mantine/core";
import { LinksGroup } from "./NavbarLinksGroup";
import { UserButton } from "./Userbutton";

import { FaUserFriends } from "react-icons/fa";

const mockdata = [
  { label: "Dashboard", icon: IconGauge },
  {
    label: "Employees",
    icon: FaUserFriends,
    initiallyOpened: false,
    links: [
      { label: "New Employee", link: "#" },
      { label: "Employee List", link: "employees" },
    ],
  },
  {
    label: "Menu",
    icon: IconCalendarStats,
    links: [
      { label: "New Menu", link: "#" },
      { label: "Menu Lists", link: "#" },
    ],
  },
  { label: "Analytics", icon: IconPresentationAnalytics },
  { label: "Contracts", icon: IconFileAnalytics },
  { label: "Settings", icon: IconAdjustments },
];

export function NavBar() {
  const links = mockdata.map((item) => (
    <LinksGroup {...item} key={item.label} />
  ));

  return (
    <div
      style={{
        display: "flex",
        flexDirection: "column",
        height: "100%",
        backgroundColor: "white",
        borderRadius: rem(12),
        boxShadow: "0 0 8px rgba(0,0,0,0.1)",
        justifyContent: "space-between",
      }}
    >
      <ScrollArea offsetScrollbars scrollbarSize={4}>
        <Stack gap="sm">{links}</Stack>
      </ScrollArea>

      <div
        style={{
          padding: rem(16),
          borderTop: `1px solid #e9ecef`,
        }}
      >
        <UserButton />
      </div>
    </div>
  );
}
