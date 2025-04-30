import { Paper, Text, Title, SimpleGrid } from "@mantine/core";

const testimonials = [
  {
    name: "John Doe",
    text: "The RMS made managing our kitchen and bookings super easy!",
  },
  {
    name: "Mary Chef",
    text: "Streamlined staff coordination and improved customer experience.",
  },
];

function Testimonials() {
  return (
    <>
      <Title order={3} align="center">
        What Our Clients Say
      </Title>
      <SimpleGrid cols={{ base: 1, sm: 2 }} spacing="lg" mt="md">
        {testimonials.map((t, i) => (
          <Paper key={i} p="lg" shadow="sm">
            <Text>"{t.text}"</Text>
            <Text size="sm" mt="sm" italic>
              – {t.name}
            </Text>
          </Paper>
        ))}
      </SimpleGrid>
    </>
  );
}

export default Testimonials;
