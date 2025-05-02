# Stage 1: Build the application
FROM node:18-alpine AS builder

WORKDIR /app

# Copy package files first to leverage Docker cache
COPY package.json yarn.lock* package-lock.json* ./

# Install dependencies (prefer yarn if available, otherwise use npm)
RUN if [ -f yarn.lock ]; then yarn --frozen-lockfile; \
    else npm ci; fi

# Copy all files
COPY . .

# Build the app
RUN npm run build

# Stage 2: Serve the application
FROM node:18-alpine

WORKDIR /app

# Copy built assets from builder
COPY --from=builder /app/dist ./build

# Install serve to run the production build
RUN npm install -g serve

# Expose the port the app runs on
EXPOSE 3000

# Start the app
CMD ["serve", "-s", "build", "-l", "3000"]