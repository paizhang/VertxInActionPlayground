package verticals;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

import java.util.Arrays;

public class MainClass {

    // TODO: Failed with exception: current classloader must be URLClassloader.
    public static DeploymentOptions getDeploymentOptionsForIsolationGroup() {
        return new DeploymentOptions().setIsolationGroup("myGroup")
                .setIsolatedClasses(Arrays.asList("com.personal.practice.Vertical.VertxInActionPlayground.verticals.HelloWorldVertical"));
    }

    public static DeploymentOptions getDeploymentOptionsWithNumberOfInstance() {
        return new DeploymentOptions().setInstances(10);
    }

    public static void main(String[] args) {
        // Created a vertx instance.
        // TODO: How to get the number of event loops.
        Vertx vertx = Vertx.vertx();

        // Deploy a stander type vertical.
        // The first parameter indicates the vertical class to be deployed. The second parameter is for assigning a callback function which
        // will be called after the deployment finished.
        // TODO: Learn more about deployment option.
        DeploymentOptions deploymentOptions = getDeploymentOptionsWithNumberOfInstance();

        // If you create a vertical by passing an instance of a vertical, the number of instance has to be 1. Otherwise it will cause
        // java.lang.IllegalArgumentException: Can't specify > 1 instances for already created verticle.
        // By deploy the vertical using the class name of the vertical, we can specify the number of vertical instances that we want to deploy.
        vertx.deployVerticle(HelloWorldVertical.class.getName(), deploymentOptions, stringAsyncResult -> {
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
