# deutsche-trading
The application integrates with a third-party trading algorithm library and processes trading signals received via HTTP. The signals trigger specific trading algorithms defined in the Algo class.

Prerequisites
1) Java Development Kit (JDK) 8 or higher
2) Maven (for building and managing dependencies)

Setup
1) Clone the repository
2) Build the project using Maven
3) Run the Spring Boot application:

The application will start on http://localhost:8080.

API Endpoint
POST /fetch-signal
Parameters:
signal (integer) - The trading signal to be processed.

Example:
POST http://localhost:8080/fetch-signal?signal=1





