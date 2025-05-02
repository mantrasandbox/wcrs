import React from "react";
import ReactDOM from "react-dom";
import { Formik, Form, useField } from "formik";
import * as Yup from "yup";
import classes from "./SignupForm.module.css";
import {
  Container,
  Title,
  Anchor,
  Text,
  Paper,
  Stack,
  Button,
  Box,
  Group,
} from "@mantine/core";
import { IconInfoCircle } from "@tabler/icons-react";
import {Link} from "react-router-dom";

const MyTextInput = ({ label, ...props }) => {
  // useField() returns [formik.getFieldProps(), formik.getFieldMeta()]
  // which we can spread on <input>. We can use field meta to show an error
  // message if the field is invalid and it has been touched (i.e. visited)
  const [field, meta] = useField(props);
  return (
    <>
      <label htmlFor={props.id || props.name}>{label}</label>
      <input className="text-input" {...field} {...props} />
      {meta.touched && meta.error ? (
        <Group gap={6} mt={4}>
          <IconInfoCircle size={16} color="red" />
          <Text size="sm" c="red">
            {meta.error}
          </Text>
        </Group>
      ) : null}
    </>
  );
};

const MyCheckbox = ({ children, ...props }) => {
  // React treats radios and checkbox inputs differently from other input types: select and textarea.
  // Formik does this too! When you specify `type` to useField(), it will
  // return the correct bag of props for you -- a `checked` prop will be included
  // in `field` alongside `name`, `value`, `onChange`, and `onBlur`
  const [field, meta] = useField({ ...props, type: "checkbox" });
  return (
    <div>
      <label className="checkbox-input">
        <input type="checkbox" {...field} {...props} />
        {children}
      </label>
      {meta.touched && meta.error ? (
        <div className="error">{meta.error}</div>
      ) : null}
    </div>
  );
};

const MySelect = ({ label, ...props }) => {
  const [field, meta] = useField(props);
  return (
    <div>
      <label htmlFor={props.id || props.name}>{label}</label>
      <select {...field} {...props} />
      {meta.touched && meta.error ? (
        <Group gap={6} mt={4}>
          <IconInfoCircle size={16} color="red" />
          <Text size="sm" c="red">
            {meta.error}
          </Text>
        </Group>
      ) : null}
    </div>
  );
};

// And now we can use these
const SignupForm = () => {
  return (
    <>
      <Box
        style={{
          backgroundImage: "url('/restaurant-hero.jpg')",
          backgroundSize: "cover", // or 'contain'
          backgroundRepeat: "no-repeat",
          backgroundPosition: "center",
          minHeight: "100vh",
        }}
      >
        <Box
          style={{
            backdropFilter: "blur(6px)",
            backgroundColor: "rgba(255, 255, 255, 0.3)",
            position: "absolute",
            inset: 0,
            zIndex: 0,
          }}
        />
        <Box style={{ position: "relative", zIndex: 1 }}>
          <Container size={420} my={40}>
            <Title ta="center" className={classes.title}>
              Welcome back!
            </Title>

            <Formik
              initialValues={{
                firstName: "",
                lastName: "",
                email: "",
                acceptedTerms: false, // added for our checkbox
                jobType: "", // added for our select
              }}
              validationSchema={Yup.object({
                firstName: Yup.string()
                  .max(15, "Must be 15 characters or less")
                  .required("Required"),
                lastName: Yup.string()
                  .max(20, "Must be 20 characters or less")
                  .required("Required"),
                email: Yup.string()
                  .email("Invalid email address")
                  .required("Required"),
                acceptedTerms: Yup.boolean()
                  .required("Required")
                  .oneOf([true], "You must accept the terms and conditions."),
                jobType: Yup.string()
                  .oneOf(
                    ["designer", "development", "product", "other"],
                    "Invalid Job Type"
                  )
                  .required("Required"),
              })}
              onSubmit={(values, { setSubmitting }) => {
                setTimeout(() => {
                  alert(JSON.stringify(values, null, 2));
                  setSubmitting(false);
                }, 400);
              }}
            >
              <Form>
                <Paper withBorder shadow="md" p={30} mt={30} radius="md">
                  <Stack spacing="md">
                    <MyTextInput
                      label="First Name"
                      name="firstName"
                      type="text"
                      placeholder="Jane"
                    />

                    <MyTextInput
                      label="Last Name"
                      name="lastName"
                      type="text"
                      placeholder="Doe"
                    />

                    <MyTextInput
                      label="Email Address"
                      name="email"
                      type="email"
                      placeholder="jane@formik.com"
                    />

                    <MySelect label="Job Type" name="jobType">
                      <option value="">Select a job type</option>
                      <option value="designer">Designer</option>
                      <option value="development">Developer</option>
                      <option value="product">Product Manager</option>
                      <option value="other">Other</option>
                    </MySelect>

                    <MyCheckbox name="acceptedTerms">
                      I accept the terms and conditions
                    </MyCheckbox>

                    <Button type="submit" fullWidth mt="md">
                      Submit
                    </Button>
                    <Text c="dimmed" size="sm" ta="center" mt={5}>
                      Do not have an account yet?{" "}
                      <Link to="/"> Create An Account</Link>
                    </Text>
                  </Stack>
                </Paper>
              </Form>
            </Formik>
          </Container>
        </Box>
      </Box>
    </>
  );
};
export default SignupForm;
