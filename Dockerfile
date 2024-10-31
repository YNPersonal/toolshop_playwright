# Use a lightweight Node.js image as the base
FROM mcr.microsoft.com/playwright:focal

# Install Java 8 (OpenJDK)
RUN apt-get update && \
    apt-get install -y openjdk-8-jdk && \
    apt-get clean
    
# Set working directory inside the container
WORKDIR /usr/src/app

# Copy the package.json and package-lock.json first to take advantage of Docker cache
COPY package*.json ./

# Install dependencies (including Playwright)
RUN npm install

# Copy all files from the local directory to the container's working directory
COPY . .

# Expose port 3000 to the host
EXPOSE 4200

# Install Playwright browsers (if not already included in the Playwright image)
RUN npx playwright install

# Command to run Playwright tests
CMD ["sh", "-c", "npx playwright test & tail -f /dev/null"]