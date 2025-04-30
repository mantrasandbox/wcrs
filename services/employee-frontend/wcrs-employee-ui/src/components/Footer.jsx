import { Container, Grid, Text } from "@mantine/core";

export default function Footer() {
  return (
    <footer
      style={{
        backgroundColor: "#f8f9fa",
        padding: "2rem 0",
        marginTop: "2rem",
      }}
    >
      <Container fluid bg="rustic.9" c="rustic.0">
        <Grid>
          <Grid.Col span={4}>
            <Text fw={700}>Contact Us</Text>
            <Text>123 Market Street, Kampala</Text>
            <Text>info@rms.com</Text>
          </Grid.Col>
          <Grid.Col span={4}>
            <Text fw={700}>Quick Links</Text>
            <Text>View Menu</Text>
            <Text>Make Reservation</Text>
          </Grid.Col>
          <Grid.Col span={4}>
            <Text
                fw={700}>External</Text>
            <Text>Local Food Authority</Text>
            <Text>Restaurant Union</Text>
          </Grid.Col>
        </Grid>
      </Container>
    </footer>
  );
}
