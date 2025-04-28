import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5173, // 👈 Your custom port
    strictPort: true, // 👈 Force it to fail if port is already in use
  },
});
