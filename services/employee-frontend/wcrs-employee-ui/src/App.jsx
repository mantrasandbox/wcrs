import HomePage from "./pages/HomePage.jsx";
import { createTheme, MantineProvider } from "@mantine/core";

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

function App() {
  return <MantineProvider theme={theme}>
    <HomePage />
  </MantineProvider>;
}

export default App;
