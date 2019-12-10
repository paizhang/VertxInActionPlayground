package verticals;

import io.vertx.core.Vertx;

public class MainClass {

    public static void main(String[] args) {
        // Created a vertx instance.
        Vertx vertx = Vertx.vertx();

        // Deploy a stander type vertical.
        // The first parameter indicates the vertical class to be deployed. The second parameter is for assigning a callback function which
        // will be called after the deployment finished.
        vertx.deployVerticle(new HelloWorldVertical(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                // Deployment succeed. Get the deployment ID.
                String verticcalId = stringAsyncResult.result();
                System.out.println("Vertical: " + stringAsyncResult.result() + " deployment succeeded!");
                System.out.println("Now starting to undeploy the vertical.");
                // Using the deployment ID to undeploy the vertical.
                vertx.undeploy(verticcalId);
            } else {
                System.out.println("Vertical deployment failed!");
            }
        });

    }
}
