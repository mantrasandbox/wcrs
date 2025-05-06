import { IconPencil, IconTrash } from "@tabler/icons-react";
import {
  ActionIcon,
  Anchor,
  Avatar,
  Badge,
  Card,
  Group,
  Table,
  Text,
  rem,
} from "@mantine/core";

const data = [
  {
    avatar:
      "https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-1.png",
    name: "Robert Wolfkisser",
    job: "Engineer",
    email: "rob_wolf@gmail.com",
    phone: "+44 (452) 886 09 12",
  },
  {
    avatar:
      "https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-7.png",
    name: "Jill Jailbreaker",
    job: "Engineer",
    email: "jj@breaker.com",
    phone: "+44 (934) 777 12 76",
  },
  {
    avatar:
      "https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-2.png",
    name: "Henry Silkeater",
    job: "Designer",
    email: "henry@silkeater.io",
    phone: "+44 (901) 384 88 34",
  },
  {
    avatar:
      "https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-3.png",
    name: "Bill Horsefighter",
    job: "Designer",
    email: "bhorsefighter@gmail.com",
    phone: "+44 (667) 341 45 22",
  },
  {
    avatar:
      "https://raw.githubusercontent.com/mantinedev/mantine/master/.demo/avatars/avatar-10.png",
    name: "Jeremy Footviewer",
    job: "Manager",
    email: "jeremy@foot.dev",
    phone: "+44 (881) 245 65 65",
  },
];

const jobColors = {
  engineer: "blue",
  manager: "cyan",
  designer: "pink",
};

export function EmployeeTable({ employees, fetchEmployees }) {
  const rows =
    Array.isArray(employees) &&
    employees.map((employee) => (
      <Table.Tr key={employee.fullName}>
        <Table.Td>
          <Group gap="sm">
            {/* <Avatar size={30} src={item.avatar} radius={30} /> */}
            <Text fz="sm" fw={500}>
              {employee.fullName}
            </Text>
          </Group>
        </Table.Td>

        {/* <Table.Td>
        <Badge color={jobColors[item.job.toLowerCase()]} variant="light">
          {item.job}
        </Badge>
      </Table.Td> */}
        <Table.Td>
          <Text fz="sm">{employee.NIN}</Text>
        </Table.Td>
        {/* <Table.Td>
        <Text fz="sm">{item.phone}</Text>
      </Table.Td> */}
        <Table.Td>
          <Group gap={0} justify="flex-end">
            <ActionIcon variant="subtle" color="gray">
              <IconPencil size={16} stroke={1.5} />
            </ActionIcon>
            <ActionIcon variant="subtle" color="red">
              <IconTrash size={16} stroke={1.5} />
            </ActionIcon>
          </Group>
        </Table.Td>
      </Table.Tr>
    ));

  return (
    <Card
      withBorder
      padding={0}
      style={{
        borderRadius: rem(12),
        overflow: "hidden", // Ensures child table corners are rounded
        boxShadow: "0 1px 3px rgba(0,0,0,0.1)",
      }}
    >
      <Table.ScrollContainer minWidth={800}>
        <Table
          verticalSpacing="sm"
          striped
          highlightOnHover
          styles={{
            thead: {
              backgroundColor: "#f1f3f5", // Light header background
            },
            th: {
              padding: `${rem(12)} ${rem(16)}`,
              fontWeight: 600,
            },
            td: {
              padding: `${rem(12)} ${rem(16)}`,
            },
          }}
        >
          <Table.Thead>
            <Table.Tr>
              <Table.Th>Employee</Table.Th>
              <Table.Th>Job title</Table.Th>
              <Table.Th>Email</Table.Th>
              <Table.Th>Phone</Table.Th>
              <Table.Th />
            </Table.Tr>
          </Table.Thead>
          <Table.Tbody>{rows?.length ? rows : null}</Table.Tbody>
        </Table>
      </Table.ScrollContainer>
    </Card>
  );
}
