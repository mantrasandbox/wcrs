import {
  AppShell,
  Group,
  Image,
  Title,
  Text,
  Button,
  Container,
  SimpleGrid,
  Divider,
  Paper,
  Avatar,
  Flex,
  ThemeIcon,
  useMantineTheme,
  rem,
} from "@mantine/core";

import { IconChefHat, IconCalendar, IconMessage } from "@tabler/icons-react";
import Testimonials from "../components/Testimonials.jsx";
import Footer from "../components/Footer.jsx";
import NavButton from "../components/NavButton.jsx";

const features = [
  {
    title: "Menu Management",
    icon: <IconChefHat size={32} />,
    desc: "Update menus in real-time and track ingredient inventory",
  },
  {
    title: "Table Reservations",
    icon: <IconCalendar size={32} />,
    desc: "Manage bookings and optimize table turnover",
  },
  {
    title: "Guest Feedback",
    icon: <IconMessage size={32} />,
    desc: "Collect and analyze customer reviews",
  },
];

function HomePage() {
  const theme = useMantineTheme();

  return (
    <AppShell padding={0} header={{ height: 80 }}>
      <AppShell.Header px="lg">
        <Flex justify="space-between" align="center" h="100%">
          <Group>
            <Avatar src="/logo.png" size={40} alt="Logo" />
            <Title
              order={4}
              weight={500}
              style={{ fontFamily: "Playfair Display, serif" }}
            >
              Restaurant Management System
            </Title>
          </Group>

          <Group spacing="xl" visibleFrom="sm">
            <NavButton to="/menu" variant="subtle" size="sm">
              Menu
            </NavButton>

            <Button>Register</Button>
            <Button variant="subtle" size="sm">
              Campaign
            </Button>
          </Group>

          <Group>
            <NavButton to="/login" variant="outline">
              Login
            </NavButton>
            <NavButton to="/signup" radius="xl" variant="filled">
              Sign Up
            </NavButton>
          </Group>
        </Flex>
      </AppShell.Header>

      <AppShell.Main>
        <Container fluid bg="rustic.0" py={80}>
          <Container size="xl">
            <SimpleGrid cols={{ base: 1, md: 2 }} spacing="xl">
              <div>
                <Title
                  c="rustic.8"
                  style={{ fontFamily: "Playfair Display, serif" }}
                >
                  Your Dining,
                  <br />
                  <Text c="rustic.8" inherit>
                    Powered by Us
                  </Text>
                </Title>
                <Text size="xl" mt="xl" mb="xl" c="dimmed">
                  Streamline your restaurant operations with our all-in-one
                  management platform designed for modern culinary
                  establishments.
                </Text>
                <Group mt="xl">
                  <Button size="lg" radius="xl" color="red">
                    Get Started
                  </Button>
                  <Button variant="outline" radius="xl" size="xl">
                    Download App
                  </Button>
                </Group>
              </div>
              <Image
                src="/rest1.jpg"
                alt="App preview"
                radius="md"
                style={{ boxShadow: theme.shadows.xl }}
              />
            </SimpleGrid>
          </Container>

          <Divider my="xl" />

          <Container size="xl" py={rem(60)}>
            <Title order={2} ta="center" mb="xl">
              Our Solutions
            </Title>
            <SimpleGrid cols={{ base: 1, sm: 3 }} spacing="lg">
              {features.map((feature) => (
                <Paper
                  shadow="sm"
                  p="xl"
                  radius="md"
                  key={feature.title}
                  style={{ borderTop: `3px solid ${theme.colors.red[6]}` }}
                >
                  <Group mb="sm">
                    <ThemeIcon bg="rustic.0" c="rustic.8" withborder>
                      {feature.icon}
                    </ThemeIcon>
                    <Text weight={600}>{feature.title}</Text>
                  </Group>
                  <Text size="sm" c="dimmed">
                    {feature.desc}
                  </Text>
                </Paper>
              ))}
            </SimpleGrid>
          </Container>
          <Divider my="xl" />

          <Divider my="xl" />
          <Testimonials />
          <Divider my="xl" />

          {/* CTA Section */}
          <Paper
            p="xl"
            mt="xl"
            radius="md"
            style={{
              backgroundImage:
                "linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)), url(/restaurant-hero.jpg)",
              backgroundSize: "cover",
              color: "white",
            }}
          >
            <Title order={3} mb="md" align="center">
              Ready to Transform Your Restaurant?
            </Title>
            <Text align="center" mb="xl">
              Join 500+ restaurants using our platform to deliver exceptional
              dining experiences.
            </Text>
            <Group position="center">
              <Button>Get Started Today</Button>
            </Group>
          </Paper>
        </Container>
      </AppShell.Main>
      <footer>
        <Footer />
      </footer>
    </AppShell>
  );
}

export default HomePage;
