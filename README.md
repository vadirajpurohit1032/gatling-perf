# Gatling Java Performance Testing Project

![Gatling Logo](https://gatling.io/hs-fs/hubfs/Gatling-logo-RVB.png?width=160&height=28&name=Gatling-logo-RVB.png)

## 🚀 Overview

This project is a **Gatling performance testing suite** for load testing APIs using the **Gatling Java DSL**. It is set up with Gradle and targets **Java 17** for maximum compatibility and modern language features.

The included simulation tests the following API:

- **API Name:** Get All Products List
- **Endpoint:** `https://automationexercise.com/api/productsList`
- **Method:** `GET`
- **Expected Response:** HTTP 200, JSON with all products

## 📂 Project Structure

```
performance-lt/
├── loadtest/
│   ├── build.gradle
│   └── src/gatling/java/gatling/simulation/GetAllProductsSimulation.java
├── build.gradle
├── settings.gradle
└── README.md
```

- **`loadtest/`**: Contains all Gatling simulations and Gradle config for load testing.
- **`src/gatling/java/gatling/simulation/`**: Place your Java-based Gatling simulations here.

## 🛠️ Prerequisites

- **Java 17** (Amazon Corretto or OpenJDK recommended)
- **Gradle 7.6.4** (managed via the included wrapper)
- Internet access (for dependency downloads)

## ⚡ Quick Start

1. **Set JAVA_HOME to Java 17:**
   ```sh
   export JAVA_HOME="/Users/deus/Library/Java/JavaVirtualMachines/corretto-17.0.15/Contents/Home"
   ```
2. **Run the simulation:**
   ```sh
   ./gradlew :loadtest:gatlingRun
   ```
   This will compile and execute all simulations in `loadtest/src/gatling/java/gatling/simulation/`.

3. **View the report:**
   After the run, open the generated HTML report:
   ```
   loadtest/build/reports/gatling/getallproductssimulation-<timestamp>/index.html
   ```

## 📝 Simulation Details

**`GetAllProductsSimulation.java`**
- Sends HTTP GET requests to `/api/productsList`
- Checks for HTTP 200 status
- Asserts response time is under 2 seconds
- Logs the response body for debugging
- Simulates 10 users ramped up over 30 seconds
- Includes global assertions for response time and success rate

```java
ScenarioBuilder scn = scenario("Get All Products List")
    .exec(
        http("Get Products List")
            .get("/api/productsList")
            .check(status().is(200))
            .check(responseTimeInMillis().lte(2000))
            .check(bodyString().saveAs("RESPONSE_BODY"))
    )
    .exec(session -> {
        System.out.println("Response Body: " + session.getString("RESPONSE_BODY"));
        return session;
    });
```

## 🧩 Adding More Simulations

1. Create a new Java class in `loadtest/src/gatling/java/gatling/simulation/`.
2. Extend `io.gatling.javaapi.core.Simulation` and define your scenario.
3. Run `./gradlew :loadtest:gatlingRun` to execute all simulations.

## 🐞 Troubleshooting

- **Plugin/Gradle errors?**
  - Ensure you are using Gradle 7.6.4 and Java 17.
  - The Gatling Gradle plugin may not work with newer Gradle or Java versions.
- **Simulation not found?**
  - Check your class/package names and file locations.
- **Network issues?**
  - Make sure the API endpoint is reachable from your machine.

## 📚 References
- [Gatling Documentation](https://gatling.io/docs/)
- [Gatling Java DSL Guide](https://gatling.io/docs/gatling/reference/current/core/java/)
- [Gradle Documentation](https://docs.gradle.org/7.6.4/userguide/userguide.html)

---

> Made with ❤️ using [Gatling](https://gatling.io/) and Java. Happy load testing! 
