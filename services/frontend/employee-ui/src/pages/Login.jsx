import styles from "./Login.module.css";
import { useState } from "react";
import {Button, Container, Paper, PasswordInput, Stack} from "@mantine/core";

function Login() {
  const [email, setEmail] = useState("jack@example.com");
  const [password, setPassword] = useState("qwerty");

  return (
    <Container size={420} my={40}>
      <Paper withBorder shadow="md" p={30} mt={30} radius="md">
        <Stack spacing="md">
        <div className={styles.row}>
          <label htmlFor="email">Email address</label>
          <input
            type="email"
            id="email"
            onChange={(e) => setEmail(e.target.value)}
            value={email}
          />
        </div>

          <PasswordInput label="Password" placeholder="Your password" required mt="md" />

        <div>
          <Button fullWidth mt="xl">Login</Button>
        </div>
        </Stack>
      </Paper>
    </Container>
  );
}

export default Login;
