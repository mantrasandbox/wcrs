import { Container, SimpleGrid, Text, Title, Image } from "@mantine/core";
import classes from "./PageNotFound.module.css";
import NavButton from "../components/NavButton";

function PageNotFound() {
  return (
    <Container className={classes.root}>
      <SimpleGrid spacing={{ base: 40, sm: 80 }} cols={{ base: 1, sm: 2 }}>
        <Image src="/notfound.jpg" className={classes.mobileImage} />
        <div>
          <Title className={classes.title}>Something is not right...</Title>
          <Text c="dimmed" size="lg">
            Page you are trying to open does not exist. You may have mistyped
            the address, or the page has been moved to another URL. If you think
            this is an error contact support.
          </Text>
          <NavButton
            variant="outline"
            size="md"
            mt="xl"
            className={classes.control}
            to="/"
          >
            Get back to home page
          </NavButton>
        </div>
      </SimpleGrid>
    </Container>
  );
}

export default PageNotFound;
