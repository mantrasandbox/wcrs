import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import {createTheme, MantineProvider} from "@mantine/core";
import "@mantine/core/styles.css";
import { BrowserRouter } from "react-router";

const theme = createTheme({
  colors: {
    // Custom rustic palette (extend Mantine's defaults)
    warm: [
      "#FFF9DB", // 0: Cream (backgrounds)
      "#FFE8A5", // 1: Light gold
      "#FCC419", // 2: Gold
      "#F59F00", // 3: Amber
      "#E67700", // 4: Dark orange
      "#D9480F", // 5: Rust (primary accent)
      "#A61E4D", // 6: Deep berry (for contrast)
      "#5C3D2E", // 7: Coffee (text)
      "#343A40", // 8: Dark gray
      "#212529", // 9: Near black
    ],
  },
  primaryColor: "warm", // Buttons/etc will use warm.5 (Rust)
  primaryShade: 5, // Default shade (D9480F)
});

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <MantineProvider theme={theme}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </MantineProvider>
  </StrictMode>
);
