package gatling.simulation;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class GetAllProductsSimulation extends Simulation {

    private static final String BASE_URL = "https://automationexercise.com";

    ScenarioBuilder scn = scenario("Get All Products List")
        .exec(
            http("Get Products List")
                .get("/api/productsList")
                .check(status().is(200))
                .check(responseTimeInMillis().lte(2000)) // Example: response time <= 2s
                .check(bodyString().saveAs("RESPONSE_BODY"))
        )
        .exec(session -> {
            System.out.println("Response Body: " + session.getString("RESPONSE_BODY"));
            return session;
        });

    {
        setUp(
            scn.injectOpen(
                rampUsers(10).during(30)
            )
        ).protocols(
            http.baseUrl(BASE_URL)
        ).assertions(
            global().responseTime().max().lt(2000),
            global().successfulRequests().percent().is(100.0)
        );
    }
} 